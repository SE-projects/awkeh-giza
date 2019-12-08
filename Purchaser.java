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
public class Purchaser extends Frame implements ActionListener {

     
    JButton[] Btn;
     
    public Purchaser()
    {    
        setBackground(Color.LIGHT_GRAY);
        JPanel panelDisplayBtn = new JPanel(new FlowLayout());
        Btn = new JButton[3];
        Btn[0] = new JButton("getVendorInfo");
        Btn[0].setBounds(50,100,95,30);
        Btn[1] = new JButton("getPurchaserList");
        Btn[1].setBounds(50,100,95,30);
        Btn[2] = new JButton("Back");
        Btn[2].setBounds(50,100,95,30);
        Btn[2].setBackground(Color.red);
            
        Btn[0].addActionListener(this);
        Btn[1].addActionListener(this);
        Btn[2].addActionListener(this);
      
        panelDisplayBtn.setBackground(Color.DARK_GRAY);
        panelDisplayBtn.setBounds(800, 600, 400, 400);
        panelDisplayBtn.add(Btn[0]);
        panelDisplayBtn.add(Btn[1]);
        panelDisplayBtn.add(Btn[2]);
        
      add(panelDisplayBtn);
      setTitle("Purchaser");
      pack();
      setSize(400, 400);            
      setVisible(true);   
        
     
   }

    @Override
   public void actionPerformed(ActionEvent ae)
     {
        try
        {
            if(ae.getSource()==Btn[2])
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
