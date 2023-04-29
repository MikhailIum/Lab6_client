package com.commands;


import com.auxiliary.TextColor;
import com.main.Client;
import com.main.Data;
import com.main.Listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;


/**
 * Puts lines from a given file to the input stream(like if user would write them)
 */
public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println(TextColor.ANSI_YELLOW + "You should print path to the script\n" + TextColor.ANSI_RESET);
            return null;
        }

        String filename = args[1];
        Scanner in;
        try {
            in = new Scanner(new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException ex){
            System.out.println(TextColor.ANSI_YELLOW + "There is no such a file\n" + TextColor.ANSI_RESET);
            return null;
        }
        while (in.hasNext()){
            String lineToShow = in.nextLine();
            System.out.println(TextColor.ANSI_GREEN + lineToShow + TextColor.ANSI_RESET);
            String[] arg = lineToShow.split(" ");
            if (Objects.equals(arg[0], "execute_script") && (Objects.equals(arg[1], filename)))
                System.out.println(TextColor.ANSI_YELLOW + "You can't execute the same script here" + TextColor.ANSI_YELLOW);
            else {
                Client.makeRequest(listener.executeCommands(arg));
            }
        }
        Client.executing = false;
        return null;
    }
}