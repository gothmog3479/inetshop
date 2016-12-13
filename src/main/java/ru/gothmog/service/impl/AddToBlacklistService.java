package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IClientDAO;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class AddToBlacklistService implements IService {

    private static final Logger log = Logger.getLogger(AddToBlacklistService.class);

    private static final AddToBlacklistService instance = new AddToBlacklistService();

    public static AddToBlacklistService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {

        int idClient = 0;
        boolean result = false;
        IClientDAO clientDAO;

        idClient = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
        clientDAO = DAOFactory.getInstance().getClientDAO();

        try {
            clientDAO.addToBlacklict(idClient);
            result = true;
        } catch (DAOException e) {
            log.error("ClientDAO didn't client to blacklist. Message: " + e.getMessage());
        }
        return result;
    }
}
