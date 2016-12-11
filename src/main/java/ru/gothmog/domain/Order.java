package ru.gothmog.domain;

import java.sql.Date;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public class Order {

    private long idOrder;
    private long idClient;
    private String address;
    private Date orderDate;
    private String status;
    private double amount;

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Order order = (Order) obj;

        if (idOrder != order.idOrder) return false;
        if (idClient != order.idClient) return false;
        if (Double.compare(order.amount, amount) != 0) return false;
        if (!address.equals(order.address)) return false;
        if (!orderDate.equals(order.orderDate)) return false;
        return status.equals(order.status);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idOrder ^ (idOrder >>> 32));
        result = 31 * result + (int) (idClient ^ (idClient >>> 32));
        result = 31 * result + address.hashCode();
        result = 31 * result + orderDate.hashCode();
        result = 31 * result + status.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idClient=" + idClient +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                '}';
    }
}
