package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.domain.Product;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class AddNewProductService implements IService {

    private static final Logger log = Logger.getLogger(AddNewProductService.class);

    private static final AddNewProductService instance = new AddNewProductService();

    public static AddNewProductService getInstance() {
        return instance;
    }
    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = true;
        Product product = new Product();
        String idCategory = null;
        String nameProduct = null;
        String costOfProduct = null;
        String quantityOfProduct = null;
        IProductDAO productDAO = null;

        idCategory = request.getParameter(RequestParameterName.ID_CATEGORY);
        product.setIdCategory(Integer.parseInt(idCategory));

        nameProduct = request.getParameter(RequestParameterName.NAME_PRODUCT);
        if (nameProduct.equals("")) {
            log.warn("Didn't enter the name of product ");
            request.setAttribute(RequestParameterName.ERROR_ADD_OR_EDIT_PRODUCT, 1);
            return false;
        }
        product.setName(nameProduct);

        costOfProduct = request.getParameter(RequestParameterName.COST_PRODUCT);
        if (costOfProduct.equals("")) {
            log.warn("Didn't enter the cost of product");
            request.setAttribute(RequestParameterName.ERROR_ADD_OR_EDIT_PRODUCT, 2);
            return false;
        }
        try {
            if (Integer.parseInt(costOfProduct) < 0) {
                log.warn("In field of cost of product have entered negative number");
                request.setAttribute(RequestParameterName.ERROR_ADD_OR_EDIT_PRODUCT, 3);
                return false;
            }
            product.setPrice(Integer.parseInt(costOfProduct));
        } catch (Exception e) {
            log.warn("In field of cost of product have entered letters");
            request.setAttribute(RequestParameterName.ERROR_ADD_OR_EDIT_PRODUCT, 5);
            return false;
        }

        quantityOfProduct = request.getParameter(RequestParameterName.QUANTITY_OF_PRODUCTS);
        if (quantityOfProduct.equals("")) {
            product.setQuantityInStock(0);
        } else {
            try {
                if (Integer.parseInt(quantityOfProduct) < 0) {
                    log.warn("In field of number of products have entered negative number");
                    request.setAttribute(RequestParameterName.ERROR_ADD_OR_EDIT_PRODUCT, 4);
                    return false;
                }
                product.setQuantityInStock(Integer.parseInt(quantityOfProduct));
            } catch (Exception e) {
                log.warn("In field of number of products have entered letters");
                request.setAttribute(RequestParameterName.ERROR_ADD_OR_EDIT_PRODUCT, 6);
                return false;
            }
        }

        if (result) {
            productDAO = DAOFactory.getInstance().getProductDAO();
            try {
                productDAO.addNewProduct(product);
            } catch (DAOException e) {
                log.error("ProductDAO didn't add a new product. Message: " + e.getMessage());
            }
        }
        return result;
    }
}
