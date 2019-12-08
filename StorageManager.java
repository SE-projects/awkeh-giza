/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradleproject1;

/**
 *
 * @author SUR
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
public class StorageManager extends Frame implements ActionListener {

     
    JButton[] Btn;
     
    public StorageManager()
    {    
        setBackground(Color.lightGray);
        JPanel panelDisplayBtn = new JPanel(new FlowLayout());
        Btn = new JButton[6];
        Btn[0] = new JButton("Add Product");
        Btn[0].setBounds(50,100,95,30);
        Btn[1]  = new JButton("Give Approval");
        Btn[1].setBounds(50,100,95,30);
        Btn[2]  = new JButton("Inform NewEntry");
        Btn[2].setBounds(50,100,95,30);
        Btn[3]  = new JButton("Remove Quantity");
        Btn[3].setBounds(50,100,95,30);
        Btn[4] = new JButton("Update Product");
        Btn[4].setBounds(50,100,95,30);
        Btn[5] = new JButton("getProduct Request");
        Btn[5].setBounds(50,100,95,30);
        Btn[6] = new JButton("Back");
        Btn[6].setBounds(50,100,95,30);
        Btn[6].setBackground(Color.red);
        
        Btn[0].addActionListener(this);
        Btn[1].addActionListener(this);
        Btn[2].addActionListener(this);
        Btn[3].addActionListener(this);
        Btn[4].addActionListener(this);
        Btn[5].addActionListener(this);
        Btn[6].addActionListener(this);
        
        panelDisplayBtn.setBackground(Color.magenta);
        panelDisplayBtn.setBounds(800, 600, 400, 400);
        panelDisplayBtn.add(Btn[0]);
        panelDisplayBtn.add(Btn[1]);
        panelDisplayBtn.add(Btn[2]);
        panelDisplayBtn.add(Btn[3]);
        panelDisplayBtn.add(Btn[4]);
        panelDisplayBtn.add(Btn[5]);
        panelDisplayBtn.add(Btn[6]);
        
      add(panelDisplayBtn);
      setTitle("Storage Manager");
      pack();
      setSize(400, 400);            
      setVisible(true);   
        
     
   }

    @Override
   public void actionPerformed(ActionEvent ae)
    {
        try
        {
            if(ae.getSource()==Btn[6])
            {
               new EmployeeInterface().setVisible(true);
            }
        }
        catch(Exception e)
        {
            System.out.println("The Exception Is : " +e);
        }
    }

    }
