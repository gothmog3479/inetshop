package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = CancelOrderService.getInstance().doService(request);
        if (result) {
            page = JspPageName.SHOPPING_CART_PAGE;
            ShowOrdersOfOneClientService.getInstance().doService(request);
        }
        return page;
    }
}
