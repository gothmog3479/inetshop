package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.domain.Product;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class ShowInformationAboutProductService implements IService {


    private static final Logger logger = Logger.getLogger(ShowInformationAboutProductService.class);

    private final static ShowInformationAboutProductService instance = new ShowInformationAboutProductService();

    public static ShowInformationAboutProductService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {

        Product product = null;
        int idProduct = 0;
        boolean result = false;
        IProductDAO productDAO;

        idProduct = Integer.parseInt(request.getParameter(RequestParameterName.ID_PRODUCT));
        productDAO = DAOFactory.getInstance().getProductDAO();

        try {
            product = productDAO.getProduct(idProduct);
        } catch (DAOException e) {
            logger.error("ProductDAO didn't return a product. Message: " + e.getMessage());
        }

        if (product != null) {
            request.setAttribute(RequestParameterName.PRODUCT, product);
            result = true;
        } else {
            request.setAttribute(RequestParameterName.PRODUCT, null);
        }
        return result;
    }
}
