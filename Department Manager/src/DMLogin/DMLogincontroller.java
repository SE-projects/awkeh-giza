package DMLogin;

import DME.DMEController;
import DMN.DMNController;
import DMS.DMSController;
import DMW.DMWCOntroller;
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
import javafx.scene.layout.Pane;


public class DMLogincontroller implements Initializable {

    DMloginModel dmloginmodel = new DMloginModel();

    @FXML
    private Label dbstatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<option> DMcombo;

    @FXML
    private Button login;

    public void initialize(URL url, ResourceBundle rb) {

        if (this.dmloginmodel.isDatabaseConnected()) {
            this.dbstatus.setText("Connected");
        } else {
            this.dbstatus.setText("Not Connected to Database");
        }

        this.DMcombo.setItems(FXCollections.observableArrayList(option.values()));

    }

    @FXML

    public void login(ActionEvent event{

        try {
            if(this.dmloginmodel.islogin(this.username.getText(), this.password.getText(),((option)this.DMcombo.getValue()) toString() )){

                Stage stage = (Stage)this.loginbtn
                stage.close();
                Stage stg1 = new Stage();
                FXMLLoader load1 = new FXMLLoader();
                Pane root1 = null;
                try {
                    Class<? extends StoManController> c = getClass();
                    InputStream n = c.getResource("decoy.fxml").openStream();
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
   public void DMWLogin(){
       try{
           Stage userstage = new Stage();
           FXMLLoader loader = new FXMLLoader();
           Pane root = (Pane)loader.load(getClass().getResource( "/DMW/DMWFXML.fxml").openStream() );

           DMWCOntroller DMWCOntroller = (DMWCOntroller)loader.getController();
           Scene scene = new Scene(root);
           userstage.setScene(scene);
           userstage.setTitle("Department Manager Dashboard");
           userstage.setResizable(false);
           userstage.show();

       } catch (IOException ex){
           ex.printStackTrace();

       }


    }
    public void DMELogin(){

        try{
            Stage DMEstage = new Stage();
            FXMLLoader DMEloader = new FXMLLoader();
            Pane DMEroot = (Pane)DMEloader.load(getClass().getResource( "/DME/DMEFXML.fxml").openStream() );

            DMEController DMEController = (DMEController) DMEloader.getController();
            Scene scene = new Scene(DMEroot);
            DMEstage.setScene(scene);
            DMEstage.setTitle("Department Manager Dashboard");
            DMEstage.setResizable(false);
            DMEstage.show();

        } catch (IOException ex){
            ex.printStackTrace();

        }

    }

    public void DMNLogin (){

        try{
            Stage DMNstage = new Stage();
            FXMLLoader DMNloader = new FXMLLoader();
            Pane DMNroot = (Pane)DMNloader.load(getClass().getResource( "/DMN/DMNFXML.fxml").openStream() );

            DMNController DMNController = (DMN.DMNController)DMNloader.getController();
            Scene scene = new Scene(DMNroot);
            DMNstage.setScene(scene);
            DMNstage.setTitle("Department Manager Dashboard");
            DMNstage.setResizable(false);
            DMNstage.show();

        } catch (IOException ex){
            ex.printStackTrace();

        }

    }

    public void DMSLogin () {
        try{
            Stage DMSstage = new Stage();
            FXMLLoader DMSloader = new FXMLLoader();
            Pane root = (Pane)DMSloader.load(getClass().getResource( "/DMS/DMSFXML.fxml").openStream() );

            DMSController DMSCOntroller = (DMSController)DMSloader.getController();
            Scene scene = new Scene(root);
            DMSstage.setScene(scene);
            DMSstage.setTitle("Department Manager Dashboard");
            DMSstage.setResizable(false);
            DMSstage.show();

        } catch (IOException ex){
            ex.printStackTrace();

        }
    }
}

