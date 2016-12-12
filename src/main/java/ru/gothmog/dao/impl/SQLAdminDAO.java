package ru.gothmog.dao.impl;

import org.apache.log4j.Logger;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.IAdminDAO;
import ru.gothmog.dao.settings.ConnectionPool;
import ru.gothmog.dao.settings.ConnectionPoolException;
import ru.gothmog.domain.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLAdminDAO implements IAdminDAO{

    private static final String LOGIN_PASSWORD_CHECK_SQL = "SELECT id_administrator FROM inetshop.administrator WHERE inetshop.administrator.login=? AND inetshop.administrator.passwd=?";
    private static final String GET_ADMIN_DATA_SQL = "SELECT * FROM inetshop.administrator WHERE login=? AND passwd=?";

    private static final Logger log = Logger.getLogger(SQLAdminDAO.class);

    private final static SQLAdminDAO instance = new SQLAdminDAO();

    public static IAdminDAO getInstance() {
        return instance;
    }

    @Override
    public boolean checkAdmin(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(LOGIN_PASSWORD_CHECK_SQL);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            throw new DAOException("SQLException in SQLAdminDAO");
        } catch (ConnectionPoolException ex) {
            log.error("ConnectionPool didn't take connection. Message: " + ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    log.error("Statement didn't close. Message: " + ex.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Connection didn't close. Message: " + ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public Admin getAdmin(String login, String password) throws DAOException {
        Admin admin = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(GET_ADMIN_DATA_SQL);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getInt(1));
                admin.setLogin(resultSet.getString(2));
                admin.setPassword(resultSet.getString(3));
            }
        } catch (SQLException ex) {
            throw new DAOException("SQLException in SQLAdminDAO");
        } catch (ConnectionPoolException ex) {
            log.error("ConnectionPool didn't take connection. Message: " + ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    log.error("Statement didn't close. Message: " + ex.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Connection didn't close. Message: " + ex.getMessage());
            }
        }
        return admin;
    }
}
