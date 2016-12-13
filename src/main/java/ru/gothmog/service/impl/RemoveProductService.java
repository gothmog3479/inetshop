package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class RemoveProductService implements IService {

    private static final Logger log = Logger.getLogger(RemoveProductService.class);

    private static final RemoveProductService instance = new RemoveProductService();

    public static RemoveProductService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        int idProduct = 0;
        IProductDAO productDAO = null;

        idProduct = Integer.parseInt(request.getParameter(RequestParameterName.ID_PRODUCT));
        productDAO = DAOFactory.getInstance().getProductDAO();
        try {
            productDAO.removeProduct(idProduct);
            result = true;
        } catch (DAOException e) {
            log.error("ProductDAO didn't remove product. Message: " + e.getMessage());
        }
        return result;
    }
}
