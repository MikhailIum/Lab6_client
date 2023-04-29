package com.main;

import com.auxiliary.CommandMapMaker;
import com.auxiliary.TextColor;
import com.commands.Command;

import java.io.*;
import java.util.*;

public class Listener {

    public Map<String, Command> commands;

    public Data start() throws Exception {
        commands = CommandMapMaker.makeDefault();
        return commands.get("info").request(this, null);
    }

    /** Listening from terminal stream */
    public Data listen() throws Exception {
        Data data = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] line = in.readLine().split(" ");
            if (line.length != 0) data = executeCommands(line);
        } catch (Exception ex) {
            System.out.println(
                    "\n"
                            + TextColor.ANSI_YELLOW
                            + "Ooops...something went wrong :("
                            + TextColor.ANSI_RESET);
            System.exit(0);
        }
        return data;
    }

    /**
     * Calls execution methods for commands
     *
     * @param line - user input
     */
    public Data executeCommands(String[] line) throws Exception {
        Data data = null;
        if (commands.containsKey(line[0])) {
            data = commands.get(line[0]).request(this, line);
//            groups.sort(
//                    new Comparator<StudyGroup>() {
//                        @Override
//                        public int compare(StudyGroup o1, StudyGroup o2) {
//                            return o1.getName().compareTo(o2.getName());
//                        }
//
//                        @Override
//                        public boolean equals(Object obj) {
//                            return obj.equals(this);
//                        }
//                    });
        } else if (!Objects.equals(line[0], "")) {
            System.out.println("Use 'help' to see possible commands");
        }
        return data;
    }
}


