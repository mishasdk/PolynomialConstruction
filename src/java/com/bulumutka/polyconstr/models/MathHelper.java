package com.bulumutka.polyconstr.models;

public class MathHelper {
    private MathHelper() {
    }

    public static double dist(double x1, double y1, double x2, double y2) {
        double shiftX = Math.abs(x1 - x2);
        double shiftY = Math.abs(y1 - y2);
        return Math.sqrt(shiftX * shiftX + shiftY * shiftY);
    }
}

