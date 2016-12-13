package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IAdminDAO;
import ru.gothmog.dao.IClientDAO;
import ru.gothmog.domain.Admin;
import ru.gothmog.domain.Client;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationService implements IService {


    private static final Logger log = Logger.getLogger(AuthorizationService.class);

    private final static AuthorizationService instance = new AuthorizationService();

    public static AuthorizationService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {

        IAdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        IClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        try {
            if (adminDAO.checkAdmin(login, password)) {
                Admin admin = adminDAO.getAdmin(login, password);
                request.getSession(true).setAttribute(RequestParameterName.ADMIN, admin);
                log.info("AdminDAO checked an administrator");
                return true;
            } else if (clientDAO.checkClient(login, password)) {
                if (clientDAO.thereIsClientOnBlacklist(login, password)) {
                    request.getSession(true).setAttribute(RequestParameterName.ERROR_AUTHORIZATION, 2);
                    log.warn("Client can't log in. He is on the blacklist");
                    return false;
                } else {
                    Client client = clientDAO.getClient(login, password);
                    request.getSession(true).setAttribute(RequestParameterName.CLIENT, client);
                    log.info("ClientDAO checked a client");
                    return true;
                }
            } else {
                request.getSession(true).setAttribute(RequestParameterName.ERROR_AUTHORIZATION, 1);
                log.warn("Client made mistake when entering login or password");
                return false;
            }
        } catch (DAOException e) {
            log.error("AdminDAO or ClientDAO didn't check administrator or client respectively. Message: "
                    + e.getMessage());
        }
        return false;
    }
}
