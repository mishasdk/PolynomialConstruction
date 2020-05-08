package com.bulumutka.polyconstr.models;

import javafx.scene.canvas.Canvas;

import java.util.Arrays;
import java.util.List;

public class Edge2D<Vertex extends Vertex2D> implements Drawable {
    public Vertex source;
    public Vertex target;
    public double time;

    public Edge2D(Vertex source, Vertex target, double time) {
        this.source = source;
        this.target = target;
        this.time = time;
    }

    @Override
    public void draw(Canvas canvas) {
        double x1 = source.getX();
        double y1 = source.getY();
        double x2 = target.getX();
        double y2 = target.getY();
        //        var cords = cutLine(x1, y1, x2, y2, Vertex2D.VERTEX_RADIUS);
        //        cords = cutLine(cords.get(2), cords.get(3), cords.get(0), cords.get(1),
        //        Vertex2D.VERTEX_RADIUS);
        var g = canvas.getGraphicsContext2D();
        g.strokeLine(x1, y1, x2, y2);
        g.fillText(Double.toString(time), (x1 + x2) / 2, (y1 + y2) / 2);
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    private List<Double> cutLine(double x1, double y1, double x2, double y2, double value) {
        var s1 = x2 - x1;
        var s2 = y2 - y1;
        var dist = MathHelper.dist(x1, y1, x2, y2);
        var n1 = s1 / Math.sqrt(dist);
        var n2 = s2 / Math.sqrt(dist);
        var s3 = n1 * (dist - value);
        var s4 = n2 * (dist - value);
        return Arrays.asList(x1, y1, x1 + s3, y1 + s4);
    }
}
