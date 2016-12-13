package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;

import javax.servlet.http.HttpServletRequest;

public class RemoveProductCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = RemoveProductService.getInstance().doService(request);
        if (result) {
            page = JspPageName.MAIN_PAGE;
        }
        return page;
    }
}
