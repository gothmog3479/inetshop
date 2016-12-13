package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.EditProductService;
import ru.gothmog.service.impl.GoEditProductService;

import javax.servlet.http.HttpServletRequest;

public class EditProductCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = EditProductService.getInstance().doService(request);

        if (result) {
            page = JspPageName.MAIN_PAGE;
        } else {
            GoEditProductService.getInstance().doService(request);
            page = JspPageName.EDIT_PRODUCT_PAGE;
        }
        return page;
    }
}
