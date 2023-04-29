package com.commands;

import com.auxiliary.TextColor;
import com.main.Data;
import com.main.Listener;

/** Removes first group from the collection(lexicographically first) */
public class RemoveFirstCommand extends Command {
    public RemoveFirstCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        return Data.createData("remove_first");
    }

}
