package com.model.customer;

import com.model.customer.CustomerQueries;

public class NewCustomer {
    private CustomerQueries cq = new CustomerQueries();

    //TODO the field have changed here
    public void register(String firstName, String lastName, String username, String password, String phone,
                         String email, String streetName, int homeNumber, String subCity,
                         String city) {

        cq.insertNewlyRegisteredCustomer(username, password, firstName, lastName, phone, email, streetName, homeNumber,
                subCity, city);

    }

    public void closeConnection() {
        cq.closeConnection();
    }
}
