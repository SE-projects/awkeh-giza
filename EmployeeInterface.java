package gradleproject1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author SUR
 */
 //String msg="";
    
import java.awt.*;

import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JPasswordField;

class EmployeeInterface extends Frame implements ActionListener{
    
     
     
    JButton[] Btn;
            //btnNew,btnSubmit,btnView;
    JLabel lblFName,lblLName,lblAge,lblAddr,lblGender,lblQua,lblPassword,
            lblId,lblEmail,lblPhoneNumber;
    TextField txtFName,txtAge,txtLName,txtId,txtEmail,txtPhoneNumber,txtAddr;
    JPasswordField passwordField;
    CheckboxGroup ChkGrp;
    Checkbox chkMale,chkFemale;
    Checkbox chkCenteralStorageManager,chkShelfManager,chkCashier,chkDepartmentManager,chkGeneralManager
            ,chkStorageManager,chkDeliveryAgent,chkPurchaser,chkCenteralManager;
    
    JFrame fram;
    public EmployeeInterface()
    {    
        //super(name);
        setBackground(Color.CYAN);
        setLayout(new FlowLayout());      
        lblFName = new JLabel("First Name: ");
        lblFName.setBounds(50,50, 150,20);
        lblLName = new JLabel("Last Name: ");
        lblLName.setBounds(50,50, 150,20);
        lblAge = new JLabel("Age: ");
        lblAge.setBounds(50,50, 150,20);
        lblAddr = new JLabel("Address : ");
        lblAddr.setBounds(50,50, 150,20);
        lblGender = new JLabel("Gender: ");
        lblGender.setBounds(50,50, 150,20);
        lblId = new JLabel("ID : ");
        lblId.setBounds(50,50, 150,20);
        lblPassword = new JLabel("Password : ");
        lblEmail = new JLabel("Email : ");
        lblEmail.setBounds(50,50, 150,20);
        lblPhoneNumber = new JLabel("Phone Number : ");
        lblPhoneNumber.setBounds(50,50, 150,20);
        lblQua = new JLabel("Position : ");
        txtFName = new TextField(); 
        txtFName.setBounds(2000,50, 150,20);
        txtLName = new TextField();
        lblLName.setBounds(2000,100, 150,20);
        txtId = new TextField();
        txtId.setBounds(200,50, 150,20);
        txtEmail = new TextField();
        txtEmail.setBounds(200,50, 150,20);
        txtPhoneNumber = new TextField();
        txtPhoneNumber.setBounds(200,50, 150,20);
        txtAge = new TextField();
        txtAge.setBounds(200,50, 150,20);
        txtAddr = new TextField();
        txtAddr.setBounds(2000,200, 150,20);
        passwordField = new JPasswordField(5);
        lblPassword.setBounds(2000,100, 80,30);    
        passwordField.setBounds(200,100,100,30);
        ChkGrp = new CheckboxGroup();
        chkMale = new Checkbox("Male",ChkGrp,false);
        chkMale.setBounds(100,100, 50,50);
        chkFemale = new Checkbox("Female",ChkGrp,false); 
        chkFemale.setBounds(100,100, 50,50);
        chkCenteralStorageManager = new Checkbox("Centeral Storage Manager :");
        chkCenteralStorageManager.setBounds(100,100, 50,50);
        chkShelfManager = new Checkbox("Shelf Manager :");
        chkShelfManager.setBounds(100,100, 50,50);
        chkDepartmentManager = new Checkbox("Department Manager :");
        chkDepartmentManager.setBounds(100,100, 50,50);
        chkDeliveryAgent = new Checkbox("Delivery Agenet :");
        chkDeliveryAgent.setBounds(100,100, 50,50);
        chkStorageManager = new Checkbox("Storage Manager :");
        chkStorageManager.setBounds(100,100, 50,50);
        chkCenteralManager = new Checkbox("Centeral Manager :");
        chkCenteralManager.setBounds(100,100, 50,50);
        chkPurchaser = new Checkbox("Purchaser :");
        chkPurchaser.setBounds(100,100, 50,50);
        chkCashier = new Checkbox("Cashier :");
        chkCashier.setBounds(100,100, 50,50);
        chkGeneralManager = new Checkbox("General Manager :");
        chkGeneralManager.setBounds(100,100, 50,50);       
        JPanel panelDisplayALL = new JPanel(new BorderLayout());
        JPanel panelDisplayBtn = new JPanel(new GridLayout());
        JPanel panelDisplayName = new JPanel(new GridLayout());
        JPanel panelDisplay = new JPanel(new FlowLayout());
        JPanel panelDisplayCheckBox = new JPanel(new GridLayout());
        JPanel panelDisplayCheckBoxPosition = new JPanel(new FlowLayout());
        Btn = new JButton[4];
        Btn[1] = new JButton("Register");
        Btn[1].setBounds(50,100,95,30);
        Btn[2]  = new JButton("Log In");
        Btn[2].setBounds(50,100,95,30);
        Btn[3]  = new JButton("VIEW");
        Btn[3].setBounds(50,100,95,30);
        Btn[1].addActionListener(this);
        Btn[2].addActionListener(this);
        Btn[3].addActionListener(this);
        panelDisplayALL.setBackground(Color.red);
        panelDisplayBtn.setBackground(Color.MAGENTA);
        panelDisplayName.setBackground(Color.ORANGE);
        panelDisplay.setBackground(Color.GREEN);
        panelDisplayCheckBox.setBackground(Color.PINK);
        panelDisplayCheckBoxPosition.setBackground(Color.lightGray);
        panelDisplayName.add(lblFName);
        panelDisplayName.add(txtFName);
        panelDisplayName.add(lblLName);
        panelDisplayName.add(txtLName);
        panelDisplay.add(lblId);
        panelDisplay.add(txtId);
        panelDisplay.add(lblEmail);
        panelDisplay.add(txtEmail);
        panelDisplay.add(lblPhoneNumber);
        panelDisplay.add(txtPhoneNumber);
        panelDisplay.add(lblPassword);
        panelDisplay.add(passwordField);
        panelDisplay.add(lblAge);
        panelDisplay.add(txtAge);
        panelDisplay.add(lblAddr);
        panelDisplay.add(txtAddr);
        panelDisplayCheckBox.add(lblGender);
        panelDisplayCheckBox.add(chkMale);
        panelDisplayCheckBox.add(chkFemale);
        
        panelDisplayCheckBoxPosition.add(lblQua);
        panelDisplayCheckBoxPosition.add(chkCenteralStorageManager);
        panelDisplayCheckBoxPosition.add(chkStorageManager); 
        panelDisplayCheckBoxPosition.add(chkCenteralManager);
        panelDisplayCheckBoxPosition.add(chkShelfManager); 
        panelDisplayCheckBoxPosition.add(chkCashier); 
        panelDisplayCheckBoxPosition.add(chkPurchaser); 
        panelDisplayCheckBoxPosition.add(chkDeliveryAgent); 
        panelDisplayCheckBoxPosition.add(chkGeneralManager); 
        panelDisplayCheckBoxPosition.add(chkDepartmentManager); 
    
        panelDisplayBtn.add(Btn[1]);
        panelDisplayBtn.add(Btn[2]);
        panelDisplayBtn.add(Btn[3]);
       
        panelDisplayALL.setAutoscrolls(true);
        panelDisplayALL.setBounds(800, 800, 100, 100);
        panelDisplayALL.setBackground(Color.red);
       panelDisplayALL.add(panelDisplayName, BorderLayout.WEST);
       panelDisplayALL.add(panelDisplay, BorderLayout.CENTER);
       panelDisplayALL.add(panelDisplayCheckBox, BorderLayout.WEST);
       panelDisplayALL.add(panelDisplayCheckBoxPosition, BorderLayout.NORTH);
       add(panelDisplayName);
       add(panelDisplayALL);
       add(panelDisplayBtn);
      setTitle("Employee Management");
      pack();
      setSize(400, 400);            
      setVisible(true);   
        
     
   }

