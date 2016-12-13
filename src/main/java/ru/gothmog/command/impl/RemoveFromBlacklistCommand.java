package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.RemoveFromBlacklistService;
import ru.gothmog.service.impl.ShowBlacklistService;

import javax.servlet.http.HttpServletRequest;

public class RemoveFromBlacklistCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        RemoveFromBlacklistService.getInstance().doService(request);
        ShowBlacklistService.getInstance().doService(request);

        return JspPageName.BLACKLIST_PAGE;
    }
}
