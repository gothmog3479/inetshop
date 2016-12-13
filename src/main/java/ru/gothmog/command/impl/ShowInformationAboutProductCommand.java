package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;

import javax.servlet.http.HttpServletRequest;

public class ShowInformationAboutProductCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        boolean result = false;
        String page = null;

        result = ShowInformationAboutProductService.getInstance().doService(request);
        if (result) {
            page = JspPageName.SINGLE_PRODUCT_PAGE;
        }
        return page;
    }
}
