///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Customers.Controllers;
//
//import Customers.Model.DBConnection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import javax.swing.JOptionPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
///**
// *
// * @author Hayyu
// */
//public class customerImpl {
//    
//    
//    String fname,lname,sex,email, level, department,choose,query,search,role;
//    Date date;
//    int mobileNo,id;
//    Double salryCalculated;
//    
//     ResultSet result;
//     DefaultTableModel model1,model2,model3;
//      Object[] row1,row2,col1,col2;
//      JTable display;
//      registeredCustomer registeredustomer;
//     ArrayList<registeredCustomer> customerArray;
//     ArrayList<registeredCustomer> searchedcustomerArray;
//     
//    public customerImpl(int id,String name) {
//        this.id=id;
//       
//          
//    }
//  
//     public ArrayList<registeredCustomer> getInfo(){
//        
////         AgerArray= new ArrayList<InformationTable>();
////         registeredcustomer=new ArrayList<registeredCustomer>();
//         try{
//              if(DBConnection.connect()){
//                 System.out.println(choose);
//                query="select * from employee";
//                result=DBConnection.select(query);
//            while(result.next()){
//                    registeredustomer = new registeredCustomer(
//                            result.getInt("employeeId"),
//                    result.getString("firstName"),
//                    result.getString("lastName"),
//                            result.getInt("mobile"),
//                            result.getString("email"),
//                            result.getString("sex"),
//                            result.getDate("hiredDate"),
//                    result.getInt("exprienceYear"),
//                    
//                    result.getString("level"),
//                    result.getString("department"),
//                    result.getDouble("salary")
//                    );
//                    employeeArray.add(emplo);
//                    System.out.println("added to array");
//                   
//     
//                 }
//              
//             DBConnection.disConnect();
//              
//              }}
//         catch(SQLException ex){
//               ex.printStackTrace();
//         }
//         return employeeArray;
//     }
//     public ArrayList<Employee> search(){
//          searchedemployeeArray=new ArrayList<Employee>();
//         try{
//              if(DBConnection.connect()){
//                 System.out.println("connect"+search);
//                query="select * from employee where firstName= '"+ search+"'";
//                result=DBConnection.select(query);
//            while(result.next()){
//                    empl = new Employee(
//                            result.getInt("employeeId"),
//                            result.getString("firstName"),
//                           result.getString("lastName"),
//                            result.getInt("mobile"),
//                            result.getString("email"),
//                            result.getString("sex"),
//                            result.getDate("hiredDate"),
//                    result.getInt("exprienceYear"),
//                    
//                    result.getString("level"),
//                    result.getString("department"),
//                    result.getDouble("salary")
//                    );
//                    searchedemployeeArray.add(empl);
//                    System.out.println("added to array");
//                    System.out.println("add:"+searchedemployeeArray.get(0));
//                   
//     
//                 }
//              
//             DBConnection.disConnect();
//              
//              }}
//         catch(SQLException ex){
//               ex.printStackTrace();
//         }
//         return searchedemployeeArray;
//     
//     }
//
//
//
//    
//
