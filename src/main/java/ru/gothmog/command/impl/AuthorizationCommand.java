package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.service.impl.AuthorizationService;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {


        boolean result = false;
        String page = null;
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        if ((login != null) & (password != null)) {
            AuthorizationService authorization = AuthorizationService.getInstance();
            result = authorization.doService(request);
            if (result) {
                if (request.getSession(true).getAttribute(RequestParameterName.ADMIN) != null) {
                    page = JspPageName.ADMIN_PAGE;
                } else if (request.getSession(true).getAttribute(RequestParameterName.CLIENT) != null) {
                    page = JspPageName.MAIN_PAGE;
                }
            } else {
                page = JspPageName.AUTHORIZATION_PAGE;
            }
        }
        return page;
    }
}
