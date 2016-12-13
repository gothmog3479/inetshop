package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.controller.RequestParameterName;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        if ((request.getSession(false).getAttribute(RequestParameterName.CLIENT) != null)
                || (request.getSession(false).getAttribute(RequestParameterName.ADMIN) != null)) {
            request.getSession(false).invalidate();
        }
        return JspPageName.MAIN_PAGE;
    }
}
