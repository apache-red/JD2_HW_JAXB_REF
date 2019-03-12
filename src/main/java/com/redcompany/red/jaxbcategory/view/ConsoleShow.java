package com.redcompany.red.jaxbcategory.view;

import com.redcompany.red.jaxbcategory.controller.console.ConsoleAction;
import com.redcompany.red.jaxbcategory.controller.console.ControllerConsole;
import com.redcompany.red.jaxbcategory.controller.console.util.CommandName;
import com.redcompany.red.jaxbcategory.entity.Category;
import com.redcompany.red.jaxbcategory.entity.SubCategoryType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.util.HashMap;
import java.util.Scanner;

import static com.redcompany.red.jaxbcategory.service.util.ServiceConstantStorage.JAXB_CONTEXT_PATH;


public class ConsoleShow implements ConsoleAction {

    private static final ConsoleShow instance = new ConsoleShow();
    private HashMap<String, String> action = new HashMap<>();
    private HashMap<String, String> param = new HashMap<>();

    public void startConsoleView() {
        while (true) {
            showBasicMenu();
            readUserInput();
        }
    }

    private void showBasicMenu() {
        System.out.println("1 - Show All News");
        System.out.println("2 - Add News");
        System.out.println("9 - Generate Entity from XML");
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
        boolean answer;
        switch (menuItem) {
            case 1:
                action.put("action", CommandName.SHOWALLNEWS_COMMAND.toString());
                param.put("param", CommandName.NO_SUCH_PARAM.toString());
                answer = doAction(action, param);
                consoleAnswer(CommandName.SHOWALLNEWS_COMMAND.toString(), answer);
                break;
            case 2:
                action.put("action", CommandName.ADD_NEWS_COMMAND.toString());
                addNewsParam();
                answer = doAction(action, param);
                consoleAnswer(CommandName.ADD_NEWS_COMMAND.toString(), answer);
                break;
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


    private void addNewsParam(){
        param.put("param", CommandName.ADD_NEWS_COMMAND.toString());
        System.out.println("Add title:");
        param.put("title", readMenuItemString());
        System.out.println("Add director:");
        param.put("director", readMenuItemString());
        System.out.println("Add date_of_issue (Format dd.MM.yyyy):");
        param.put("date_of_issue", readMenuItemString());
        System.out.println("Add news_body:");
        param.put("news_body", readMenuItemString());
    }

    private void consoleAnswer(String param, boolean answer) {
        if (answer == true) {
            System.out.println("Action: " + param + " was successful");
        } else {
            System.out.println("Action: " + param + " was not successful! Try Again!");
        }
    }

    public void consoleShow(Category category) {
        try {
            JAXBContext context = JAXBContext.newInstance(JAXB_CONTEXT_PATH);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            try {
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            } catch (PropertyException e) {
                e.printStackTrace();
            }
            marshaller.marshal(category, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static ConsoleShow getInstance() {
        return instance;
    }


}
