package com.bulumutka.polyconstr.ui;

import java.util.List;

public class GraphCanvas extends ResizableCanvas {
    private List<Drawable> components;

    public void setData(List<Drawable> data) {
        components = data;
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("Draw graph canvas.");
        for (var component : components) {
            component.draw(this);
        }
        for (var component : components) {
            if (component instanceof Vertex2D) {
                component.draw(this);
            }
        }
    }
}
