package com.redcompany.red.jaxbcategory.controller.console.command.impl;

import com.redcompany.red.jaxbcategory.controller.console.command.BasicCommand;

import java.util.HashMap;

public class NoSuchCommand implements BasicCommand {
    @Override
    public boolean performAction(HashMap<String, String> paran)
    {
       return false;
    }
}
