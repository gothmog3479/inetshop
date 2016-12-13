package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IClientDAO;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class RemoveFromBlacklistService implements IService {

    private static final Logger log = Logger.getLogger(RemoveFromBlacklistService.class);

    private static final RemoveFromBlacklistService instance = new RemoveFromBlacklistService();

    public static RemoveFromBlacklistService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {

        int id_client = 0;
        boolean result = false;
        IClientDAO clientdDAO = null;

        id_client = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
        clientdDAO = DAOFactory.getInstance().getClientDAO();

        try {
            clientdDAO.removeFromBlacklist(id_client);
            result = true;
        } catch (DAOException e) {
            log.error("ClientDAO didn't remove client from blacklist. Message: " + e.getMessage());
        }
        return result;
    }
}
