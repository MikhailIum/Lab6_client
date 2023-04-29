package com.commands;

import com.auxiliary.TextColor;
import com.main.Data;
import com.main.Listener;

/** Shows those groups which names contain certain substring */
public class FilterNameCommand extends Command {
    public FilterNameCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        if (args.length == 1) {
            System.out.println(TextColor.ANSI_YELLOW + "This command should have an argument" + TextColor.ANSI_RESET);
            return null;
        }

        return Data.createData("filter_contains_name", args);
    }
}
