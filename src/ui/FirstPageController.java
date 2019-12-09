package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class FirstPageController {

    ObservableList<String> loginAsList= FXCollections.observableArrayList("Customer", "Employee");
    @FXML
    private ComboBox loginAsBox;
    @FXML
    private void initialize(){

        loginAsBox.setItems(loginAsList);
    }

}
