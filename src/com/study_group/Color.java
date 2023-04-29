package com.study_group;

import java.io.Serializable;

/** Enum class for colors of admin hair */
public enum Color implements Serializable {
    BLACK("black"),
    BLUE("blue"),
    BROWN("brown");

    final String name;
    private static final long serialVersionUID = 9L;


    Color(String color) {
        name = color;
    }

    /**
     * Finds hair color by its number
     *
     * @param s - number
     * @return Enum Color
     */
    public static Color findByNum(String s) {
        switch (s) {
            case ("1"):
                return BLACK;
            case ("3"):
                return BLUE;
            case ("2"):
                return BROWN;
            default:
                return null;
        }
    }

    /**
     * Finds number of the hair color by its name
     *
     * @param s - name of the hair color
     * @return number
     */
    public static String findNum(String s) {
        switch (s) {
            case ("black"):
                return "1";
            case ("blue"):
                return "3";
            case ("brown"):
                return "2";
            default:
                return null;
        }
    }

    /**
     * Finds enum Color by its name
     *
     * @param s - name of the hair color
     * @return Enum Color
     */
    public static Color find(String s) {
        switch (s) {
            case ("black"):
                return BLACK;
            case ("blue"):
                return BLUE;
            case ("brown"):
                return BROWN;
            default:
                return null;
        }
    }

    /**
     * Finds name of the hair color by its enum
     *
     * @param c - enum Color
     * @return name of hair color
     */
    public static String find(Color c) {
        return c.name;
    }
}
