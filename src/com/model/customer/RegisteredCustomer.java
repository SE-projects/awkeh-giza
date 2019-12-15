package com.model.customer;

import javafx.collections.ObservableList;

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

    public void deleteCustomer(String userName, String password){
        if(!cq.establishConnection()){
            System.out.println("Couldn't open connection");
            return;
        }
        cq.deleteRegisteredCustomer(userName, password);
        cq.closeConnection();
    }
    //for updating
    public void updateCustomerInfo(String oldUsername, String oldPassword, String newUsername, String newPassword,
                                   String phone, String email, String streetName, int homeNumber, String subCity,
                                   String city){
       if(!cq.establishConnection()){
           System.out.println("Couldn't establish connection");
           return;
       }

       cq.updateRegisteredCustomerInfo(oldUsername, oldPassword, newUsername, newPassword, phone, email, streetName,
               homeNumber, subCity, city);

       cq.closeConnection();
    }

    public int createACart(String cartName, int customerId){
        if(!cq.establishConnection()){
            System.out.println("Couldn't establish connection");
            return -1;
        }

        int cartId = cq.createACart(cartName, customerId);

        cq.closeConnection();

        return cartId;
    }
    public void addProductToCart(int productId, String productName, double price,
                                 int quantity, double totalAmount, String cartName, int customerId){
        if(!cq.establishConnection()){
            System.out.println("Couldn't establish connection");
            return;
        }

        cq.addProductToCart(productId, productName, price, quantity, totalAmount, cartName, customerId);

        cq.closeConnection();
    }
    public void removeProductFromProduct(CartProduct cartProduct){
        if(!cq.establishConnection()){
            System.out.println("Couldn't establish connection");
            return;
        }

        cq.removeProductFromCart(cartProduct);

        cq.closeConnection();
    }
    public ObservableList<ProductInCart> viewCartContents(Cart cart, int sortOrder){
        if(!cq.establishConnection()){
            System.out.println("Couldn't establish connection");
            return null;
        }

        ObservableList<ProductInCart> productsInCart = cq.viewCartContents(cart, sortOrder);


        cq.closeConnection();
        return productsInCart;
    }
    public void updateProductInCart(CartProduct cartProduct){
        if(!cq.establishConnection()){
            System.out.println("Couldn't establish connection");
            return;
        }

        cq.updateProductInCart(cartProduct);

        cq.closeConnection();
    }
    public boolean orderCart(Cart cart){
        if(!cq.establishConnection()){
            System.out.println("Couldn't establish connection");
            return false;
        }

        cq.orderCart(cart);

        cq.closeConnection();
        return true;
    }
    public boolean cancelOrder(Order order){
        if(!cq.establishConnection()){
            System.out.println("Couldn't establish connection");
            return false;
        }

        cq.cancelOrder(order);

        return true;
    }

}
