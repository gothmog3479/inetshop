package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IOrderDAO;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.domain.Client;
import ru.gothmog.domain.Order;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ShowOrdersOfOneClientService implements IService {


    private static final Logger logger = Logger.getLogger(ShowOrdersOfOneClientService.class);

    private final static ShowOrdersOfOneClientService instance = new ShowOrdersOfOneClientService();

    public static ShowOrdersOfOneClientService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {

        boolean result = false;
        int idClient = 0;
        int idClientInSession = 0;
        IOrderDAO orderDAO = null;
        IProductDAO productDAO = null;
        List<Order> orderList = null;
        Map<Long, List<Object>> dataOfOrdersOfOneClient = null;

        idClient = Integer.parseInt(request.getParameter(RequestParameterName.ID_CLIENT));
        idClientInSession = Math.toIntExact(((Client) request.getSession().getAttribute(RequestParameterName.CLIENT)).getId());
        System.out.println("1)" + idClient);
        System.out.println("2)" + idClientInSession);

        if (idClient != idClientInSession) {
            return false;
        }

        orderDAO = DAOFactory.getInstance().getOrderDAO();
        productDAO = DAOFactory.getInstance().getProductDAO();
        try {
            orderList = orderDAO.getAllOrdersOfOneClient(idClient);
            dataOfOrdersOfOneClient = productDAO.getDataOfAllOrdersOfOneClient(orderList);
            request.setAttribute(RequestParameterName.ALL_ORDERS_OF_ONE_CLIENT, dataOfOrdersOfOneClient);
            result = true;
        } catch (DAOException e) {
            logger.error(
                    "OrderDAO didn't return all orders of one client or ProductDAO didn't return data of orders of one client. Message: "
                            + e.getMessage());
        }
        return result;
    }
}
