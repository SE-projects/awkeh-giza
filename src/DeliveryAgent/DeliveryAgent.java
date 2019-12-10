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
    
    
    public ArrayList<Employees> getUpdatedInfo( String orderId){

//    AgerArray= new ArrayList<Employees>();
      employee=new ArrayList<Employees>();
    try{
         if(DBConnection.connect()){
            System.out.println(id);
           query="select * from order where id= id";
           result=DBConnection.select(query);
       while(result.next()){
               employe = new Employees(
                       result.getDouble("employeeId"),
                       result.getString("firstName"),
                       result.getString("lastName"),
                       result.getString("email"),
                       result.getInt("age")
               );
               employee.add(employe);
               System.out.println("added to array");
            }

        DBConnection.disConnect();

         }}
    catch(SQLException ex){
          ex.printStackTrace();
    }
    return employee;
}
}
