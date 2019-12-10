/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customers.Controllers;

import java.util.Date;

/**
 *
 * @author Hayyu
 */
public class newCustomer implements Customer {
   
    String username, password;
    Product info, search;
    String role;
    public String getRole(){
        return role;
    }

    public void register(String username,String password){
        this.username=username;
        this.password=password;
        
    }
    @Override
    public Product getProductInfo() {
        return info;
    }

    @Override
    public Product searchProductInfo() {
        return search;
    }
    
    
}