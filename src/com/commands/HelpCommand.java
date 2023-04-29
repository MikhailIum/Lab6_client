package com.commands;

import com.main.Data;
import com.main.Listener;

/**
 * Gives a list of possible commands Called when user prints the text that does not correspond to
 * any of the possible commands
 */
public class HelpCommand extends Command {

    public HelpCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        listener.commands.values().stream()
                .forEach((command -> System.out.println(command.getName() + ": " + command.getDescription())));
        System.out.println();
        return null;
    }


}