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
import java.util.ResourceBundle;

public class PLogin implements Initializable {
    SModel pur = new SModel();
    @FXML
    private Label pDb;
    @FXML
    private Label lgSt;
    @FXML
    private Button lgn;
    @FXML
    private TextField name;
    @FXML
    private PasswordField pass;
    @Override
    public void initialize(URL Location, ResourceBundle resources){
        if(pur.isDBconnected()){
            pDb.setText("GOOD");
        }else{pDb.setText("BAD");}
    }
    @FXML
    public void Plogin(ActionEvent e){
        if(this.pur.isPurLoggedin(this.name.getText(), this.pass.getText())){
            try {
                Stage pstg = (Stage) this.lgn.getScene().getWindow();
                pstg.close();
                Stage pstg2 = new Stage();
                FXMLLoader loadp = new FXMLLoader();
                Pane rootp = (Pane) loadp.load(getClass().getResource("/ui/pMain.fxml").openStream());
                pController p = (pController)loadp.getController();

                Scene sc = new Scene(rootp);
                pstg2.setScene(sc);
                pstg2.setTitle("Welcome back to Awkeh Giza " + this.name.getText());
                pstg2.setResizable(false);
                pstg2.show();
            }catch(Exception ex){System.err.println("Err login " + ex);}
        }
        else{lgSt.setText("Please try again");}
    }

}
