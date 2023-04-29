package com.commands;


import com.main.Data;
import com.main.Listener;

import java.util.LinkedList;

/** Removes all the groups from the collection */
public class ClearCommand extends Command {
    public ClearCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        return Data.createData("clear");
    }

}