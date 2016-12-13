package ru.gothmog.dao;

import ru.gothmog.dao.impl.SQLAdminDAO;
import ru.gothmog.dao.impl.SQLClientDAO;
import ru.gothmog.dao.impl.SQLOrderDAO;
import ru.gothmog.dao.impl.SQLProductDAO;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance() {
        return instance;
    }

    public IAdminDAO getAdminDAO(){
        return SQLAdminDAO.getInstance();
    }

    public IClientDAO getClientDAO(){
        return SQLClientDAO.getInstance();
    }

    public IProductDAO getProductDAO(){
        return SQLProductDAO.getInstance();
    }

    public IOrderDAO getOrderDAO(){
        return SQLOrderDAO.getInstance();
    }

}
