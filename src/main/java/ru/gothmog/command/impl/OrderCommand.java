package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.OrderService;
import ru.gothmog.service.impl.ShowInformationAboutProductService;

import javax.servlet.http.HttpServletRequest;

public class OrderCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = OrderService.getInstance().doService(request);

        if (result) {
            page = JspPageName.SUCCESSFUL_ORDER_PAGE;
        } else {
            ShowInformationAboutProductService.getInstance().doService(request);
            page = JspPageName.SINGLE_PRODUCT_PAGE;
        }
        return page;
    }
}
