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

public class shelfMLoginController implements Initializable {
    @FXML
    private Button loginButton;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;
    @FXML
    private Label DBcheck;
    @FXML
    private Label fieldStatus;

    mainModel mm=new mainModel();
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;

    public void initialize(URL location, ResourceBundle rb) {
        if (this.mm.isDBconnected()) {
            this.DBcheck.setText("Database Connected");
        } else {
            this.DBcheck.setText("DB not connected");
        }
    }

    public void login(ActionEvent e){
        if (this.mm.isShelfMLoggedin(this.userName.getText(), this.passWord.getText())) {
            Stage stage = (Stage) this.loginButton.getScene().getWindow();
            stage.close();
            Stage stg = new Stage();
            FXMLLoader load1 = new FXMLLoader();
            Pane root1 = null;
            try {
                Class<? extends shelfMLoginController> c = getClass();
                InputStream n = c.getResource("shelfManager.fxml").openStream();
                root1 = (Pane) load1.load(n);
            } catch (IOException i) {
                i.printStackTrace();
            }
            shelfMPageController smp = (shelfMPageController) load1.getController();

            Scene sc2 = new Scene(root1);
            stg.setScene(sc2);
            stg.setTitle("Welcome to Awkeh-Giza " + this.userName.getText());
            stg.setResizable(false);
            stg.show();
        } else {
            fieldStatus.setText("Please Try Again");
        }
    }
}
