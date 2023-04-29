package com.study_group;

import com.auxiliary.TextColor;
import com.commands.AddCommand;
import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/** Admin of the study group */
public class Person implements Serializable {
    private static final long serialVersionUID = 5L;
    static String tabs = "\t\t";
    private final Location location;
    private String name;
    private java.time.LocalDate birthday;
    private Color hairColor;
    private Country nationality;

    public Person(
            String name,
            java.time.LocalDate birthday,
            Color hairColor,
            Country nationality,
            Location location) {
        this.name = name;
        this.birthday = birthday;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    /**
     * Defines if tabs are necessary for printing info about admin If the whole element of the
     * collection is showed - yes If only admins are showed - no
     */
    public static void changeTabs() {
        if (Objects.equals(tabs, "")) {
            tabs = "\t\t";
        } else {
            tabs = "";
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String name_str =
                TextColor.ANSI_PURPLE
                        + "\n"
                        + tabs
                        + "name"
                        + TextColor.ANSI_WHITE
                        + ": "
                        + TextColor.ANSI_CYAN
                        + name
                        + TextColor.ANSI_RESET;
        String birthday_str =
                TextColor.ANSI_PURPLE
                        + "\n"
                        + tabs
                        + "birthday"
                        + TextColor.ANSI_WHITE
                        + ": "
                        + TextColor.ANSI_CYAN
                        + birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        + TextColor.ANSI_RESET;
        String hair_color_str =
                TextColor.ANSI_PURPLE
                        + "\n"
                        + tabs
                        + "hair color"
                        + TextColor.ANSI_WHITE
                        + ": "
                        + TextColor.ANSI_CYAN
                        + hairColor
                        + TextColor.ANSI_RESET;
        String nationality_str =
                TextColor.ANSI_PURPLE
                        + "\n"
                        + tabs
                        + "nationality"
                        + TextColor.ANSI_WHITE
                        + ": "
                        + TextColor.ANSI_CYAN
                        + nationality
                        + TextColor.ANSI_RESET;
        String location_str =
                TextColor.ANSI_PURPLE
                        + "\n"
                        + tabs
                        + "location"
                        + TextColor.ANSI_WHITE
                        + ": "
                        + TextColor.ANSI_RESET
                        + "{"
                        + location.toString()
                        + "\n"
                        + tabs
                        + "}";

        return name_str + birthday_str + hair_color_str + nationality_str + location_str;
    }

    /**
     * Making a string of all the fields
     *
     * @return String
     */
    public String getParams() {
        return name
                + ","
                + birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                + ","
                + Color.find(hairColor)
                + ","
                + Country.find(nationality)
                + ","
                + location.getParams();
    }

    /**
     * Call a method from AddCommand class according to which field user wants to update
     *
     * @param num - number of the field user wants to update
     */
    public void updateField(int num) throws IOException {
        switch (num) {
            case (7):
                this.name = AddCommand.getGroupName();
                break;
            case (8):
                this.birthday = AddCommand.getBirthday();
                break;
            case (9):
                this.hairColor = AddCommand.getHairColor();
                break;
            case (10):
                this.nationality = AddCommand.getNationality();
                break;
            default:
                location.updateField(num);
        }
    }
}
