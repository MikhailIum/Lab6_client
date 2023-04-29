package com.commands;

import com.auxiliary.Message;
import com.main.*;



import java.net.http.WebSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/** Shows information about the collection */
public class InfoCommand extends Command {
    public InfoCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
         return Data.createData("info");
    }




}

