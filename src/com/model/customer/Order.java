package com.model.customer;

import java.time.LocalDate;
//TODO order should have a total amount column
//TODO order should have a an order status as well
public class Order {
    private int orderId;
    private LocalDate orderDate;
    private int cartId;
    private int customer_id;
    private String orderStatus;
    private double totalAmount;

    public Order(int orderId, LocalDate orderDate, int cartId,
                 int customer_id, String orderStatus, double totalAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cartId = cartId;
        this.customer_id = customer_id;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
