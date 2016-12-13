package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.controller.RequestParameterName;

import javax.servlet.http.HttpServletRequest;

public class LanguageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession(true).setAttribute(RequestParameterName.LOCAL_NAME, request.getParameter(RequestParameterName.LOCAL_NAME));
        if (request.getSession(true).getAttribute(RequestParameterName.ADMIN) != null) {
            return JspPageName.ADMIN_PAGE;
        } else {
            return JspPageName.MAIN_PAGE;
        }
    }
}
