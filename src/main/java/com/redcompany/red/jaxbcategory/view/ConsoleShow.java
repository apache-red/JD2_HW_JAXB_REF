package com.redcompany.red.jaxbcategory.view;

import com.redcompany.red.jaxbcategory.controller.console.ConsoleAction;
import com.redcompany.red.jaxbcategory.controller.console.ControllerConsole;
import com.redcompany.red.jaxbcategory.controller.console.util.CommandName;

import java.util.HashMap;
import java.util.Scanner;


public class ConsoleShow implements ConsoleAction {

    private HashMap<String, String> action = new HashMap<>();
    private HashMap<String, String> param = new HashMap<>();

    public void startConsoleView() {
        while (true){
            showBasicMenu();
            readUserInput();
        }
    }


    private void showBasicMenu() {
        System.out.println("9 - generate Entity from XML");
        System.out.println("0 - Exit");
    }

    public void readUserInput() {
        int item = readMenuItem();
        getInputData(item);
    }


    public boolean doAction(HashMap<String, String> action, HashMap<String, String> param) {
        ConsoleAction consoleAction = ControllerConsole.getInstance();
        return consoleAction.doAction(action, param);
    }


    private int readMenuItem() {
        System.out.print("Input field: ");
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        } else {
            System.out.println("!Exeption! Enter a number");
            readMenuItem();
        }
        return number;
    }

    private String readMenuItemString() {
        System.out.print("Input field: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str;
    }

    private void getInputData(int menuItem) {
        boolean answer ;
        switch (menuItem) {
            case 9:
                action.put("action", CommandName.XJCEGENERATION_COMMAND.toString());
                param.put("param", CommandName.NO_SUCH_COMMAND.toString());
                answer = doAction(action, param);
                consoleAnswer(CommandName.XJCEGENERATION_COMMAND.toString(), answer);
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    private void consoleAnswer(String param, boolean answer) {
        if (answer== true){
            System.out.println("Action: " + param + " was successful");
        }else {
            System.out.println("Action: " + param + " was not successful! Try Again!");
        }

    }
}
