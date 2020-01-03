package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class cmL implements Initializable{
    SModel cm = new SModel();
    @FXML
    private Label dbst;
    @FXML
    private TextField name;
    @FXML
    private PasswordField pass;
    @FXML
    private Button cmL;
    @FXML
    private Label cmlgSt;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(cm.isDBconnected()){
            this.dbst.setText("GOOD");
        }else{this.dbst.setText("BAD");}
    }
    //lets login
    @FXML
    public void CMlogin(ActionEvent e){
            if (this.cm.isCMloggedin(this.name.getText(), this.pass.getText())) {
                try {
                    Stage cmstg = (Stage) this.cmL.getScene().getWindow();
                    cmstg.close();
                    Stage cmstg2 = new Stage();
                    FXMLLoader loadcm = new FXMLLoader();
                    Pane rootcm = (Pane) loadcm.load(getClass().getResource("/ui/cmMainPage.fxml").openStream());
                    cmController cm = (cmController)loadcm.getController();

                    Scene sc = new Scene(rootcm);
                    cmstg2.setScene(sc);
                    cmstg2.setTitle("Welcome back to Awkeh Giza " + this.name.getText());
                    cmstg2.setResizable(false);
                    cmstg2.show();
                }catch(Exception ex){ex.printStackTrace();}
            }else{cmlgSt.setText("try again");}

        }

}
