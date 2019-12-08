package com.model;

public class RegisteredCustomer {
    private int id;
    private String userName;
    private String password;

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
        if(!DataSource.getInstance().open()){
            System.out.println("Couldn't connect to dataSource");
            return;
        }

        DataSource.getInstance().deleteRegisteredCustomer(userName, password);

        DataSource.getInstance().close();
    }
    //for updating
    public void updateCustomerInfo(String oldUsername, String oldPassword, String newUsername, String newPassword,
                                   String phone, String email, String streetName, int homeNumber, String subCity,
                                   String city){
        if(!DataSource.getInstance().open()){
            System.out.println("Couldn't open DataSource");
            return;
        }

        DataSource.getInstance().updateRegisteredCustomerInfo(oldUsername, oldPassword, newUsername, newPassword, phone,
                email, streetName, homeNumber, subCity, city);

        DataSource.getInstance().close();
    }

}
