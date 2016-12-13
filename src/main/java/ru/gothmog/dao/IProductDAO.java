package ru.gothmog.dao;

import ru.gothmog.domain.Order;
import ru.gothmog.domain.Product;
import ru.gothmog.domain.ProductCategory;

import java.util.List;
import java.util.Map;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public interface IProductDAO {

    List<ProductCategory> getAllCategories() throws DAOException;

    List<Product> getProductsOfCategory(int idCategory) throws DAOException;

    Product getProduct(int idProduct) throws DAOException;

    void addNewCategory(String nameCategory) throws DAOException;

    void addNewProduct(Product product) throws DAOException;

    void editProduct(Product product) throws DAOException;

    void removeProduct(int idProduct) throws DAOException;

    void updateQuantityOfProductsInStock(int idProduct, int newValueOfQuantity) throws DAOException;

    Map<Long, List<Object>> getDataOfAllOrdersOfOneClient(List<Order> orderList) throws DAOException;

}
