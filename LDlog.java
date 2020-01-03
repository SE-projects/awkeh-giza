package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LDlog implements Initializable {
    SModel LD = new SModel();
    @FXML
    private Label LDst;
    @FXML
    private Label inputError;
    @FXML
    private Button LDlog;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @Override
    public void initialize(URL Location, ResourceBundle resources){
        if(LD.isDBconnected()){
            LDst.setText("GOOD");
        }else{LDst.setText("BAD");}
    }
@FXML
    public void LDcheck(ActionEvent ff){
       if(LD.isLDloggedin(this.name.getText(), this.password.getText())) {
        try {
            Stage pstg = (Stage) this.LDlog.getScene().getWindow();
            pstg.close();
            Stage pstg2 = new Stage();
            FXMLLoader loadLD = new FXMLLoader();
            Pane rootLD = (Pane) loadLD.load(getClass().getResource("/ui/LDmain.fxml").openStream());
            LDcontroller ld = (LDcontroller) loadLD.getController();

            Scene sc = new Scene(rootLD);
            pstg2.setScene(sc);
            pstg2.setTitle("Welcome back to Awkeh Giza " + this.name.getText());
            pstg2.setResizable(false);
            pstg2.show();
        } catch (Exception e) {
            System.err.println("Err in chk " + e);
        }
    }else{inputError.setText("Try Again");}
}
}
