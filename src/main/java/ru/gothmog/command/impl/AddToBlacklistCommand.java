package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;
import ru.gothmog.service.impl.AddToBlacklistService;
import ru.gothmog.service.impl.ShowCorrectClientsService;

import javax.servlet.http.HttpServletRequest;

public class AddToBlacklistCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        AddToBlacklistService.getInstance().doService(request);
        ShowCorrectClientsService.getInstance().doService(request);

        return JspPageName.ALL_CLIENTS_PAGE;

    }
}
