package com.model.customer;

import javafx.collections.ObservableList;

//TODO database connection shouldn't be closed and opened for every method
// This kinds of things should be handled when the GUI has been built
public class RegisteredCustomer {
    private int id;
    private String userName;
    private String password;

    private CustomerQueries cq = new CustomerQueries();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void deleteCustomer(String userName, String password) {
        cq.deleteRegisteredCustomer(userName, password);
        cq.closeConnection();
    }

    //for updating
    public void updateCustomerInfo(String oldUsername, String oldPassword, String newUsername, String newPassword,
                                   String phone, String email, String streetName, int homeNumber, String subCity,
                                   String city) {
        cq.updateRegisteredCustomerInfo(oldUsername, oldPassword, newUsername, newPassword, phone, email, streetName,
                homeNumber, subCity, city);

        cq.closeConnection();
    }

    public int createACart(String cartName, int customerId) {
        cq.closeConnection();

        return cq.createACart(cartName, customerId);
    }

    public void addProductToCart(int productId, String productName, double price,
                                 int quantity, double totalAmount, String cartName, int customerId) {

        cq.addProductToCart(productId, productName, price, quantity, totalAmount, cartName, customerId);
    }

    public void removeProductFromCart(CartProduct cartProduct) {
        cq.removeProductFromCart(cartProduct);
    }

    public ObservableList<ProductInCart> viewCartContents(Cart cart, int sortOrder) {
        return cq.viewCartContents(cart, sortOrder);
    }

    public void updateProductInCart(CartProduct cartProduct) {
        cq.updateProductInCart(cartProduct);
    }

    public boolean orderCart(Cart cart) {
        cq.orderCart(cart);
        return true;
    }

    public boolean cancelOrder(Order order) {
        cq.cancelOrder(order);
        return true;
    }

    public void closeConnection() {
        cq.closeConnection();
    }

}
