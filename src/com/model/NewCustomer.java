package com.model;

public class NewCustomer {

    public static void register(String username, String password, String phone,
                         String email, String streetName, int homeNumber, String subCity,
                         String city){
        if(!DataSource.getInstance().open()){
            System.out.println("Couldn't open dataSource");
            return;
        }

        DataSource.getInstance().insertNewlyRegisteredCustomer(username, password, phone, email, streetName, homeNumber,
                subCity, city);

        DataSource.getInstance().close();
    }
}
