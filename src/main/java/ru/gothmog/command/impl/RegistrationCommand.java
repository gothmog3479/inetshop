package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.RegistrationService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = RegistrationService.getInstance().doService(request);

        if (result) {
            page = JspPageName.SUCCESSFUL_REGISTRATION_PAGE;
        } else {
            page = JspPageName.REGISTRATION_PAGE;
        }
        return page;
    }
}
