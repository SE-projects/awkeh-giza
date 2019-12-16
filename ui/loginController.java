package ui;

import Customers.CustomerController;
import Employees.EmployeeController;
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
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable{
    // creating an object of the login model
    loginModel loginM = new loginModel();
    @FXML
    private Label loginPage;
    @FXML
    private Label loginStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Choice> combobox;
    @FXML
    private Button loginButton;



    public void initialize(URL location, ResourceBundle rb) {
        if (this.loginM.isDBconnected()){
            this.loginPage.setText("Connected");
        }else{
            this.loginPage.setText("Disconncted");
        }
        this.combobox.setItems(FXCollections.observableArrayList(Choice.values()));
    }

    @FXML
    public void LOGIN(ActionEvent e){
        try {
            if(this.loginM.isLoggedin(this.username.getText(), this.password.getText(), ((Choice)this.combobox.getValue()).toString())){
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();
                switch ( ((Choice)this.combobox.getValue()).toString()){
                    case "Employee":
                        EmployeeLogin();
                        break;
                    case "Customer":
                        CustomerLogin();
                        break;
                     default:
                         this.loginStatus.setText("Wrong!");
                }
            }

            else{this.loginStatus.setText("Wrong!");}
        }catch (Exception localEx){}
    }
    public void CustomerLogin(){
        try {
            Stage stg = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/Customers/customerPage.fxml").openStream());

            CustomerController cusCont = (CustomerController)loader.getController();

            Scene sc = new Scene(root);
            stg.setScene(sc);
            stg.setTitle("Customer dashboard");
            stg.setResizable(false);
            stg.show();
        }
        catch(IOException io){io.printStackTrace();
        }
    }
    public void EmployeeLogin(){
        try {
            Stage emStg = new Stage();
            FXMLLoader loader1 = new FXMLLoader();
            Pane root1 = (Pane)loader1.load(getClass().getResource("/Employees/employeePage.fxml").openStream());
            EmployeeController empCont = (EmployeeController) loader1.getController();

            Scene sc1 = new Scene(root1);
            emStg.setScene(sc1);
            emStg.setTitle("Employee dashboard");
            emStg.setResizable(false);
            emStg.show();

        }
        catch (IOException ex){ex.printStackTrace();}
    }
}
