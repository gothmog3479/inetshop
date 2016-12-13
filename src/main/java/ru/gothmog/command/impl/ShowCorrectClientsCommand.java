package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;

import javax.servlet.http.HttpServletRequest;

public class ShowCorrectClientsCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        boolean result = false;
        String page = null;

        result = ShowCorrectClientsService.getInstance().doService(request);

        if (result) {
            page = JspPageName.ALL_CLIENTS_PAGE;
        }
        return page;
    }
}
