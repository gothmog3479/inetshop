package ru.gothmog.jsptag;

import org.apache.log4j.Logger;
import ru.gothmog.dao.DAOException;
import ru.gothmog.dao.DAOFactory;
import ru.gothmog.dao.IProductDAO;
import ru.gothmog.domain.Product;
import ru.gothmog.domain.ProductCategory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class SpecialJSPTag extends TagSupport {

    private ProductCategory category;

    private static final Logger log = Logger.getLogger(SpecialJSPTag.class);

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        List<Product> productList = null;
        IProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
        try {
            productList = productDAO.getProductsOfCategory((int)category.getId());
        } catch (DAOException e) {
            log.error("ProductDAO didn't return products of category. Message: " + e.getMessage());
        }
        try {
            for (Product product : productList) {
                out.write("<tr>");
                out.write("<td> </td>");
                out.write("<td><font size=\"+1\">");
                out.write("<a href=\"controller?command=show_information_about_product&id_product=" + product.getId() + "\"> " + product.getName() + "</a> <br />");
                out.write("</font></td>");
                out.write("</tr>");
            }
        } catch (IOException e) {
            log.error("Instance of JspWriter threw IOException. Message: " + e.getMessage());
        }
        return SKIP_BODY;
    }
}
