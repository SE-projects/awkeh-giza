package ui;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class StoManController implements Initializable {
    // creating an instance of SModel
    SModel smModel = new SModel();
    @FXML
    private Label dbStatus;
    @FXML
    private ComboBox<Branch> comboBranch;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button loginBtn;
    @FXML
    private Label fieldStatus;
    @FXML
    private Label pageStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(this.smModel.isDBconnected()){
            this.dbStatus.setText("GOOD");
        }
        else{this.dbStatus.setText("Bad");}
        this.comboBranch.setItems(FXCollections.observableArrayList(Branch.values()));
        pageStatus.setText("Storage Manager Login Page");
    }


    @FXML
    public void SMLogin (ActionEvent ex){
        if(this.smModel.isSMloggedin(this.nameField.getText(), this.passField.getText(), ((Branch)this.comboBranch.getValue()).toString())) {

            Stage stg = (Stage) this.loginBtn.getScene().getWindow();
            stg.close();
            Stage stg1 = new Stage();
            FXMLLoader load1 = new FXMLLoader();
            Pane root1 = null;
            try {
                Class<? extends StoManController> c = getClass();
                InputStream n = c.getResource("/ui/decoy.fxml").openStream();
                root1 = (Pane) load1.load(n);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SMcontroller SM1 = (SMcontroller) load1.getController();

            Scene sc2 = new Scene(root1);
            stg1.setScene(sc2);
            stg1.setTitle("Welcome to Awkeh-Giza " + ((Branch) this.comboBranch.getValue()).toString() + " Branch " + this.nameField.getText());
            stg1.setResizable(false);
            stg1.show();

        }
        else{fieldStatus.setText("Please try again");}
    }


}


