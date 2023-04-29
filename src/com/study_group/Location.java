package com.study_group;

import com.auxiliary.TextColor;
import com.commands.AddCommand;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

/** Admin's location */
public class Location implements Serializable {
    private static final long serialVersionUID = 6L;
    static String tabs = "\t\t\t";
    private float x;
    private Integer y;
    private long z;
    private String name;

    public Location(float x, Integer y, long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * Defines if extra tabs are necessary for printing info about admin If the whole element of the
     * collection is showed - yes If only admins are showed - no
     */
    public static void changeTabs() {
        if (Objects.equals(tabs, "")) {
            tabs = "\t\t\t";
        } else {
            tabs = "\t";
        }
    }

    @Override
    public String toString() {
        String coords_str =
                TextColor.ANSI_PURPLE
                        + "\n"
                        + tabs
                        + "coordinates"
                        + TextColor.ANSI_WHITE
                        + ": "
                        + TextColor.ANSI_CYAN
                        + "("
                        + x
                        + ", "
                        + y
                        + ", "
                        + z
                        + ")"
                        + TextColor.ANSI_RESET;
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

        return coords_str + name_str;
    }

    /**
     * Making a string of all the fields
     *
     * @return String
     */
    public String getParams() {
        return x + "," + y + "," + z + "," + name;
    }

    /**
     * Call a method from AddCommand class according to which field user wants to update
     *
     * @param num - number of the field user wants to update
     */
    public void updateField(int num) throws IOException {
        switch (num) {
            case (11):
                this.x = Float.parseFloat(AddCommand.getCoordinate("x"));
                break;
            case (12):
                this.y = Integer.parseInt(AddCommand.getCoordinate("y"));
                break;
            case (13):
                this.z = Long.parseLong(AddCommand.getCoordinate("z"));
                break;
            case (14):
                this.name = AddCommand.getGroupName();
                break;
        }
    }
}
