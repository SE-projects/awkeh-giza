package com.model.customer;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private LocalDate orderDate;
    private int cartId;


    public Order(int orderId, LocalDate orderDate, int cartId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cartId = cartId;
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
}
