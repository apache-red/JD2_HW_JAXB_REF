package com.redcompany.red.jaxbcategory.view;

import com.redcompany.red.jaxbcategory.controller.console.ConsoleAction;
import com.redcompany.red.jaxbcategory.controller.console.ControllerConsole;

public class ConsoleShow  {

  private ControllerConsole controllerConsole;

    public void startConsoleView() {
        showBasicMenu();
doAction(new String("no"));
    }



    private void showBasicMenu() {


        System.out.println("9 - generate Entity from XML");
        System.out.println("0 - Exit");
    }




    public void doAction(String action) {
        ConsoleAction consoleAction = ControllerConsole.getInstance();
        consoleAction.doAction(action);
    }
}
