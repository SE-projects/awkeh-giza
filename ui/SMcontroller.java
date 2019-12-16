package ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SMcontroller  implements Initializable {
    SModel smModel = new SModel();
    @FXML
    private ListView list;
    @FXML
    private Button reqBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(this.smModel.isDBconnected()){
        }
        else{}
    }

    @FXML
    public void getItemToBeBought(ActionEvent ex){
        try{
            ResultSet rs=smModel.getItemToBeBought();
       while(rs.next()){
// how change index for different items
           list.getItems().add((smModel.getItemToBeBought().getString(2)));
           list.getItems().add((smModel.getItemToBeBought().getInt(3)));
        }
        }catch(Exception e){
System.out.println("linne 40");
        }
    }


}
