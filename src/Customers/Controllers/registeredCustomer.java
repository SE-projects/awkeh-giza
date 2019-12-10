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
public class registeredCustomer implements Customer {

    String name,role,address,gender,paymentDetail;
    int id,age;
    boolean confirmProductPurchase;
    public registeredCustomer (){}

    Product info, search;
     public registeredCustomer (int id,String name,int age, String gender, String address, String paymentDetail) {
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.address=address;
        this.paymentDetail=paymentDetail;
        this.age=age;
       
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(String paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isConfirmProductPurchase() {
        return confirmProductPurchase;
    }

    public void setConfirmProductPurchase(boolean confirmProductPurchase) {
        this.confirmProductPurchase = confirmProductPurchase;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole(){
        return role;
    }

    @Override
    public Product getProductInfo() {
        return info;
    }

    @Override
    public Product searchProductInfo() {
        return search;
    }
    
     public Product  rateProduct() {
        return search;
    }
    
}