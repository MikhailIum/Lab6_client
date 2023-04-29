package com.commands;

import com.auxiliary.TextColor;
import com.main.Data;
import com.main.Listener;

/**
 * shows the whole collection
 */
public class ShowCommand extends Command {
    public ShowCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        return Data.createData("show");
    }


}