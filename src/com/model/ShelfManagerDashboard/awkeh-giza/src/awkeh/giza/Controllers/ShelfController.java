
package awkeh.giza.Controllers;

import awkeh.giza.Model.DBConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;

import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Pair;


public class ShelfController implements Initializable {
    
    String types,locations, query;
    int productId;
    
     ResultSet result;
     Products providedProduct, providedSingleProduct,singleProduct, searchProd,requestProducts,requestProd,fixedAmountProd;
     ProductRequest prodReq, productRequest;
      
    @FXML
    private Button product;

    @FXML
    private Button request;

    @FXML
    private Button report;
    
    @FXML
    private Button placeProduct;
    
    @FXML
    private TextField reqProName;

    @FXML
    private TextField reqProBrand;

    @FXML
    private TextField reqProAmount;

    @FXML
    private TextField reqProStatus;
    
    @FXML
    private Text txt;
    
     @FXML
    private Button close;
     
    @FXML
    private Button addReq;
     
    @FXML
    private VBox header;

    @FXML
    private HBox headerPanel;

    @FXML
    private TextField search;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField repProName;

    @FXML
    private TextField repProBrand;

    @FXML
    private TextArea repInfo;
    
    @FXML
    private HBox addPanel;

    @FXML
    private HBox addPanel11;

    @FXML
    private FontAwesomeIcon account;

    @FXML
    private GridPane productgrid;

    @FXML
    private GridPane reportgrid;
    
    @FXML
    private GridPane viewgrid;
    
    @FXML
    private Label prodName;

    @FXML
    private Label prodType;
    
    @FXML
    private Label prodQuantity;

    @FXML
    private Label prodPrice;

    @FXML
    private Label prodInfo;

    @FXML
    private Label expDate;

    @FXML
    private Label prodbrand;
    
    @FXML
    private Label prodSource;

  
    @FXML
    private TextField shelfno;
    
    @FXML
    private TextField direction;
    
    @FXML
    private FontAwesomeIcon back;

     @FXML
    private GridPane requestgrid;

    @FXML
    private GridPane reqformgrid;
    
    @FXML
    private TableView<Products> requestTable;

    @FXML
    private TableColumn<Products, String> requestId;

    @FXML
    private TableColumn<Products, String> requestName;

    @FXML
    private TableColumn<Products, String> requestBrand;

    @FXML
    private TableColumn<Products, String> amountNeeded;

    @FXML
    private TableColumn<Products, String> requestStatus;

    @FXML
    private TableColumn<Products, String> delete;
    
    @FXML
    private TableColumn<Products, String> place ;

    @FXML
    private TableView<Products> table;

   @FXML
    private TableColumn<Products, String> mname;

    @FXML
    private TableColumn<Products, String> brand;

    @FXML
    private TableColumn<Products, String> amountno;

    @FXML
    private TableColumn<Products, String> price;

    @FXML
    private TableColumn<Products, String> placeStatus;

