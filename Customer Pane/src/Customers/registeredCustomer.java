/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customers;

import java.util.Date;

/**
 *
 * @author Hena
 */
public class registeredCustomer  {

    String firstName,lastName,email,role;
    Date date;
    int id,mobile;
    boolean confirmProductPurchase;
    public registeredCustomer (){}

     public registeredCustomer (int id,String firstName,String lastName,int mobile,String email) {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.mobile=mobile;
        this.email=email;
       
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 

    public boolean  confirmProductPurchase(){
     return confirmProductPurchase;
    }
    public String getRole(){
        return role;
    }
    
}
