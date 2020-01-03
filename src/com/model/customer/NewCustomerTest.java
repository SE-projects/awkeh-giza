package com.model.customer;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewCustomerTest {

    NewCustomer newCustomer = new NewCustomer();

    @Test
    public void register() {
        newCustomer.register("Joel", "Stephens", "JoelZ",
                "hfgt@fg", "95674554", "joel@gmail.com",
                "Megenegna", 546, "Yeka", "A.A");
    }

    @Test
    public void closeConnection() {
        newCustomer.closeConnection();
    }
}