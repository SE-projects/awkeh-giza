package DMLogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class DMLogin extends Application {

    public void start(Stage stage)throws Exception {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource( "DMlogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Department Manager");
        stage.show();
    }
    public static void main(String[] args){

        launch(args);
    }



    }
