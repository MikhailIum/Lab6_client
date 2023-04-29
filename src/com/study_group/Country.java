package com.study_group;

import java.io.Serializable;

/** Enum class of countries in which an admin can live */
public enum Country implements Serializable {
    UNITED_KINGDOM("UK"),
    USA("USA"),
    FRANCE("France"),
    THAILAND("Thailand");

    final String name;
    private static final long serialVersionUID = 7L;


    Country(String country) {
        name = country;
    }

    /**
     * Finds full name of the country by short one
     *
     * @param s - short name of the country
     * @return Enum County
     */
    public static Country find(String s) {
        switch (s) {
            case ("UK"):
                return UNITED_KINGDOM;
            case ("USA"):
                return USA;
            case ("France"):
                return FRANCE;
            case ("Thailand"):
                return THAILAND;
            default:
                return null;
        }
    }

    /**
     * Finds number of the country by its short name
     *
     * @param s - short name of the country
     * @return number
     */
    public static String findNum(String s) {
        switch (s) {
            case ("UK"):
                return "1";
            case ("USA"):
                return "2";
            case ("France"):
                return "3";
            case ("Thailand"):
                return "4";
            default:
                return null;
        }
    }

    /**
     * Finds full name of the country by its number
     *
     * @param s - number
     * @return Enum Country
     */
    public static Country findByNum(String s) {
        switch (s) {
            case ("1"):
                return UNITED_KINGDOM;
            case ("2"):
                return USA;
            case ("3"):
                return FRANCE;
            case ("4"):
                return THAILAND;
            default:
                return null;
        }
    }

    /**
     * Finds short name of the country by full one
     *
     * @param c - full name of the country
     * @return short name
     */
    public static String find(Country c) {
        return c.name;
    }
}

