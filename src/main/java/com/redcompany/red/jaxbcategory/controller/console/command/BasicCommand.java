package com.redcompany.red.jaxbcategory.controller.console.command;

import java.util.HashMap;

public interface BasicCommand {


    boolean performAction(HashMap<String, String> param);
}
