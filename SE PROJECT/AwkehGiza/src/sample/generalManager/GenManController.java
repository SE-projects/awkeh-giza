package sample.generalManager;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class GenManController {

    @FXML
    private RadioButton Transaction;

    @FXML
    private RadioButton Report;

    @FXML
    private Button gLog;

    @FXML
    private AnchorPane pnReport;

    @FXML
    private AnchorPane pnTransaction;

    @FXML
    private Void handleRadioButtonAction(ActionEvent gm) {
        if (gm.equals(Report)) {
            pnReport.toFront();
        } else if (gm.equals(Transaction)) {
            pnTransaction.toFront();
        } else{};
        return null;
    }
        private void handleButtonAction(ActionEvent gb){
        if(gb.equals(gLog)){
            System.exit(0);
        }
    }

}