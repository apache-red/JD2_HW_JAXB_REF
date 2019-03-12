package com.redcompany.red.jaxbcategory.controller.console.command.impl;

import com.redcompany.red.jaxbcategory.controller.console.command.BasicCommand;
import com.redcompany.red.jaxbcategory.service.impl.ShowAllNewsService;

import java.util.HashMap;

public class ShowAllNewsCommand implements BasicCommand {
    @Override
    public boolean performAction(HashMap<String, String> param) {
        boolean result = false;
        return result = ShowAllNewsService.getInstance().doService(param);
    }

}