    private ObservableList<Products> productInStockArray=FXCollections.observableArrayList();
    private ObservableList<Products> requestArray=FXCollections.observableArrayList();
     private ObservableList<Products> searchArray=FXCollections.observableArrayList();
//    private ObservableList<Products> customerSingleArray=FXCollections.observableArrayList();
    private ArrayList<Products> custSingleArray= new ArrayList<Products>();
    Shelf shelf;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       productgrid.setVisible(true);
       listItemInStock();
       listOfRequest();
    }  
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
     if(event.getSource() == product){
            txt.setText("products");
            addReq.setVisible(false);
            reportgrid.setVisible(false);
            requestgrid.setVisible(false);
            reqformgrid.setVisible(false);
            productgrid.setVisible(true);
            productgrid.toFront();
            
        }
         else if(event.getSource() == request){
            txt.setText("Request");
            addReq.setVisible(true);
            reportgrid.setVisible(false);
            viewgrid.setVisible(false);
            productgrid.setVisible(false);
            reqformgrid.setVisible(false);
            requestgrid.setVisible(true);
            productgrid.toFront();
            requestArray.clear();
            
            listOfRequest();
        }
          else if(event.getSource() == report){
            addReq.setVisible(false);
            txt.setText("Report");
            productgrid.setVisible(false);
            viewgrid.setVisible(false);
            reqformgrid.setVisible(false);
            requestgrid.setVisible(false);
            reportgrid.setVisible(true);
            reportgrid.toFront();
        }
        else if(event.getSource() == viewgrid){
            txt.setText("view Products");
            addReq.setVisible(false);
            productgrid.setVisible(false);
            reportgrid.setVisible(false);
            reqformgrid.setVisible(false);
            requestgrid.setVisible(false);
            viewgrid.setVisible(true);
            viewgrid.toFront();

          }
     
    }
    @FXML
    private void handleClose(MouseEvent event){
        if(event.getSource() == close){
            System.exit(0);
        } 
    }
     @FXML
    void profileClicked(MouseEvent event) {
 
    }
    @FXML
    void backHandle(MouseEvent event) {
        
    }
     @FXML
    void cartHandler(MouseEvent event) {
        System.out.println("list cart list");
    }
     Timer myTimer = new Timer();
  
    public void listItemInStock(){
        if(DBConnection.connect()){
            System.out.println("connected");
            query="select * from stockprovided";
            result=DBConnection.select(query);
            System.out.println(result);
            try {
                while(result.next()){
                    providedProduct= new Products(
                            result.getInt("productId"),
                            result.getString("productName"),
                            result.getString("productType"),
                            result.getString("productBrand"),
                            result.getInt("quantity"),
                             result.getInt("singlePrice"),
                            result.getString("information"),
                            result.getString("source"),
                            result.getString("status")
                    );
                    productInStockArray.add(providedProduct);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ShelfController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            place.setCellValueFactory(new PropertyValueFactory<>("VIEW"));
            Callback<TableColumn<Products, String>, TableCell<Products, String>> viewCellFactory = new Callback<TableColumn<Products, String>, TableCell<Products, String>>() {
            @Override
            public TableCell call(final TableColumn<Products, String> param) {
                final TableCell<Products, String> cell = new TableCell<Products, String>() {

                    final Button place_btn = new Button("putOnPlace");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            place_btn.setOnAction(event -> {
                                 singleProduct = getTableView().getItems().get(getIndex());
                                productgrid.setVisible(false);
                                reportgrid.setVisible(false);
                                viewgrid.setVisible(true);
                                viewgrid.toFront();
                                providedSingleProduct= new Products(singleProduct.productId,singleProduct.productName,singleProduct.type,singleProduct.brand,singleProduct.amount,singleProduct.singlePrice, singleProduct.productInfo,singleProduct.source,singleProduct.status);
                                prodName.setText(singleProduct.productName);
                                 prodType.setText(singleProduct.type);
                                   prodbrand.setText(singleProduct.brand);
                                  prodQuantity.setText(singleProduct.quantity);
//                                 prodPrice.setText(singleProduct.singlePrice.toString());
                                 prodInfo.setText(singleProduct.productInfo);
                                prodSource.setText(singleProduct.status);
                            });
                            place_btn.setAlignment(Pos.CENTER);
                            setGraphic(place_btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

            place.setCellFactory(viewCellFactory);
            mname.setCellValueFactory(new PropertyValueFactory<>("productName"));
            brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            amountno.setCellValueFactory(new PropertyValueFactory<>("amount"));
            price.setCellValueFactory(new PropertyValueFactory<>("singlePrice"));
            placeStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            table.setItems(productInStockArray);
        }
    }
      
    @FXML
    void handleSearch(ActionEvent event) {
        productInStockArray.clear();
        if(!(search.getText().isEmpty())){
        System.out.println("search clicked");
        search(search.getText());
        table.setItems(searchArray);
        }
        else{
        listItemInStock();
        }
    }
    
    public void listOfRequest(){
        if(DBConnection.connect()){
            System.out.println("connected");
            query="select * from requestForStock";
            result=DBConnection.select(query);
            System.out.println(result);
            try {
                while(result.next()){
                    requestProd= new Products(
                            result.getInt("productId"),
                            result.getString("productName"),
                            result.getString("productBrand"),
                            result.getDouble("amount"),
                            result.getString("status")
                    
                    );
                    
                    requestArray.add(requestProd);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ShelfController.class.getName()).log(Level.SEVERE, null, ex);
            }
             delete.setCellValueFactory(new PropertyValueFactory<>("DELETE"));

           Callback<TableColumn<Products, String>, TableCell<Products, String>> viewFactory = new Callback<TableColumn<Products, String>, TableCell<Products, String>>() {
            @Override
            public TableCell call(final TableColumn<Products, String> param) {
                final TableCell<Products, String> cell = new TableCell<Products, String>() {

                    final Button delete_btn = new Button("Delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            delete_btn.setOnAction(event -> {
                                requestProducts = getTableView().getItems().get(getIndex());
                                deleteRequest(requestProducts.productId);
                                requestArray.clear();
                                listOfRequest();
                                
                            });
                            delete_btn.setAlignment(Pos.CENTER);
                            setGraphic(delete_btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
            delete.setCellFactory(viewFactory);
            requestId.setCellValueFactory(new PropertyValueFactory<>("productId"));
            requestName.setCellValueFactory(new PropertyValueFactory<>("productName"));
            requestBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            amountNeeded.setCellValueFactory(new PropertyValueFactory<>("price"));
            requestStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            requestTable.setItems(requestArray);
        }
    }
   
     public void deleteRequest(int index){
         if(DBConnection.connect()){
         query="delete from requestForStock where productId='"+index+"'";
         DBConnection.query(query);
          int res =DBConnection.query(query);
                 if( res != -1){
                txt.setStyle("-fx-background-color: #8A2BE2");
                txt.setStyle("-fx-color : white");
                txt.setText("deleted from cart");
                myTimer.schedule(new TimerTask(){
                @Override
                public void run() {
                     txt.setText("request");
                }
                },3000);
                 }
                 else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Delete Error ");
                    alert.setContentText("delete operation unsuccessful!");
                    alert.setX(510);
                     alert.setY(285);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.initModality(Modality.WINDOW_MODAL);
                    alert.showAndWait();
                 
                 }
         }
     
     }
    public ObservableList<Products> search(String search){
        searchArray.clear();
         try{
              if(DBConnection.connect()){
                 System.out.println("connect"+search);
                query="select * from stockProvided where productId= '"+ search+"'";
                result=DBConnection.select(query);
             while(result.next()){
                   searchProd= new Products(
                            result.getInt("productId"),
                            result.getString("productName"),
                            result.getString("productType"),
                            result.getString("productBrand"),
                            result.getInt("quantity"),
                             result.getInt("singlePrice"),
                            result.getString("information"),
                            result.getString("source"),
                            result.getString("status")
                    );
                    searchArray.add(searchProd);
                                place.setCellValueFactory(new PropertyValueFactory<>("VIEW"));
            Callback<TableColumn<Products, String>, TableCell<Products, String>> viewCellFactory = new Callback<TableColumn<Products, String>, TableCell<Products, String>>() {
            @Override
            public TableCell call(final TableColumn<Products, String> param) {
                final TableCell<Products, String> cell = new TableCell<Products, String>() {

                    final Button place_btn = new Button("putOnPlace");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            place_btn.setOnAction(event -> {
                                 prodName.setText("");
                                 prodType.setText("");
                                 prodPrice.setText("");
                                 prodSource.setText("");
                                 prodQuantity.setText("");
                                 prodInfo.setText("");
                             
                                 singleProduct = getTableView().getItems().get(getIndex());
                                productgrid.setVisible(false);
                                reportgrid.setVisible(false);
                                viewgrid.setVisible(true);
                                viewgrid.toFront();
                                providedSingleProduct= new Products(singleProduct.productId,singleProduct.productName,singleProduct.type,singleProduct.brand,singleProduct.amount,singleProduct.singlePrice, singleProduct.productInfo,singleProduct.source,singleProduct.status);
                                prodName.setText(singleProduct.productName);
                                 prodType.setText(singleProduct.type);
                                 prodbrand.setText(singleProduct.brand);
                                 prodQuantity.setText("2");
                                 prodPrice.setText("120");
                                 prodInfo.setText(singleProduct.productInfo);
                                prodSource.setText(singleProduct.status);
                            });
                            place_btn.setAlignment(Pos.CENTER);
                            setGraphic(place_btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

            place.setCellFactory(viewCellFactory);
            mname.setCellValueFactory(new PropertyValueFactory<>("productName"));
            brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            amountno.setCellValueFactory(new PropertyValueFactory<>("amount"));
            price.setCellValueFactory(new PropertyValueFactory<>("singlePrice"));
            placeStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            table.setItems(productInStockArray);
        
                 }
              
              }}
         catch(SQLException ex){
               ex.getMessage();
         }
         return searchArray;
     }
    
      @FXML
    void placeProductOnShelf(ActionEvent event) {
        String storeDirection=direction.getText();
        String shelfNumber=shelfno.getText();
        shelf=new Shelf(shelfNumber ,storeDirection);
        String location= shelfNumber + "/" +storeDirection ;
        if(!storeDirection.isEmpty() && !shelfNumber .isEmpty()){
            query = "insert into product (productName,type,productBrand,quantity, price,information,location) "
                     + "values('" + singleProduct.getProductName()+ "','" + singleProduct.getType()+ "','" + singleProduct.getBrand()+ "',"
                    + "'" + singleProduct.getAmount()+ "','" + singleProduct.getSinglePrice()+ "','" + singleProduct.getProductInfo()+ "',"
                    + "'"+shelf.updataProductShelfInfo()+"')";
            if(DBConnection.connect()){
                 int response=DBConnection.query(query);
                 if(response != -1){

                    String query2="update stockprovided set status = '"+shelf.updataProductShelfInfo()+"' WHERE productName= '"+singleProduct.getProductName()+"'";
                    int res=DBConnection.query(query2);
                    if(res != -1){
                        productInStockArray.clear();
                        listItemInStock();
                        reportgrid.setVisible(false);
                        requestgrid.setVisible(false);
                        productgrid.setVisible(true);
                        productgrid.toFront();
                    }
                  
                 }
                 myTimer.schedule(new TimerTask(){
                @Override
                public void run() {
                     txt.setText("Product Placed Successfully");
                }
                },3500);
//                   txt.setText("products");
                 }
                 else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Error ");
                    alert.setContentText("This item not placed!!");
                    alert.setX(510);
                     alert.setY(285);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.initModality(Modality.WINDOW_MODAL);
                    alert.showAndWait();
                 
            }
        }
        else{
              Alert alert = new Alert(AlertType.ERROR);
              alert.setHeaderText("Input Error");
              alert.setContentText("The textFields empty!!!");
              alert.initStyle(StageStyle.UTILITY);
                alert.setX(510);
                alert.setY(285);
              alert.setAlertType(AlertType.ERROR);
               alert.initStyle(StageStyle.UNDECORATED);
              alert.initModality(Modality.WINDOW_MODAL);
              alert.showAndWait();
            }

    }
    @FXML
    void addRequest(ActionEvent event) {
         reportgrid.setVisible(false);
         requestgrid.setVisible(false);
         productgrid.setVisible(false);
         reqformgrid.setVisible(true);
    }
    @FXML
    void sendRequest(ActionEvent event) {
         String proName=reqProName.getText();
         String proBrand=reqProBrand.getText();
         String proAmount=reqProAmount.getText();
         String proStatus=reqProStatus.getText();
         productRequest= new ProductRequest(proName,proBrand,proAmount,proStatus);
         System.out.println("1"+proName);
         
             query="insert into requestforstock (productName, productBrand, amount, status) "
                     + "values('"+productRequest.getProductName()+"','"+productRequest.getProductBrand()+"','"+productRequest.getAmount()+"','"+productRequest.getStatus()+"')";
             try{
             if(DBConnection.connect()){
                  System.out.println(productRequest.getProductName());
                  if(!proName.isEmpty() && !proAmount.isEmpty()){
                 int response=DBConnection.query(query);
                   System.out.println(response);
                 if(response !=-1){
                      System.out.println(response);
                     requestArray.clear();
                     listOfRequest();
                     System.out.println("successfully inserted");
                      reportgrid.setVisible(false);
                      productgrid.setVisible(false);
                      requestgrid.setVisible(true);
                      requestgrid.toFront();
                      
                      myTimer.schedule(new TimerTask(){
                @Override
                public void run() {
                     txt.setText("Request Successfully Sent");
                }
                },3500);
                     txt.setText("Request");  
                 }
                 
                 }
                  else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Input Error");
                    alert.setContentText("The textFields empty!!!");
                    alert.initStyle(StageStyle.UTILITY);
                      alert.setX(510);
                      alert.setY(285);
                    alert.setAlertType(AlertType.ERROR);
                     alert.initStyle(StageStyle.UNDECORATED);
                    alert.initModality(Modality.WINDOW_MODAL);
                    alert.showAndWait();
            }
             }}
             catch(Exception ex){
             ex.printStackTrace();
           
         } }
     @FXML
    void sendReport(ActionEvent event) {
         String reportName=repProName.getText();
         String reportBrand=repProBrand.getText();
         String reportReason=repInfo.getText();
         System.out.println("1"+reportName);

             query="insert into report (productName, productBrand,reportReason) "
                     + "values('"+reportName+"','"+reportBrand+"','"+reportReason+"')";
             try{
             if(DBConnection.connect()){
               if(!reportName.isEmpty() && !reportBrand.isEmpty() && !reportReason.isEmpty() ){
                 int response=DBConnection.query(query);
                   System.out.println(response);
                 if(response !=-1){
                      System.out.println(response);
                     requestArray.clear();
                     listOfRequest();
                     System.out.println("successfully inserted");
                      myTimer.schedule(new TimerTask(){
                @Override
                public void run() {
                     txt.setText("Report Successfully Sent");
                }
                },3500);
                     txt.setText("Report");  
                 }
                 
                 
             }
             else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Input Error");
                    alert.setContentText("The textFields empty!!!");
                    alert.initStyle(StageStyle.UTILITY);
                      alert.setX(510);
                      alert.setY(285);
                    alert.setAlertType(AlertType.ERROR);
                     alert.initStyle(StageStyle.UNDECORATED);
                    alert.initModality(Modality.WINDOW_MODAL);
                    alert.showAndWait();
            }}}
             catch(Exception ex){
             ex.printStackTrace();
         
    }}
    }
    

