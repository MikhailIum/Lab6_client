package com.commands;

import com.auxiliary.TextColor;
import com.main.Data;
import com.main.Listener;

import java.util.Scanner;

/** Shows admins whose names lexicographically greater than given */
public class FilterAdminCommand extends Command {
    public FilterAdminCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print(
                TextColor.ANSI_PURPLE
                        + "Name"
                        + TextColor.ANSI_BLUE
                        + "(string)"
                        + TextColor.ANSI_PURPLE
                        + ": "
                        + TextColor.ANSI_RESET);
        String name = in.nextLine();

        return Data.createData("filter_greater_than_group_admin", new String[]{name});
    }

}
