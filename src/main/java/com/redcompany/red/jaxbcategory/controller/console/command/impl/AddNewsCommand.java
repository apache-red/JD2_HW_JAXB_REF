package com.redcompany.red.jaxbcategory.controller.console.command.impl;

import com.redcompany.red.jaxbcategory.controller.console.command.BasicCommand;
import com.redcompany.red.jaxbcategory.service.impl.AddNewsService;

import java.util.HashMap;

public class AddNewsCommand implements BasicCommand {

    @Override
    public boolean performAction(HashMap<String, String> param) {
        boolean result = false;
        return result = AddNewsService.getInstance().doService(param);

    }
}
