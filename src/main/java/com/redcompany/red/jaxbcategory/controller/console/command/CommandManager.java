package com.redcompany.red.jaxbcategory.controller.console.command;


import com.redcompany.red.jaxbcategory.controller.console.command.impl.*;
import com.redcompany.red.jaxbcategory.controller.console.util.CommandName;

import java.util.HashMap;
import java.util.Map;

public class CommandManager  {

    private static final CommandManager instance = new CommandManager();
    private Map<CommandName, BasicCommand> commands = new HashMap<>();


    public CommandManager() {
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.XJCEGENERATION_COMMAND, new XJCEGenCommand());

    }



    public static CommandManager getInstance() {
        return instance;
    }

    public BasicCommand getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        BasicCommand command;
        if (name != null) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }

}
