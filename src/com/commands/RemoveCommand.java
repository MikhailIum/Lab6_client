package com.commands;

import com.main.Data;
import com.main.Listener;

/** Remove an element by its name */
public class RemoveCommand extends Command {
    public RemoveCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {

        return Data.createData("remove", args);
    }
}
