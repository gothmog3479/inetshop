package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.AddNewProductService;
import ru.gothmog.service.impl.GoAddProductService;

import javax.servlet.http.HttpServletRequest;

public class AddNewPoductCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = AddNewProductService.getInstance().doService(request);

        if (result) {
            page = JspPageName.MAIN_PAGE;
        } else {
            GoAddProductService.getInstance().doService(request);
            page = JspPageName.Add_NEW_PRODUCT_PAGE;
        }
        return page;
    }
}
