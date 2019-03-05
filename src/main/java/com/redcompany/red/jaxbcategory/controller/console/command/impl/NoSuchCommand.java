package com.redcompany.red.jaxbcategory.controller.console.command.impl;

import com.redcompany.red.jaxbcategory.controller.console.command.BasicCommand;

import java.util.HashMap;

public class NoSuchCommand implements BasicCommand {
    @Override
    public void performAction(HashMap<String, String> paran)
    {
        System.out.println("NoSuchCommand!");
    }
}
