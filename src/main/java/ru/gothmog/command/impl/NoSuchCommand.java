package ru.gothmog.command.impl;

import ru.gothmog.command.CommandException;
import ru.gothmog.command.ICommand;
import ru.gothmog.controller.JspPageName;

import javax.servlet.http.HttpServletRequest;

public class NoSuchCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return JspPageName.ERROR_PAGE;
    }
}
