package com.model.customer;

public class Cart {
    private int cartId;
    private String cartName;

    public Cart(int cartId, String cartName) {
        this.cartId = cartId;
        this.cartName = cartName;
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
}
