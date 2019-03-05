package com.redcompany.red.jaxbcategory.controller.console.command.impl;

import com.redcompany.red.jaxbcategory.controller.console.command.BasicCommand;
import com.redcompany.red.jaxbcategory.service.impl.XJCEGenerationService;

import java.util.HashMap;

public class XJCEGenCommand implements BasicCommand {

    @Override
    public void performAction(HashMap<String, String> param) {


        boolean result = false;

        result = XJCEGenerationService.getInstance().doService(param);


//        if (result) {
//            XJCEGenerationService.getInstance().doService(actionName, param);
//        }

    }
}
