package com.model.customer;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private LocalDate orderDate;
    private int cartId;
    private int customer_id;


    public Order(int orderId, LocalDate orderDate, int cartId, int customer_id) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cartId = cartId;
        this.customer_id = customer_id;
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
}
