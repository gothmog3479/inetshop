package ru.gothmog.dao;

import ru.gothmog.domain.Client;

import java.sql.Date;
import java.util.List;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public interface IClientDAO {

    boolean checkClient(String login, String password) throws DAOException;

    boolean thereIsClientOnBlacklist(String login, String password) throws DAOException;

    Client getClient(String login, String password) throws DAOException;

    boolean checkUniquenessOfLogin(String login) throws DAOException;

    void registration(String login, String password, String surname, String name, Date registrationDate,
                      String phone, String address, String email) throws DAOException;

    List<Client> getClientsThatAreNotIncludedInBlacklist() throws DAOException;

    List<Client> getBlacklist() throws DAOException;

    void addToBlacklict(int idClient) throws DAOException;

    void removeFromBlacklist(int idClient) throws DAOException;

    String getAddressOfClient(int idClient) throws DAOException;
}
