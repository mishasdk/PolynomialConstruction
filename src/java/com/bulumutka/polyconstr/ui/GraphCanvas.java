package com.bulumutka.polyconstr.ui;

import com.bulumutka.polyconstr.models.Drawable;
import com.bulumutka.polyconstr.models.Edge2D;
import com.bulumutka.polyconstr.models.Vertex2D;

import java.util.ArrayList;
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
    }
}
