package com.commands;

import com.auxiliary.TextColor;
import com.main.Data;
import com.main.Listener;

/** Shows the first element of the collection */
public class HeadCommand extends Command {
    public HeadCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        return Data.createData("head");
    }
}