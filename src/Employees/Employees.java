/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employees;

import CentralManager.CentralManager;
import DeliveryAgent.DeliveryAgent;
import GeneralManager.GeneralManager;
import Purchaser.Purchaser;
import ShelfManager.ShelfManager;
import StorageManager.StorageManager;
import static com.oracle.jrockit.jfr.ContentType.Address;
import com.sun.jndi.cosnaming.IiopUrl.Address;

public class Employees {
    GeneralManager generalManager;
    Purchaser purchaser;
    ShelfManager shelfManager;
    StorageManager storageManager;
    CentralManager centralManager;
    DeliveryAgent deliveryAgent;
  
    String firstName,lastName,email;
    Address address;
    int age;
    Double  id;
    Employees(){
        
    }
    public Employees(Double id, String firstName,String lastName, String email, int age){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.age=age;
        
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public void setDeliveryAgent(DeliveryAgent deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
    }

    public String createEmployeeInfo(){
        return this.firstName + this.lastName + this.email + this.address.toString()+ this.age;
    }
}
