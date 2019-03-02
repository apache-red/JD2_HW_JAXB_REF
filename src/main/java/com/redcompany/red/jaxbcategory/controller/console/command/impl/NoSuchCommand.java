package com.redcompany.red.jaxbcategory.controller.console.command.impl;

import com.redcompany.red.jaxbcategory.controller.console.command.BasicCommand;

public class NoSuchCommand implements BasicCommand {
    @Override
    public void performAction(String action) {
        System.out.println("NoSuchCommand!");
    }
}
