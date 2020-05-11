package com.bulumutka.polyconstr.ui;

import javafx.scene.canvas.Canvas;

public interface Drawable {
    void draw(Canvas canvas);

    boolean contains(double x, double y);
}
