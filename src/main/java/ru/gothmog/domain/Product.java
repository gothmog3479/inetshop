package ru.gothmog.domain;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public class Product {

    private long id;
    private long idCategory;
    private String name;
    private double price;
    private int quantityInStock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;

        if (id != product.id) return false;
        if (idCategory != product.idCategory) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (quantityInStock != product.quantityInStock) return false;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (idCategory ^ (idCategory >>> 32));
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantityInStock;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                '}';
    }
}
