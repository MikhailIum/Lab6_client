package com.commands;

import com.auxiliary.TextColor;
import com.main.Data;
import com.main.Listener;

import java.util.Objects;
import java.util.Scanner;

/** Adds a new group if its name is lexicographically less than others */
public class AddIfMinCommand extends Command {
    public AddIfMinCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        String[] args_new = new String[3];
        System.out.print(
                TextColor.ANSI_PURPLE
                        + "Name"
                        + TextColor.ANSI_BLUE
                        + "(string)"
                        + TextColor.ANSI_PURPLE
                        + ": "
                        + TextColor.ANSI_RESET);
        Scanner in = new Scanner(System.in);
        args_new[0] = args[0];
        args_new[2] = "";
        while (Objects.equals(args_new[2], "")) {
            args_new[2] = in.nextLine();
            if(Objects.equals(args_new[2], ""))
                System.out.println(
                    TextColor.ANSI_YELLOW + "Empty string isn't possible here \n" + TextColor.ANSI_RESET);
        }
        args_new[1] = "if min";
        return listener.commands.get("add").request(listener, args_new);
    }

}

