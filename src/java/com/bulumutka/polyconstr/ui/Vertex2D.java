package com.bulumutka.polyconstr.ui;

import com.bulumutka.polyconstr.models.MathHelper;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Vertex2D<Vertex> implements Drawable {
    public static final double VERTEX_RADIUS = 10;

    private final Vertex vertex;
    private final double x;
    private final double y;
    private boolean start = false;

    public Vertex2D(Vertex vertex, double x, double y) {
        this.vertex = vertex;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setIsStart(boolean isStart) {
        start = isStart;
    }

    @Override
    public void draw(Canvas canvas) {
        double x = getX();
        double y = getY();
        var g = canvas.getGraphicsContext2D();
        g.setStroke(Color.BLACK);
        g.setFill(Color.WHITE);
        if (start) {
            g.setStroke(Color.BLUE);
        }
        g.fillOval(centerX(), centerY(), 2 * VERTEX_RADIUS, 2 * VERTEX_RADIUS);
        g.strokeOval(centerX(), centerY(), 2 * VERTEX_RADIUS, 2 * VERTEX_RADIUS);
    }

    @Override
    public boolean contains(double x, double y) {
        return MathHelper.dist(getX(), getY(), x, y) < VERTEX_RADIUS;
    }

    @Override
    public String toString() {
        return "Vertex2D{" + "vertex=" + vertex + ", x=" + x + ", y=" + y + '}';
    }

    public double centerX() {
        return getX() - VERTEX_RADIUS;
    }

    public double centerY() {
        return getY() - VERTEX_RADIUS;
    }
}
