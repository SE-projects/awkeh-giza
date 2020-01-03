package ui;

import DBconnection.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class pController implements Initializable {
    SModel p = new SModel();
    @FXML
    private Label csDB;
    @FXML
    private Label rDB;

    //for request
    @FXML
    private Button getR;
    @FXML
    private TableView<ProductPrime> reqT;
    @FXML
    private TableColumn<ProductPrime, String> pr;
    @FXML
    private TableColumn<ProductPrime, String> br;
    @FXML
    private TableColumn<ProductPrime, String> qr;
    @FXML
    private ObservableList<ProductPrime> req;
    @FXML
    private String sq = "select * from ToBeBought";

    //for adding and viewing the central storage
    // textfields and buttons
    @FXML
    private Button editCS;
    @FXML
    private Button load;
    @FXML
    private TextField pCS;
    @FXML
    private TextField cCS;
    @FXML
    private TextField bCS;
    @FXML
    private TextField qCS;
    @FXML
    private DatePicker expCS;
    @FXML
    private TextField prCS;
// tables and columns
    @FXML
    private TableView<ProductPrime> csT;
    @FXML
    private TableColumn<ProductPrime, String> pid;
    @FXML
    private TableColumn<ProductPrime, String> name;
    @FXML
    private TableColumn<ProductPrime, String> cat;
    @FXML
    private TableColumn<ProductPrime, String> brnd;
    @FXML
    private TableColumn<ProductPrime, String> rtng;
    @FXML
    private TableColumn<ProductPrime, String> quan;
    @FXML
    private TableColumn<ProductPrime, String> expD;
    @FXML
    private TableColumn<ProductPrime, String> price;
    @FXML
    private ObservableList<ProductPrime> cS;
    @FXML
    private String sq1 = "select * from centralStorage";
    @FXML
    private String sq2 = "INSERT INTO centralStorage (cName, cCategory, cBrand, cQuant, cExpd, cPrice) VALUES (?,?,?,?,?,?)";
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(p.isDBconnected()){
            csDB.setText("GOOD");
            rDB.setText("GOOD");
        }else{
            csDB.setText("BAD");
            rDB.setText("BAD");
        }
    }
    @FXML
    public void getRe(ActionEvent e){
        try{
            Connection conn = dbconnection.getConnection();
            //pr = this.smModel..prepareStatement(sql);
            this.req = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(sq);
            while(rs.next()){
                //as long as there are requests to display
                this.req.add(new ProductPrime(rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        }catch(SQLException ex){System.err.println("Error occured :" + ex);}
        //adding to the table
        // this.idC.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>( "ID"));
        this.pr.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productName"));
        this.br.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Brand"));
        this.qr.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Quantity"));

        this.reqT.setItems(null);
        this.reqT.setItems(this.req);
    }
    @FXML
    public void addCS(ActionEvent g){
    try{
        Connection conn = dbconnection.getConnection();
        PreparedStatement add = conn.prepareStatement(sq2);

        add.setString(1, this.pCS.getText());
        add.setString(2, this.cCS.getText());
        add.setString(3, this.bCS.getText());
        add.setString(4, this.qCS.getText());
        add.setString(5, this.expCS.getEditor().getText());
        add.setString(6, this.prCS.getText());

        add.execute();
        conn.close();
    }catch(SQLException e){System.err.println("Err in add:" + e);}
    }


    @FXML
    public void loadCS(ActionEvent f){
        try{
            Connection conn = dbconnection.getConnection();
            this.cS = FXCollections.observableArrayList();
            ResultSet rs2 = conn.createStatement().executeQuery(sq1);
            while(rs2.next()){
                this.cS.add(new ProductPrime(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getString(8)));
            }
        }catch(SQLException ey){System.err.println("Err CS" + ey);}
        this.pid.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productID"));
        this.name.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productName"));
        this.cat.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Category"));
        this.brnd.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Brand"));
        this.rtng.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Rating"));
        this.quan.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Quantity"));
        this.expD.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("ExpirationDate"));
        this.price.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("price"));
        this.csT.setItems(null);
        this.csT.setItems(this.cS);
    }
}
