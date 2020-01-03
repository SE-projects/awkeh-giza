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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class cmController implements Initializable {
    SModel cm1 = new SModel();
    //labels
    @FXML
    private Label reqSt;
    @FXML
    private Label orderSt;
    @FXML
    private Label stgST;
    //buttons
    @FXML
    private Button getReqCM;
    @FXML
    private Button getOrder;
    @FXML
    private Button getCSTG;
    @FXML
    private Button deletePDCT;
    //textfield
    @FXML
    private TextField remPID;

    //request table
    @FXML
    private TableView<ProductPrime> reqTable;
    @FXML
    private TableColumn<ProductPrime, String> nameR;
    @FXML
    private TableColumn<ProductPrime, String> BrandR;
    @FXML
    private TableColumn<ProductPrime, String> quanR;
    @FXML
    private ObservableList<ProductPrime> req;
    @FXML
    private String sql = "select * from ToBeBought";

    //order table
    @FXML
    private  TableView<ProductPrime> orderTable;
    @FXML
    private TableColumn<ProductPrime, String> cidO;
    @FXML
    private TableColumn<ProductPrime, String> pidO;
    @FXML
    private TableColumn<ProductPrime, String> prO;
    @FXML
    private TableColumn<ProductPrime, String> qO;
    @FXML
    private TableColumn<ProductPrime, String> tpO;
    @FXML
    private ObservableList<ProductPrime> ord;
    @FXML
    private String sq2 = "select * from Orders";

    //central storage
    @FXML
    private TableView<ProductPrime> CStable;
    @FXML
    private TableColumn<ProductPrime, String> pCS;
    @FXML
    private TableColumn<ProductPrime, String> nCS;
    @FXML
    private TableColumn<ProductPrime, String> cCS;
    @FXML
    private TableColumn<ProductPrime, String> bCS;
    @FXML
    private TableColumn<ProductPrime, String> rCS;
    @FXML
    private TableColumn<ProductPrime, String> qCS;
    @FXML
    private TableColumn<ProductPrime, String> exCS;
    @FXML
    private TableColumn<ProductPrime, String> prCS;
    @FXML
    private ObservableList<ProductPrime> cs;
    @FXML
    private String sq3 = "select * from centralStorage";


    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(cm1.isDBconnected()){
            reqSt.setText("GOOD");
            orderSt.setText("GOOD");
            stgST.setText("GOOD");
        }else{
            reqSt.setText("BAD");
            orderSt.setText("BAD");
            stgST.setText("BAD");
        }
    }
    //to get request
    @FXML
    public void getRequest(ActionEvent e){
        try{
            Connection conn = dbconnection.getConnection();
            //pr = this.smModel..prepareStatement(sql);
            this.req = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                //as long as there are requests to display
                this.req.add(new ProductPrime(rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        }catch(SQLException ex){System.err.println("Error occured :" + ex);}
        //adding to the table
        // this.idC.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>( "ID"));
        this.nameR.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productName"));
        this.BrandR.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Brand"));
        this.quanR.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Quantity"));

        this.reqTable.setItems(null);
        this.reqTable.setItems(this.req);
        }
        //to get order
    @FXML
    public void gtOrder(ActionEvent f){
        try{
            Connection conn = dbconnection.getConnection();
            this.ord = FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery(sq2);
            while(rs1.next()){
                this.ord.add(new ProductPrime(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
            }
        }catch(SQLException ex){System.err.println("Err order" + ex);}
        this.cidO.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("customerID"));
        this.pidO.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productID"));
        this.prO.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("price"));
        this.qO.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Quantity"));
        this.tpO.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Totprice"));
        this.orderTable.setItems(null);
        this.orderTable.setItems(this.ord);
    }
    //to get central storage
    @FXML
    public void gtCs(ActionEvent g){
        try{
            Connection conn = dbconnection.getConnection();
            this.cs = FXCollections.observableArrayList();
            ResultSet rs2 = conn.createStatement().executeQuery(sq3);
            while(rs2.next()){
                this.cs.add(new ProductPrime(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7), rs2.getString(8)));
            }
        }catch(SQLException ey){System.err.println("Err CS" + ey);}
        this.pCS.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productID"));
        this.nCS.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productName"));
        this.cCS.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Category"));
        this.bCS.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Brand"));
        this.rCS.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Rating"));
        this.qCS.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Quantity"));
        this.exCS.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("ExpirationDate"));
        this.prCS.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("price"));
        this.CStable.setItems(null);
        this.CStable.setItems(this.cs);
    }
}
