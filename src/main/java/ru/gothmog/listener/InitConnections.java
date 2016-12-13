package ru.gothmog.listener;

import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.dao.settings.ConnectionPool;
import ru.gothmog.dao.settings.ConnectionPoolException;
import ru.gothmog.domain.ProductCategory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
@WebListener
public class InitConnections implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        List<ProductCategory> categoryList = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        IProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
        try {
            categoryList = productDAO.getAllCategories();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        ServletContext application = contextEvent.getServletContext();
        application.setAttribute("allCategories", categoryList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
