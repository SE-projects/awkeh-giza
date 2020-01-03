package ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import ui.dbconnection;
import  ui.mainModel;
public class RegistrationPageController implements Initializable {
    @FXML
    private Label dbstatus;
    @FXML
    private Button registorationButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField snameField;
    @FXML
    private TextField hnumberField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField subcityField;
    mainModel mm = new mainModel();


    @Override
    public void initialize(URL location, ResourceBundle rb) {
        if (this.mm.isDBconnected()) {
            this.dbstatus.setText("GOOD");
        } else {
            this.dbstatus.setText("Bad");
        }


    }

    @FXML
    public void register(ActionEvent e) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlinsert = "INSERT INTO Customers(phone, email, streetName, homeNumber, subCity, city, FirstName, LastName) VALUES (?,?,?,?,?,?,?,?)";

        try {
            Connection conn = dbconnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlinsert);
            stmt.setString(1, this.phoneField.getText());
            stmt.setString(2, this.emailField.getText());
            stmt.setString(3, this.snameField.getText());
            stmt.setString(4, this.hnumberField.getText());
            stmt.setString(5, this.subcityField.getText());
            stmt.setString(6, this.cityField.getText());
            stmt.setString(7, this.firstNameField.getText());
            stmt.setString(8, this.lastNameField.getText());

            stmt.execute();
            conn.close();

        } catch (SQLException a) {
            System.err.println("Error in Registration" + a);
        }


    }
}
