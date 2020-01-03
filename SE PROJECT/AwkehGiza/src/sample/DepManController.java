package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class DepManController {

    @FXML
    protected RadioButton checkReport;

    @FXML
    private RadioButton AttachReport;

    @FXML
    private AnchorPane DReport;

    @FXML
    private AnchorPane pnAtach;

    @FXML
    private Button DLog;

    @FXML
    private Void handleRadioButtonAction(ActionEvent dm) {
        if (dm.getSource().equals(checkReport)) {
            DReport.toFront();
        } else if (dm.getSource().equals(AttachReport)) {
            pnAtach.toFront();
        } else{};
        return null;
    }

    @FXML
    private void handleButtonAction(ActionEvent db){
        if(db.getSource().equals(DLog)){
            System.exit(0);
        }
    }

}