package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;

import javax.servlet.http.HttpServletRequest;

public class RemoveFromBlacklistCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        RemoveFromBlacklistService.getInstance().doService(request);
        ShowBlacklistService.getInstance().doService(request);

        return JspPageName.BLACKLIST_PAGE;
    }
}
