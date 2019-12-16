package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("SMLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Awkeh-Giza");
        primaryStage.setScene(scene);
        // primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
