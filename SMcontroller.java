package ui;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DBconnection.dbconnection;
import javafx.scene.control.cell.PropertyValueFactory;

public class SMcontroller  implements Initializable {
    SModel smModel = new SModel();
    @FXML
    private Button getReq;
    /////for the product table
    @FXML
    private Label tableConn;
    @FXML
    private TableView<ProductPrime> productTable;
    @FXML
    private TableColumn<ProductPrime, String> catC1;
    @FXML
    private TableColumn<ProductPrime, String> nameC1;
    @FXML
    private TableColumn<ProductPrime, String> brandC1;
    @FXML
    private TableColumn<ProductPrime, String> expC11;
    @FXML
    private TableColumn<ProductPrime, String> priceC111;
    @FXML
    private TableColumn<ProductPrime, String> quantityC1111;
    ////// for the request table
    @FXML
    private TableView<ProductPrime> reqTable;
    @FXML
    private TableColumn<ProductPrime, String> idC;
    @FXML
    private TableColumn<ProductPrime, String> nameC;
    @FXML
    private TableColumn<ProductPrime, String> brandC;
    @FXML
    private TableColumn<ProductPrime, String> quantityC;
    @FXML
    private ObservableList<ProductPrime> request;
    @FXML
    private String sql = "select * from ToBeBought";
    @FXML
    private ObservableList<ProductPrime> prdctList;
    @FXML
    private  String sq2 = "select * from ptable";

    //private ListView list;
    //@FXML
    //private Button reqBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(this.smModel.isDBconnected()){
            this.tableConn.setText("GOOD Connection");
        }
        else{this.tableConn.setText("BAD Connection");}
    }
    // second attempt of a getting a request method
    ////////////////////////////////////////////////////////////////////////////////////////
    // notice me the SM can only pull the requests from his/hers branch only
    //work on that
    //ERD work on that
    // we need a product table to work with
    // reply we only need one table and all can pull from that specifically for their branch
    //example storage(for that branch??) that allows the SM to check it against the shelfMan, to update(new Products), to remove(eg.
    // expired products, most of all display the current table

    @FXML
    public void getRequest(ActionEvent e){
        try{
            Connection conn = dbconnection.getConnection();
            //pr = this.smModel..prepareStatement(sql);
            this.request = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                //as long as there are requests to display
                this.request.add(new ProductPrime(rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        }catch(SQLException ex){System.err.println("Error occured :" + ex);}
        //adding to the table
       // this.idC.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>( "ID"));
        this.nameC.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productName"));
        this.brandC.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Brand"));
        this.quantityC.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Quantity"));

        this.reqTable.setItems(null);
        this.reqTable.setItems(this.request);
        //


    }
    // lets look at products of that storage manager
    @FXML
    public void getproductTable(ActionEvent e){
        try{
            Connection conn1 = dbconnection.getConnection();
            this.prdctList = FXCollections.observableArrayList();
            ResultSet rs = conn1.createStatement().executeQuery(sq2);
            while(rs.next()){
                //as long as there are requests to display
                this.prdctList.add(new ProductPrime(rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        }catch(SQLException ex){System.err.println("Error45 occured :" + ex);}
        this.catC1.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Category"));
        this.nameC1.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("productName"));
        this.brandC1.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Brand"));
        this.expC11.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("ExpirationDate"));
        this.priceC111.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("price"));
        this.quantityC1111.setCellValueFactory(new PropertyValueFactory<ProductPrime, String>("Quantity"));

        this.productTable.setItems(null);
        this.productTable.setItems(this.prdctList);

    }
    //another functionality
    // lets say updating the storage with new products or
    // removing products

   /* @FXML
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
        }/////
    }
*/

}
