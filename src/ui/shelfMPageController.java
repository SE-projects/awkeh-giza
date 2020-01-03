package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import ui.dbconnection;

public class shelfMPageController implements Initializable {
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
    public void addRequest(ActionEvent e){PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlinsert = "INSERT INTO ToBeBought(Name, Quantity, Brand, Branch) VALUES (?,?,?,?)";
        try {
            Connection conn = dbconnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlinsert);
            stmt.setString(2, this.requestName.getText());
            stmt.setString(3, this.requestQuantity.getText());
            stmt.setString(4, this.requestBrand.getText());
            stmt.setString(5, this.requestBranch.getText());

            stmt.execute();
            conn.close();

        } catch (SQLException a) {
            System.err.println("Error in Registration" + a);
        }

    }


}
