package com.study_group;

import java.io.Serializable;

/** Enum class of semesters on which a group can be */
public enum Semester implements Serializable {
    FIRST("1"),
    SECOND("2"),
    FOURTH("4"),
    SIXTH("6"),
    SEVENTH("7");

    final String num;
    private static final long serialVersionUID = 4L;

    Semester(String num) {
        this.num = num;
    }

    /**
     * Finds name of the semester by number
     *
     * @param s - number of the semester
     * @return Enum Semester
     */
    public static Semester find(String s) {
        switch (s) {
            case ("1"):
                return FIRST;
            case ("2"):
                return SECOND;
            case ("3"):
                return FOURTH;
            case ("4"):
                return SIXTH;
            case ("5"):
                return SEVENTH;
            default:
                return null;
        }
    }

    /**
     * Finds number of the semester by its name
     *
     * @param s - name of the semester
     * @return number
     */
    public static String find(Semester s) {
        return s.num;
    }
}

