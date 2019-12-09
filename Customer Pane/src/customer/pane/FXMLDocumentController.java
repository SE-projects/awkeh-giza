/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.pane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Abel.S
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Tab tab1;
    @FXML
    private Label lb1;
    @FXML
    private TextField txt1;
    @FXML
    private Tab tab2;
    @FXML
    private TextArea txt2;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private TabPane tab4;
    @FXML
    private Tab tab3;
    
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
