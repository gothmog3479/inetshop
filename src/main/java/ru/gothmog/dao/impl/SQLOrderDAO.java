package ru.gothmog.dao.impl;

import org.apache.log4j.Logger;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.IOrderDAO;
import ru.gothmog.dao.settings.ConnectionPool;
import ru.gothmog.dao.settings.ConnectionPoolException;
import ru.gothmog.domain.Client;
import ru.gothmog.domain.Order;
import ru.gothmog.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLOrderDAO implements IOrderDAO {

    private static final String ORDER1_SQL = "INSERT INTO inetshop.orders (id_client, address, order_date, status, amount) VALUES (?,?,?,?,?)";
    private static final String GET_LAST_ID_ORDER_SQL = "SELECT id_order FROM inetshop.orders ORDER BY id_order DESC LIMIT 1";
    private static final String ORDER2_SQL = "INSERT INTO inetshop.order_has_product (id_order, id_product, count) VALUES (?,?,?)";
    private static final String GET_ALL_ORDERS_OF_ONE_CLIENT_SQL = "SELECT * FROM inetshop.orders WHERE id_client=?";
    private static final String CANCEL1_ORDER_SQL = "DELETE FROM inetshop.order_has_product WHERE id_order=?";
    private static final String CANCEL2_ORDER_SQL = "DELETE FROM inetshop.orders WHERE id_order=?";

    private static final String INITIAL_ORDER_STATUS = "В процессе";

    private static final Logger log = Logger.getLogger(SQLOrderDAO.class);

    private static final SQLOrderDAO instance = new SQLOrderDAO();

    public static SQLOrderDAO getInstance() {
        return instance;
    }

    @SuppressWarnings("resource")
    @Override
    public void addOrder(Client client, Product product, int numberOfInstances) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int idOrder = 0;
        double amount = numberOfInstances * product.getPrice();
        Date orderDate = new Date(System.currentTimeMillis());

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ORDER1_SQL);
            statement.setLong(1, client.getId());
            statement.setString(2, client.getAddress());
            statement.setDate(3, orderDate);
            statement.setString(4, INITIAL_ORDER_STATUS);
            statement.setDouble(5, amount);
            statement.executeUpdate();

            statement = connection.prepareStatement(GET_LAST_ID_ORDER_SQL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                idOrder = resultSet.getInt(1);
            }

            statement = connection.prepareStatement(ORDER2_SQL);
            statement.setInt(1, idOrder);
            statement.setLong(2, product.getId());
            statement.setInt(3, numberOfInstances);
            statement.executeUpdate();

        } catch (ConnectionPoolException e) {
            log.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLOrderDAO");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Order> getAllOrdersOfOneClient(int id_client) throws DAOException {
        List<Order> orderList = new ArrayList<Order>();
        Order order = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(GET_ALL_ORDERS_OF_ONE_CLIENT_SQL);
            statement.setInt(1, id_client);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                order = new Order();
                order.setIdOrder(resultSet.getInt(1));
                order.setIdClient(resultSet.getInt(2));
                order.setAddress(resultSet.getString(3));
                order.setOrderDate(resultSet.getDate(4));
                order.setStatus(resultSet.getString(5));
                order.setAmount(resultSet.getInt(6));
                orderList.add(order);
            }
        } catch (ConnectionPoolException e) {
            log.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLOrderDAO");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
        return orderList;
    }

    @SuppressWarnings("resource")
    @Override
    public void cancelOrder(int idOrder) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(CANCEL1_ORDER_SQL);
            statement.setInt(1, idOrder);
            statement.executeUpdate();
            statement = connection.prepareStatement(CANCEL2_ORDER_SQL);
            statement.setInt(1, idOrder);
            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            log.error("ConnectionPool didn't take connection. Message: " + e.getMessage());
        } catch (SQLException e) {
            throw new DAOException("SQLException in SQLOrderDAO");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.error("Statement didn't close. Message: " + e.getMessage());
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Connection didn't close. Message: " + e.getMessage());
            }
        }
    }
}
