package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IOrderDAO;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderService implements IService {


    private static final Logger log = Logger.getLogger(CancelOrderService.class);

    private static final CancelOrderService instance = new CancelOrderService();

    public static CancelOrderService getInstance() {
        return instance;
    }
    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        int idOrder = 0;
        int idProduct = 0;
        int quantityOfProductsInOrder = 0;
        int quantityOfProductsInStock = 0;
        IOrderDAO orderDAO = null;
        IProductDAO productDAO = null;

        idOrder = Integer.parseInt(request.getParameter(RequestParameterName.ID_ORDER));
        idProduct = Integer.parseInt(request.getParameter(RequestParameterName.ID_PRODUCT));
        quantityOfProductsInOrder = Integer.parseInt(request.getParameter(RequestParameterName.QUANTITY_OF_PRODUCTS_IN_ORDER));
        quantityOfProductsInStock = Integer.parseInt(request.getParameter(RequestParameterName.QUANTITY_OF_PRODUCTS_IN_STOCK));

        orderDAO = DAOFactory.getInstance().getOrderDAO();
        productDAO = DAOFactory.getInstance().getProductDAO();
        try {
            orderDAO.cancelOrder(idOrder);
            productDAO.updateQuantityOfProductsInStock(idProduct, quantityOfProductsInStock + quantityOfProductsInOrder);
            result = true;
        } catch (DAOException e) {
            log.error("ProductDAO didn't cancel order or ProductDAO didn't update quantity of products in the stock. Message: "+ e.getMessage());
        }
        return result;
    }
}
