package ru.gothmog.dao;

import ru.gothmog.domain.Admin;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public interface IAdminDAO {

    boolean checkAdmin(String login, String password) throws DAOException;

    Admin getAdmin(String login, String password) throws DAOException;
}
