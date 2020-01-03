package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class regCustomerPageController implements Initializable {

    @FXML
    private TextField requestName;
    @FXML
    private TextField requestBrand;
    @FXML
    private TextField requestQuantity;
    @FXML
    private Button submitButton;
    @FXML
    private Label DBcheck;
    @FXML
    private TextField requestBranch;
    @FXML
    private TextField orderedProduct;
    @FXML
    private TextField orderedPrice;
    @FXML
    private TextField orderedQuantity;
    @FXML
    private TextField orderedTotalPrice;

    PreparedStatement ps=null;
    Connection con=null;
    ResultSet rs=null;

    mainModel mm=new mainModel();

    @Override
    public void initialize(URL location, ResourceBundle rb ){
        if (this.mm.isDBconnected()){
            this.DBcheck.setText("Database Connected");
        }else{
            this.DBcheck.setText("Disconncted");
        }

    }
    public void addRequest(ActionEvent e){
        String sqlinsert = "INSERT INTO ToBeBought(Name, Quantity, Brand, Branch) VALUES (?,?,?,?)";
        try{
            Connection conn=dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sqlinsert);
            stmt.setString(1,requestName.getText());
            stmt.setString(2,requestQuantity.getText());
            stmt.setString(3,requestBrand.getText());
            stmt.setString(4,requestBranch.getText());

            stmt.execute();
            conn.close();
        } catch (SQLException a){ System.err.println(("Error In Adding Items "+a));}
    }
    public void placeOrder(ActionEvent e){
        String sqlinsert = "INSERT INTO Orders(pdctID, price, quantity, totalPrice) VALUES (?,?,?,?)";
        try{
            Connection conn=dbconnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sqlinsert);
            stmt.setString(1,orderedProduct.getText());
            stmt.setString(2,orderedPrice.getText());
            stmt.setString(3,orderedQuantity.getText());
            stmt.setString(4,orderedTotalPrice.getText());

            stmt.execute();
            conn.close();
        } catch (SQLException a){ System.err.println(("Error In Adding Items "+a));}
    }
}
