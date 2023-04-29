package com.main;

import com.study_group.StudyGroup;

import java.io.Serializable;
import java.util.Objects;

public class Data implements Serializable {

    private static final long serialVersionUID = 1L;

    private String commandType;
    private String[] args;

    StudyGroup group;

    private Data(String commandType, String[] args, StudyGroup group){
        this.commandType = commandType;
        this.args = args;
        this.group = group;
    }

    public static Data createData(String commandType){
        return new Data(commandType, null, null);
    }

    public static Data createData(String commandType, String[] args){
        return new Data(commandType, args, null);
    }

    public static Data createData(String commandType, StudyGroup group){return new Data(commandType, null, group);}
}
