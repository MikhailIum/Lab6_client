package com.commands;

import com.auxiliary.Check;
import com.auxiliary.CollectionException;
import com.auxiliary.Message;
import com.auxiliary.TextColor;
import com.main.Client;
import com.main.Data;
import com.main.Listener;
import com.study_group.StudyGroup;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class UpdateCommand extends Command {
    public UpdateCommand(String name, String description) {
        super(name, description);
    }

    /** Enables update mode, where user can choose which fields he/she wants to change */
    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        Message message = Client.requestWithAnswer(Data.createData("update", args));
        assert message != null;
        if(message.isException) {
            System.out.println(TextColor.ANSI_YELLOW + message.text + TextColor.ANSI_RESET);
            return null;
        }

        StudyGroup toUpdate = message.group;

        return Data.createData("update_element", update(toUpdate));
    }

    /**
     * Updates the chosen group
     *
     * @param toUpdate - groups the user wants to be updated
     */
    private StudyGroup update(StudyGroup toUpdate) throws IOException {
        System.out.println(TextColor.ANSI_YELLOW + "Chosen group:" + TextColor.ANSI_RESET);
        System.out.println(toUpdate);
        String num = "7";
        while (!Objects.equals(num, "0")) {
            System.out.println(TextColor.ANSI_YELLOW + "What do you want to update:");
            System.out.println(
                    "0 - nothing, stop updating\n1 - name\n2 - coordinates\n3 - number of students\n"
                            + "4 - expelled students\n5 - should be expelled\n6 - semester\n7 - admin's name\n"
                            + "8 - admin's birthday\n9 - admin's hair color\n10 - admin's nationality\n"
                            + "11 - admin's location x coordinate\n12 - admin's location y coordinate\n"
                            + "13 - admin's location z coordinate\n14 - admin location's name"
                            + TextColor.ANSI_RESET);

            Scanner in = new Scanner(System.in);
            while (!Check.checkEnum((num = in.nextLine()), 0, 14)) {}
            if (!Objects.equals(num, "0")) {
                toUpdate.updateField(Integer.parseInt(num));
            }
        }
        System.out.println("The group has been updated");
        return toUpdate;
    }


}
