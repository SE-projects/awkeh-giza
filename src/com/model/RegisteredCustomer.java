package com.model;

public class RegisteredCustomer {
    private int id;
    private String userName;
    private String password;
//    private int customerDataId;
    private String email;
    private String streetName;
    private int homeNumber;
    private String subCity;
    private String city;
//    private int customerId;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getSubCity() {
        return subCity;
    }

    public void setSubCity(String subCity) {
        this.subCity = subCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void deleteCustomer(String userName, String password){
        if(!DataSource.getInstance().open()){
            System.out.println("Couldn't connect to dataSource");
            return;
        }

        DataSource.getInstance().deleteRegisteredCustomer(userName, password);

        DataSource.getInstance().close();
    }

    public void updateCustomerInfo(String username, String password,
                                   String email, String streetName, int homeNumber, String subCity,
                                   String city){

    }
}
