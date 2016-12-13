package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.ShowBlacklistService;

import javax.servlet.http.HttpServletRequest;

public class ShowBlacklistCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        boolean result = false;
        String page = null;

        result = ShowBlacklistService.getInstance().doService(request);

        if (result) {
            page = JspPageName.BLACKLIST_PAGE;
        }
        return page;
    }
}
