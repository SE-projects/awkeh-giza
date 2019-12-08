package com.model;

import javafx.collections.ObservableList;

public class CentralManager {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObservableList<RegisteredCustomerData> getFullCustomerInfo(int sortOrder){
        if(!DataSource.getInstance().open()){
            System.out.println("Couldn't open DataSource");
            return null;
        }

        ObservableList<RegisteredCustomerData> registeredCustomerData =
                DataSource.getInstance().getCustomersFullInfo(sortOrder);

        DataSource.getInstance().close();
        return registeredCustomerData;
    }
}
