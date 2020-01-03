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

public class FPageController implements  Initializable {
    mainModel mm = new mainModel();

    @FXML
    private Button loginButton;
    @FXML
    private ComboBox<loginChoice> loginComboBox;
    @FXML
    private Button viewcart;
    @FXML
    private Button registerbutton;
    @FXML
    private Button searchbutton;
    @FXML
    private TextField searchfield;
    @FXML
    private Label DBcheck;
    @FXML
    private Label lotion;
    @FXML
    private Label cheese;
    @FXML
    private Label juice;
    @FXML
    private Label meat;
    @FXML
    private Label apples;
    @FXML
    private Label tv;
    @FXML
    private ListView sidelist;
    @FXML
    private ImageView lotionimage;
    @FXML
    private ImageView juiceimage;
    @FXML
    private ImageView meatimage;
    @FXML
    private ImageView applesimge;
    @FXML
    private ImageView tvimage;
    @FXML
    private ImageView cheeseimage;
    PreparedStatement ps=null;
    Connection con=null;
    ResultSet rs=null;
    @Override
    public void initialize(URL location, ResourceBundle rb ){
        if (this.mm.isDBconnected()){
            this.DBcheck.setText("Connected");
        }else{
            this.DBcheck.setText("Disconncted");
        }
        this.loginComboBox.setItems(FXCollections.observableArrayList(loginChoice.values()));

    }
    @FXML
    public void handleSearch(ActionEvent e) throws SQLException {

        String searchItem = searchfield.getText();

        con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Alazar GT\\Documents\\GitHub\\awkeh-giza\\superMarket.sqlite");
        ps = con.prepareStatement("select *");
        rs=ps.executeQuery();

    }
    @FXML
    public void registerAction(ActionEvent e){
        try{
            Stage stg = (Stage)this.registerbutton.getScene().getWindow();
            stg.close();
            Stage stg1 = new Stage();
            FXMLLoader load1 = new FXMLLoader();
            Pane root1 = (Pane) load1.load(getClass().getResource("/ui/RegistrationPage.fxml").openStream());
            RegistrationPageController rp = (RegistrationPageController) load1.getController();

            Scene sc2 = new Scene(root1);
            stg1.setScene(sc2);
            stg1.setResizable(false);
            stg1.show();
        }
        catch (Exception f){f.printStackTrace();}
    }

    public void loginAction(ActionEvent e){
        try{
           Stage stg=(Stage)this.loginButton.getScene().getWindow();
           stg.close();
          switch (((loginChoice)this.loginComboBox.getValue()).toString()){

              case "storageManager":
                  smLogin();
                  break;
              case "customer":
                  customerLogin();
                  break;
              case "centralManager":
                  cmLogin();
                  break;
              case "shelfManager":
                  shelfManager();
                  break;
          }
        } catch (Exception localException){}
    }
    public void shelfManager(){
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/ui/shelfManagerPage.fxml").openStream());

            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.setTitle("Shelf Manager dashboard");
            stage.setResizable(false);
            stage.show();
        } catch(IOException io){io.printStackTrace();}

    }
    public void smLogin(){
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/ui/SMLogin.fxml").openStream());
            StoManController stmcont= (StoManController) loader.getController();
            Scene sc = new Scene(root);
            stage.setScene(sc);
            //stage.setTitle("Storage Manager dashboard");
            stage.setResizable(false);
            stage.show();
        } catch(IOException io){io.printStackTrace();}
    }
    public void customerLogin(){
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/ui/CustomerLoginPage.fxml").openStream());
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.setTitle("Customer dashboard");
            stage.setResizable(false);
            stage.show();
        } catch(IOException io){io.printStackTrace();}
    }
    public void cmLogin(){
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/ui/cmLogin.fxml").openStream());
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.setTitle("Central Manager dashboard");
            stage.setResizable(false);
            stage.show();
        } catch(IOException io){io.printStackTrace();}
    }
}
