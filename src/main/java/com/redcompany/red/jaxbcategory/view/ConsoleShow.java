package com.redcompany.red.jaxbcategory.view;

import com.redcompany.red.jaxbcategory.controller.console.ConsoleAction;
import com.redcompany.red.jaxbcategory.controller.console.ControllerConsole;

import java.util.HashMap;




public class ConsoleShow  {

  private ControllerConsole controllerConsole;
private HashMap<String , String> action =  new HashMap<>();
    private HashMap<String , String> param =  new HashMap<>();
    public void startConsoleView() {
        showBasicMenu();

        action.put(new String("action"), new String("XJCEGENERATION_COMMAND"));
        param.put(new String("param"), new String("NO_SUCH_PARAM"));

        doAction(action, param);
    }



    private void showBasicMenu() {


        System.out.println("9 - generate Entity from XML");
        System.out.println("0 - Exit");
    }




    public void doAction(HashMap<String, String> action, HashMap<String, String> param) {
        ConsoleAction consoleAction = ControllerConsole.getInstance();
        consoleAction.doAction(action, param);
    }
}
