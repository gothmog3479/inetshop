package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IClientDAO;
import ru.gothmog.domain.Client;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowBlacklistService implements IService {

    private static final Logger log = Logger.getLogger(ShowBlacklistService.class);

    private static final ShowBlacklistService instance = new ShowBlacklistService();

    public static ShowBlacklistService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {

        IClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
        List<Client> clientList = null;

        if (request.getSession(false).getAttribute(RequestParameterName.ADMIN) == null) {
            return false;
        }

        try {
            clientList = clientDAO.getBlacklist();
        } catch (DAOException e) {
            log.error("ClientDAO didn't return clients which are in the blacklist. Message: " + e.getMessage());
        }

        if (!clientList.isEmpty()) {
            request.getSession(false).setAttribute(RequestParameterName.CLIENTS, clientList);
            return true;
        } else {
            request.getSession(false).setAttribute(RequestParameterName.CLIENTS, null);
            return false;
        }
    }
}
