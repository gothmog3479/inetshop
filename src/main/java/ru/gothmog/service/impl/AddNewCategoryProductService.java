package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class AddNewCategoryProductService implements IService {

    private static final Logger log = Logger.getLogger(AddNewCategoryProductService.class);

    private static final AddNewCategoryProductService instance = new AddNewCategoryProductService();

    public static AddNewCategoryProductService getInstance() {
        return instance;
    }
    @Override
    public boolean doService(HttpServletRequest request) {
        String nameCategory = null;
        boolean result = false;
        IProductDAO productDAO = null;

        nameCategory = request.getParameter(RequestParameterName.NAME_CATEGORY);
        if (!nameCategory.isEmpty()) {
            productDAO = DAOFactory.getInstance().getProductDAO();
            try {
                productDAO.addNewCategory(nameCategory);
                result = true;
            } catch (DAOException e) {
                log.error("ProductDAO didn't add a new category of products. Message: "+e.getMessage());
            }
        } else {
            log.warn("Didn't enter the name of category ");
            request.getSession(true).setAttribute(RequestParameterName.ERROR_ADD_CATEGORY, 1);
        }
        return result;
    }
}
