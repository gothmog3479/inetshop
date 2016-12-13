package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.domain.Product;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class GoEditProductService implements IService {

    private static final Logger log = Logger.getLogger(GoEditProductService.class);

    private static final GoEditProductService instance = new GoEditProductService();

    public static GoEditProductService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        int idProduct = 0;
        Product product = null;
        IProductDAO productDAO = null;

        idProduct = Integer.parseInt(request.getParameter(RequestParameterName.ID_PRODUCT));
        productDAO = DAOFactory.getInstance().getProductDAO();
        try {
            product = productDAO.getProduct(idProduct);
        } catch (DAOException e) {
            log.error("ProductDAO didn't return a product. Message: " + e.getMessage());
        }

        if (product != null) {
            request.setAttribute(RequestParameterName.PRODUCT, product);
            result = true;
        }
        return result;
    }
}
