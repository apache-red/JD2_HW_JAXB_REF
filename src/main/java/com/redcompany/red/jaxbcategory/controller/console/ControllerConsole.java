package com.redcompany.red.jaxbcategory.controller.console;

import com.redcompany.red.jaxbcategory.controller.console.command.BasicCommand;
import com.redcompany.red.jaxbcategory.controller.console.command.CommandManager;


public class ControllerConsole implements ConsoleAction{

    private static final ControllerConsole instance = new ControllerConsole();


public void doAction (String action){

    String commandName = action;
    BasicCommand basicCommand = CommandManager.getInstance().getCommand(action);
    

}


    public static ControllerConsole getInstance() {
        return instance;
    }




}
