package com.model.customer;

public class Cart {
    private int cartId;
    private String cartName;
    private int customerId;

    public Cart(int cartId, String cartName, int customerId) {
        this.cartId = cartId;
        this.cartName = cartName;
        this.customerId = customerId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
