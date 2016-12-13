package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IClientDAO;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationService implements IService {

    private static final Logger logger = Logger.getLogger(RegistrationService.class);

    private static final RegistrationService instance = new RegistrationService();

    public static RegistrationService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {

        String login;
        String password;
        String passwordAgain;
        String surname;
        String name;
        Date registrationDate;
        String phone;
        String address;
        String email;
        boolean result = false;

        login = request.getParameter(RequestParameterName.LOGIN);
        password = request.getParameter(RequestParameterName.PASSWORD);
        passwordAgain = request.getParameter(RequestParameterName.PASSWORD_AGAIN);
        surname = request.getParameter(RequestParameterName.SURNAME);
        name = request.getParameter(RequestParameterName.NAME);
        registrationDate = new Date(System.currentTimeMillis());
        phone = request.getParameter(RequestParameterName.PHONE);
        address = request.getParameter(RequestParameterName.ADDRESS);
        email = request.getParameter(RequestParameterName.EMAIL);

        IClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();

        if (!password.equals(passwordAgain)) {
            logger.warn("Passwords entered by the client are not identical");
            request.setAttribute(RequestParameterName.ERROR_REGISTRATION, 1);
            return false;
        }

        try {
            if (!clientDAO.checkUniquenessOfLogin(login)) {
                logger.warn("User with entered login is already registered");
                request.setAttribute(RequestParameterName.ERROR_REGISTRATION, 2);
                return false;
            }
        } catch (DAOException e) {
            logger.error("ClientDAO didn't check uniqueness of login of client. Message: " + e.getMessage());
        }

        if ((login.equals("")) || (password.equals("")) || (surname.equals("")) || (name.equals("")) || (phone.equals(""))) {
            logger.warn("Client didn't fill in all required fields for registration");
            request.setAttribute(RequestParameterName.ERROR_REGISTRATION, 3);
            return false;
        }

        Pattern p = Pattern.compile("[\\d\\s()\\-\\+]+");
        Matcher matcher = p.matcher(phone);
        if (!matcher.matches()) {
            logger.warn("In field of telephone number have entered letters");
            request.setAttribute(RequestParameterName.ERROR_REGISTRATION, 4);
            return false;
        }

        try {
            clientDAO.registration(login, password, surname, name, registrationDate, phone, address, email);
            result = true;
        } catch (DAOException e) {
            logger.error("ClientDAO didn't register client. Message: " + e.getMessage());
        }
        return result;
    }
}
