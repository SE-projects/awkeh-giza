package com.model.customer;

import com.model.customer.CustomerQueries;

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

}
