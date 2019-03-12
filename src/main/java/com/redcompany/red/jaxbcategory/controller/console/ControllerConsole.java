package com.redcompany.red.jaxbcategory.controller.console;

import com.redcompany.red.jaxbcategory.controller.console.command.BasicCommand;
import com.redcompany.red.jaxbcategory.controller.console.command.CommandManager;

import java.util.HashMap;


public class ControllerConsole implements ConsoleAction {

    private static final ControllerConsole instance = new ControllerConsole();

    public boolean doAction(HashMap<String, String> action, HashMap<String, String> param) {

        String commandName = action.get("action");
        BasicCommand command = CommandManager.getInstance().getCommand(commandName);
        return command.performAction(param);

    }

    public static ControllerConsole getInstance() {
        return instance;
    }

}
