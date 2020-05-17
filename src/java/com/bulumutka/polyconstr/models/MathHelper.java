package com.bulumutka.polyconstr.models;

import java.lang.Math;

import static java.lang.Math.sqrt;

public class MathHelper {
    private MathHelper() {
    }

    public static double dist(double x1, double y1, double x2, double y2) {
        double shiftX = Math.abs(x1 - x2);
        double shiftY = Math.abs(y1 - y2);
        return sqrt(shiftX * shiftX + shiftY * shiftY);
    }

    public static double length(double[] a, double[] b) {
        return sqrt((a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]));
    }
}
