package ru.gothmog.dao;

import ru.gothmog.domain.Client;
import ru.gothmog.domain.Order;
import ru.gothmog.domain.Product;

import java.util.List;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public interface IOrderDAO {

    void addOrder(Client client, Product product, int numberOfInstances) throws DAOException;

    List<Order> getAllOrdersOfOneClient(int id_client) throws DAOException;

    void cancelOrder(int idOrder) throws DAOException;
}
