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

public class ShowCorrectClientsService implements IService {


    private static final Logger logger = Logger.getLogger(ShowCorrectClientsService.class);

    private static final ShowCorrectClientsService instance = new ShowCorrectClientsService();

    public static ShowCorrectClientsService getInstance() {
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
            clientList = clientDAO.getClientsThatAreNotIncludedInBlacklist();
        } catch (DAOException e) {
            logger.error("ClientDAO didn't return clients which aren't included in the blacklist. Message: " + e.getMessage());
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
