package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.domain.ProductCategory;
import ru.gothmog.service.IService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RecordAllCategoriesService implements IService {

    private static final Logger logger = Logger.getLogger(RecordAllCategoriesService.class);

    private static final RecordAllCategoriesService instance = new RecordAllCategoriesService();

    public static RecordAllCategoriesService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        List<ProductCategory> categoryList = null;
        IProductDAO productDAO = DAOFactory.getInstance().getProductDAO();

        try {
            categoryList = productDAO.getAllCategories();
            result = true;
        } catch (DAOException e) {
            logger.error("ProductDAO didn't return all categories. Message: " + e.getMessage());
        }

        ServletContext application = request.getServletContext();
        application.setAttribute(RequestParameterName.ALL_CATEGORIES, categoryList);
        return result;
    }
}
