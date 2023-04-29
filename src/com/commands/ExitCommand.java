package com.commands;

import com.main.Data;
import com.main.Listener;

public class ExitCommand extends Command{
    public ExitCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        System.exit(0);
        return null;
    }
}
