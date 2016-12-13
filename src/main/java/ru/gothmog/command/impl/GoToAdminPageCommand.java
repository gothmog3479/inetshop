package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.controller.RequestParameterName;

import javax.servlet.http.HttpServletRequest;

public class GoToAdminPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        if (request.getSession().getAttribute(RequestParameterName.ADMIN) != null) {
            page = JspPageName.ADMIN_PAGE;
        }
        return page;
    }
}
