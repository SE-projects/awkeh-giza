/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeegui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SUR
 */
public class EmployeeController implements Initializable {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Log In.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }


public class PleaseProvideControllerClassName {

    @FXML
    private Button btn;
Stage stage = new Stage();
    @FXML
    void btn(MouseEvent event) {

    }

    @FXML
    void openLogin(ActionEvent event) throws IOException {

            if(event.getSource()==btn)  
            {  
        Parent root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
            }  
    }

}

public static void main(String[] args) {  
  launch(args); 
}  
}
