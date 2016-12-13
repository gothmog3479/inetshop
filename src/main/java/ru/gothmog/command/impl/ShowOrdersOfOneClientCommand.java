package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.ShowOrdersOfOneClientService;

import javax.servlet.http.HttpServletRequest;

public class ShowOrdersOfOneClientCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        boolean result = false;
        String page = null;

        result = ShowOrdersOfOneClientService.getInstance().doService(request);
        if (result) {
            page = JspPageName.SHOPPING_CART_PAGE;
        }
        return page;
    }
}