    @Override
   public void actionPerformed(ActionEvent ae)
    {
      
        try
        {
           
            String str = ae.getActionCommand();
        
            if(ae.getSource()==Btn[1])
            {
                txtFName.setText("");
                txtLName.setText("");
                txtId.setText("");
                txtPhoneNumber.setText("");
                txtAge.setText("");                    
                txtAddr.setText("");
                chkMale.setState(false);
                chkFemale.setState(false);
                chkCenteralStorageManager.setState(false);                    
                chkStorageManager.setState(false);                    
                chkCenteralManager.setState(false);                    
                chkShelfManager.setState(false);
                chkCashier.setState(false);
                chkDeliveryAgent.setState(false);
                chkPurchaser.setState(false);
                chkDepartmentManager.setState(false);
                chkGeneralManager.setState(false);
            }
        
         if(ae.getSource()==Btn[3]){
              if(chkCashier.getState()){
                new Cashier().setVisible(true);
                this.setVisible(false);
            }
         
         else
              
              if(chkCenteralStorageManager.getState()){
                new CenteralStorageManager().setVisible(true);
                this.setVisible(false);
            
         }
              else 
                
              if(chkDeliveryAgent.getState()){
                new DeliveryAgent().setVisible(true); 
                this.setVisible(false);
         }
                else 
                
              if(chkDepartmentManager.getState()){
                new DepartmentManager().setVisible(true); 
         }
                 else 
              if(chkGeneralManager.getState()){
                new GeneralManager().setVisible(true); 
                this.setVisible(false);
         }
                 else 
              if(chkShelfManager.getState()){
                new ShelfManager().setVisible(true); 
         }
                 else 
              if(chkStorageManager.getState()){
                new StorageManager().setVisible(true);
                this.setVisible(false);
         }
                 else 
              if(chkPurchaser.getState()){
                new Purchaser().setVisible(true); 
                this.setVisible(false);
         }
                  else 
              if(chkCenteralManager.getState()){
                new CenteralManager().setVisible(true); 
                this.setVisible(false);
         }
        }}
        catch(Exception e)
        {
            System.out.println("The Exception Is : " +e);
        }
    }
}
    