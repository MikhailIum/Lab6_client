package com.auxiliary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/** Checks if some parameters are correct */
public class Check {

    /**
     * Checks if string can be converted to double
     *
     * @param x - string
     * @return true/false
     */
    private static boolean isDouble(String x) {
        try {
            Double.parseDouble(x);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Checks if string can be converted to float
     *
     * @param x - string
     * @return true/false
     */
    private static boolean isFloat(String x) {
        try {
            Float.parseFloat(x);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Checks if string can be converted to integer
     *
     * @param x - string
     * @return true/false
     */
    private static boolean isInteger(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Checks if string can be converted to long
     *
     * @param x - string
     * @return true/false
     */
    private static boolean isLong(String x) {
        try {
            Long.parseLong(x);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Checks if name is in correct form
     *
     * @return true/false
     */
    public static boolean checkName(String name) {
        if (name == null || name.equals("") || name.replace(" ", "").equals("")) {
            System.out.println(
                    TextColor.ANSI_RED + "Field 'name' should consist of at least one character" + TextColor.ANSI_RESET);
            return false;
        } else return true;
    }

    /**
     * Checks if there are enough coordinates
     *
     * @param coords - coordinates
     * @param del - delimiter between coordinates
     * @return true/false
     */
    private static boolean checkCordArgs(String coords, String del) {
        if (coords == null || coords.equals("")) {
            System.out.println(
                    TextColor.ANSI_RED
                            + "Field 'coordinates' can't be an empty string"
                            + TextColor.ANSI_RESET);
            return false;
        } else {
            ArrayList<String> cord_ar = new ArrayList<>(Arrays.asList(coords.split(del)));
            cord_ar.removeIf(s -> Objects.equals(s, ""));

            if (cord_ar.size() != 2) {
                System.out.println(
                        TextColor.ANSI_RED
                                + "Field 'coordinates' should have 2 parameters"
                                + TextColor.ANSI_RESET);
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the coordinates is in the correct form
     *
     * @param coords - coordinates
     * @param del - delimiter between coordinates
     * @return true/false
     */
    public static boolean checkCoords(String coords, String del) {
        if (!checkCordArgs(coords, del)) {
            return false;
        } else {
            ArrayList<String> cord_ar = new ArrayList<>(Arrays.asList(coords.split(del)));
            cord_ar.removeIf(s -> Objects.equals(s, ""));
            if (isDouble(cord_ar.get(0)) && isLong(cord_ar.get(1))) {
                if (Double.parseDouble(cord_ar.get(0)) > 860) {
                    System.out.println(
                            TextColor.ANSI_RED
                                    + "The x parameter should be less than 860"
                                    + TextColor.ANSI_RESET);
                    return false;
                } else return true;
            } else {
                System.out.println(
                        TextColor.ANSI_RED
                                + "The x parameter should be double, the y - integer"
                                + TextColor.ANSI_RESET);
                return false;
            }
        }
    }

    /**
     * Checks if number of students/number of expelled students/number of students who should be
     * expelled is in correct form
     *
     * @param field - number of students/number of expelled students/number of students who should be
     *     expelled
     * @return true/false
     */
    public static boolean checkStudentsCount(String num, String field) {
        if (num == null || num.equals("")) {
            return true;
        } else if (isInteger(num) && Integer.parseInt(num) > 0) {
            return true;
        } else {
            System.out.println(
                    TextColor.ANSI_RED
                            + "'"
                            + field
                            + "' field should be a positive integer(<= "
                            + Integer.MAX_VALUE
                            + ")"
                            + TextColor.ANSI_RESET);
            return false;
        }
    }

    /**
     * Checks if admin's birthday is in correct form
     *
     * @param date - birthday
     * @return true/false
     */
    public static boolean checkBirthday(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate.parse(date, format);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println(
                    TextColor.ANSI_RED + "Date should be in a DD.MM.YYYY format" + TextColor.ANSI_RESET);
            return false;
        }
    }

    /**
     * Checks if the coordinate is in the correct form
     *
     * @param x - coordinate value
     * @param type - x/y/z
     * @return true/false
     */
    public static boolean checkCoordinate(String x, String type) {
        if (type.equals("x")) {
            if (!isFloat(x)) {
                System.out.println(
                        TextColor.ANSI_RED + "Coordinate should be a float" + TextColor.ANSI_RESET);
                return false;
            }
            return true;
        } else if (type.equals("y")) {
            if (!isInteger(x)) {
                System.out.println(
                        TextColor.ANSI_RED + "Coordinate should be an integer" + TextColor.ANSI_RESET);
                return false;
            }
            return true;
        } else {
            if (!isLong(x)) {
                System.out.println(
                        TextColor.ANSI_RED + "Coordinate should be an integer" + TextColor.ANSI_RESET);
                return false;
            }
            return true;
        }
    }

    /**
     * Checks if the user printed one of the numbers written above
     *
     * @param left - the number should be more than left
     * @param right - the number should be more than right
     * @return true/false
     */
    public static boolean checkEnum(String num, int left, int right) {
        if (!isInteger(num)) {
            System.out.println(TextColor.ANSI_RED + "Please, write a number" + TextColor.ANSI_RESET);
            return false;
        } else {
            int a = Integer.parseInt(num);
            if (a <= right && a >= left) {
                return true;
            } else {
                System.out.println(
                        TextColor.ANSI_RED + "You can use only numbers written above" + TextColor.ANSI_RESET);
                return false;
            }
        }
    }
}
