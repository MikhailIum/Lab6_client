package com.auxiliary;

import com.study_group.StudyGroup;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 2L;

    public boolean isException;
    public String text;

    public StudyGroup group;

    public Exception exception;

    private Message(String text, boolean isException, StudyGroup group){
        this.text = text;
        this.group = group;
        this.isException = isException;
    }

    public static Message createMessage(String text){
        return new Message(text, false, null);
    }

    public static Message createMessage(String text, Exception ex){
        Message message = new Message(text, true, null);
        message.exception = ex;
        return message;
    }

    public static Message createMessage(StudyGroup studyGroup){
        return new Message("", false, studyGroup);
    }

}
