package com.commands;

import com.auxiliary.TextColor;
import com.main.Data;
import com.main.Listener;
import java.util.ArrayList;import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;import java.util.stream.Collectors;

/** Shows all admins of the groups */
public class ShowAdminsCommand extends Command {
    public ShowAdminsCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public Data request(Listener listener, String[] args) throws Exception {
        return Data.createData("print_field_ascending_group_admin");
    }

}
