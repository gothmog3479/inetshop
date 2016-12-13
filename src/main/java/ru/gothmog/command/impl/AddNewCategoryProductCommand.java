package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.AddNewCategoryProductService;
import ru.gothmog.service.impl.RecordAllCategoriesService;

import javax.servlet.http.HttpServletRequest;

public class AddNewCategoryProductCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        boolean result = false;

        result = AddNewCategoryProductService.getInstance().doService(request);

        if (result) {
            RecordAllCategoriesService.getInstance().doService(request);
        }
        return JspPageName.MAIN_PAGE;
    }
}
