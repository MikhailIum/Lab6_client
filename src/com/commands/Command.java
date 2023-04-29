package com.commands;
import com.auxiliary.Message;
import com.main.*;


/**
 * Abstract class for commands which user can execute to manipulate with a collection
 */
public abstract class Command {
    private final String name;
    private final String description;


    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }


    /**
     * Method called when user print the command's name
     */
    public abstract Data request(Listener listener, String[] args) throws Exception;



}
