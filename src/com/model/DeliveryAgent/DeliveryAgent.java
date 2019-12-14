/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeliveryAgent;

import Database.DBConnection;
import Employees.Employees;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DeliveryAgent {
    
    int id;
    String query;
    Employees employe;
    ResultSet result;
    ArrayList<Employees> employee;
    DeliveryAgent(Object Product){
        
    }
    
    public void updateOrderStatus(String orderId)
    {
        getUpdatedInfo(orderId);
        
    }    
    
}
