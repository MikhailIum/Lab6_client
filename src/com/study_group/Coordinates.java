package com.study_group;

import java.io.Serializable;

/** Coordinates of the group */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 8L;
    private final double x;
    private final long y;

    public Coordinates(double x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Making a string of all the fields
     *
     * @return String
     */
    public String get_params() {
        return x + "," + y;
    }
}
