package ru.gothmog.controller;

import ru.gothmog.command.CommandName;
import ru.gothmog.command.ICommand;
import ru.gothmog.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class ControllerHelper {

    private static final ControllerHelper instance = new ControllerHelper();
    private Map<CommandName, ICommand> commands = new HashMap<>();

    public ControllerHelper() {
        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.ADD_NEW_CATEGORY_PRODUCT, new AddNewCategoryProductCommand());
        commands.put(CommandName.ADD_NEW_PRODUCT, new AddNewPoductCommand());
        commands.put(CommandName.ORDER, new OrderCommand());
        commands.put(CommandName.ADD_TO_BLACKLIST, new AddToBlacklistCommand());
        commands.put(CommandName.REMOVE_FROM_BLACKLIST, new RemoveFromBlacklistCommand());
        commands.put(CommandName.EDIT_PRODUCT, new EditProductCommand());
        commands.put(CommandName.REMOVE_PRODUCT, new RemoveProductCommand());
        commands.put(CommandName.CANCEL_ORDER, new CancelOrderCommand());
        commands.put(CommandName.LOG_OUT, new LogOutCommand());
        commands.put(CommandName.CHOOSE_LANGUAGE, new LanguageCommand());
        commands.put(CommandName.SHOW_CORRECT_CLIENTS, new ShowCorrectClientsCommand());
        commands.put(CommandName.SHOW_BLACKLIST, new ShowBlacklistCommand());
        commands.put(CommandName.SHOW_INFORMATION_ABOUT_PRODUCT, new ShowInformationAboutProductCommand());
        commands.put(CommandName.GO_ADD_PRODUCT, new GoAddProductCommand());
        commands.put(CommandName.GO_EDIT_PRODUCT, new GoEditProductCommand());
        commands.put(CommandName.SHOW_ORDERS, new ShowOrdersOfOneClientCommand());
        commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
    }

    public static ControllerHelper getInstance() {
        return instance;
    }

    public ICommand getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        ICommand command;
        if (name != null) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }
}
