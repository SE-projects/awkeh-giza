/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerDashboard.Controllers;

import customerDashboard.Model.DBConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Pair;


public class CustomerPage implements Initializable {
    
    String types,locations, query;
    int productId;
    
     ResultSet result;
     Products prod, singleProd, searchProd, cartProd,fixedAmountProd;
     
    @FXML
    private Button home;

    @FXML
    private Button product;

    @FXML
    private Button about;

    @FXML
    private Button help;

    @FXML
    private Text txt;
    
     @FXML
    private Button close;
     
    @FXML
    private VBox header;

    @FXML
    private HBox headerPanel;

    @FXML
    private TextField search;

    @FXML
    private Button searchBtn;

    @FXML
    private HBox addPanel;

    @FXML
    private FontAwesomeIcon cart;

    @FXML
    private HBox addPanel1;

    @FXML
    private FontAwesomeIcon notification;

    @FXML
    private HBox addPanel11;

    @FXML
    private FontAwesomeIcon account;

     @FXML
    private GridPane homegrid;

    @FXML
    private GridPane productgrid;

    @FXML
    private GridPane aboutgrid;
    
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
    private Label prodLocation;

     @FXML
    private Button addToCartBtn;

    @FXML
    private TextField amount;
    
    @FXML
    private FontAwesomeIcon back;

     @FXML
    private GridPane cartgrid;

    @FXML
    private TableView<Products> cartTable;

    @FXML
    private TableColumn<Products, String> id1;

    @FXML
    private TableColumn<Products, String> mname1;

    @FXML
    private TableColumn<Products, String> type1;

    @FXML
    private TableColumn<Products, String> price1;

    @FXML
    private TableColumn<Products, String> location1;

    @FXML
    private TableColumn<Products, String> view1;
    
    @FXML
    private TableColumn<Products, String> view;

    @FXML
    private TableColumn<Products, String> add;
    
    @FXML
    private TableView<Products> table;

    @FXML
    private TableColumn<Products, String> id;

    @FXML
    private TableColumn<Products, String> mname;

    @FXML
    private TableColumn<Products, String> type;

    @FXML
    private TableColumn<Products, String> price;

    @FXML
    private TableColumn<Products, String> location;
    
    private ObservableList<Products> customerArray=FXCollections.observableArrayList();
    private ObservableList<Products> cartArray=FXCollections.observableArrayList();
     private ObservableList<Products> searchArray=FXCollections.observableArrayList();
//    private ObservableList<Products> customerSingleArray=FXCollections.observableArrayList();
    private ArrayList<Products> custSingleArray= new ArrayList<Products>();
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(event.getSource() == home){
            txt.setText("home");
            productgrid.setVisible(false);
            aboutgrid.setVisible(false);
            homegrid.setVisible(true);
            cartgrid.setVisible(false);
            homegrid.toFront();
//            productgrid.toBack();
        }
        else if(event.getSource() == product){
            txt.setText("product");
            homegrid.setVisible(false);
            aboutgrid.setVisible(false);
            cartgrid.setVisible(false);
            productgrid.setVisible(true);
            productgrid.toFront();
            
        }
         else if(event.getSource() == about){
            txt.setText("about");
            productgrid.setVisible(false);
            homegrid.setVisible(false);
            viewgrid.setVisible(false);
            cartgrid.setVisible(false);
            aboutgrid.setVisible(true);
            aboutgrid.toFront();
        }
          else if(event.getSource() == help){
            txt.setText("help");
            homegrid.setVisible(false);
            aboutgrid.setVisible(false);
            viewgrid.setVisible(false);
            productgrid.setVisible(false);
            cartgrid.setVisible(true);
            productgrid.toFront();
            cartArray.clear();
            listProductInCart();
        }
        else if(event.getSource() == viewgrid){
            txt.setText("view");
            productgrid.setVisible(false);
            aboutgrid.setVisible(false);
            cartgrid.setVisible(false);
            homegrid.setVisible(false);
            viewgrid.setVisible(true);
            viewgrid.toFront();

          }
        else if(event.getSource() == searchBtn){
            search.setText("hena");
        }
        
    }
    @FXML
    private void handleClose(MouseEvent event){
        if(event.getSource() == close){
            System.exit(0);
        } 
    }
    
    @FXML
    void backHandle(MouseEvent event) {
         if(event.getSource() == close){
            txt.setText("home");
            homegrid.setVisible(false);
            aboutgrid.setVisible(false);
            viewgrid.setVisible(false);
             productgrid.setVisible(true);
            productgrid.toFront();

        }
    }

     @FXML
    void cartHandler(MouseEvent event) {
        System.out.println("list cart list");
    }
     Timer myTimer = new Timer();
     
     //for addtoCart button in viewgrid
    @FXML
    void handleAddToCart(ActionEvent event) {
         AddToCart();
    }
    
    void AddToCart(){
        DialogPane dialog=new DialogPane();
         
          if(!(amount.getText() == null || amount.getText().length() == 0)){
         try{
         if(DBConnection.connect()){
              System.out.println("ready to add");
         
            query = "insert into cart (productId, productName, productType, price,amount) "
                     + "values('" + singleProd.getProductId()+ "','" + singleProd.getProductName()+ "','" + singleProd.getType()+ "','" + singleProd.getPrice()+ "','" + singleProd.getAmount()+ "')";
                 System.out.println(query);
                 int res =DBConnection.query(query);
                 if( res != -1){
                System.out.println("Your query is executed");
                 productgrid.setVisible(true);
                 viewgrid.setVisible(false);
                 productgrid.toFront();
                txt.setStyle("-fx-background-color: #8A2BE2");
                txt.setStyle("-fx-color : white");
                txt.setText("Successfully added to cart");
                 myTimer.schedule(new TimerTask(){

                @Override
                public void run() {
                     txt.setText("product");
                }
                },3500);
                 }
                 else{
                    Alert alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Info Dialog");
                    alert.setHeaderText("Duplication Error ");
                    alert.setContentText("This item already added!");
//                    alert.setAlertType(AlertType.);
                    alert.setX(510);
                     alert.setY(285);
//                    alert.setHeight(300);
//                     alert.setWidth(400);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.initModality(Modality.WINDOW_MODAL);
                    alert.showAndWait();
                 }
                 
            }   
//            DBConnection.disConnect();
    }
         catch(Exception ex){
             ex.printStackTrace();
         }    
    }
        else{
              System.out.println("amount empty");
              Alert alert = new Alert(AlertType.ERROR);
//              alert.setTitle("Error Dialog");
              alert.setHeaderText("Input Error");
              alert.setContentText("Specify amount of u want add!");
              alert.initStyle(StageStyle.UTILITY);
                alert.setX(510);
                alert.setY(285);
//              alert.setHeight(400);
//              alert.setWidth(500);
              
              alert.setAlertType(AlertType.ERROR);
               alert.initStyle(StageStyle.UNDECORATED);
              alert.initModality(Modality.WINDOW_MODAL);
              alert.showAndWait();
                System.out.println("Your query is not executed");

            }
    
    
    }
    
     void AddSingleToCart(){
        DialogPane dialog=new DialogPane();
         try{
         if(DBConnection.connect()){
              System.out.println("ready to add");
             
            query = "insert into cart (productId, productName, productType, price,amount) "
                     + "values('" + fixedAmountProd.getProductId()+ "','" + fixedAmountProd.getProductName()+ "','" + fixedAmountProd.getType()+ "','" + fixedAmountProd.getPrice()+ "','" + fixedAmountProd.getAmount()+ "')";
                 System.out.println(query);
                 int res =DBConnection.query(query);
                 if( res != -1){
                System.out.println("Your query is executed");
                 productgrid.setVisible(true);
                 viewgrid.setVisible(false);
                 productgrid.toFront();
                txt.setStyle("-fx-background-color: #8A2BE2");
                txt.setStyle("-fx-color : white");
                txt.setText("Successfully added to cart");

                myTimer.schedule(new TimerTask(){

                @Override
                public void run() {
                     txt.setText("product");
                }
                },3500);
                 }
                 else{
                    Alert alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Info Dialog");
                    alert.setHeaderText("Duplication Error ");
                    alert.setContentText("This item already added!");
//                    alert.setAlertType(AlertType.);
                    alert.setX(510);
                     alert.setY(285);
//                    alert.setHeight(300);
//                     alert.setWidth(400);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.initModality(Modality.WINDOW_MODAL);
                    alert.showAndWait();
                 }
            }   
//            DBConnection.disConnect();
    }
         catch(Exception ex){
             ex.printStackTrace();
         }    
   
    
    }
    @FXML
    void handleSearch(ActionEvent event) {
        customerArray.clear();
        if(!(search.getText().isEmpty())){
        System.out.println("search clicked");
        int idNum= Integer.parseInt(search.getText());
        search(idNum);
        displayResult();
        table.setItems(searchArray);
        }
        else{
        selectProduct();
        }
    }
    
    public void listProductInCart(){
        if(DBConnection.connect()){
            System.out.println("connected");
            query="select * from cart";
            result=DBConnection.select(query);
            System.out.println(result);
            try {
                while(result.next()){
                    cartProd= new Products(
                            result.getInt("productId"),
                            result.getString("productName"),
                            result.getString("productType"),
                            result.getDouble("price"),
                            result.getString("amount")
                    
                    );
                    
                    cartArray.add(cartProd);
                    System.out.println("added to cart array");
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerPage.class.getName()).log(Level.SEVERE, null, ex);
            }
             displayCartResult();
             cartTable.setItems(cartArray);
           
           
        }
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        menu1.setText("menu 1");
//        menuItem1.setText("1");
//        menuItem2.setText("2");
//        menuItem3.setText("3");
//        menu1.getItems().add(menuItem1);
//        menu1.getItems().add(menuItem2);
//        menu1.getItems().add(menuItem3);
        
//        menuBar1.getMenus().add(menu1);
//        Menu menu = new Menu("Menu 1");
//        Menu subMenu = new Menu("Menu 1.1");
//        MenuItem menuItem11 = new MenuItem("Item 1.1.1");
//        subMenu.getItems().add(menuItem11);
//        menu.getItems().add(subMenu);
//
//        MenuItem menuItem1 = new MenuItem("Item 1");
//        menu.getItems().add(menuItem1);
//
//        MenuItem menuItem2 = new MenuItem("Item 2");
//        menu.getItems().add(menuItem2);
//        menu1.getMenus().add(menu);
//        home.setGraphic(GlyphsDude.createIcon(FontAwesomeIcons.ARROW_LEFT));
//        addPanel.getChildren().addAll(GlyphsDude.createIcon(FontAwesomeIcons.SHOPPING_CART,"20px"));
//        addPanel.setCenter(GlyphsDude.createIcon(FontAwesomeIcons.SHOPPING_CART,"20px"));
       homegrid.setVisible(true);
       selectProduct();
       listProductInCart();
    }  
    
    public void selectProduct(){
        if(DBConnection.connect()){
            System.out.println("connected");
            query="select * from product";
            result=DBConnection.select(query);
            System.out.println(result);
            try {
                while(result.next()){
                    prod= new Products(
                            result.getInt("productId"),
                            result.getString("productName"),
                            result.getString("type"),
                            result.getDouble("price"),
                            result.getString("location")
                    
                    );
                    
                    customerArray.add(prod);
                    System.out.println("added to array");
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerPage.class.getName()).log(Level.SEVERE, null, ex);
            }
             displayResult();
             table.setItems(customerArray);
           
           
        }
    }
    public ArrayList<Products> getDeatilProduct(int id){
        query="select * from product where productId= '"+ id+"'";
        result=DBConnection.select(query);
       try {
                while(result.next()){
                    singleProd= new Products(
                            result.getInt("productId"),
                            result.getString("productName"),
                            result.getString("type"),
                            result.getDouble("price"),
                            result.getString("location")
                    
                    );
                    
                    custSingleArray.add(prod);
                    System.out.println("single product added to array");
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        return custSingleArray;
    
    }
    public ObservableList<Products> search(int id){
        searchArray.clear();
         try{
              if(DBConnection.connect()){
                 System.out.println("connect"+search);
                query="select * from product where productId= '"+ id+"'";
                result=DBConnection.select(query);
             while(result.next()){
                    searchProd= new Products(
                            result.getInt("productId"),
                            result.getString("productName"),
                            result.getString("type"),
                            result.getDouble("price"),
                            result.getString("location")
                    
                    );
                    searchArray.add(searchProd);
                    System.out.println("added to array");
                    System.out.println("add:"+searchArray);
                   
     
                 }
              
              }}
         catch(SQLException ex){
               ex.getMessage();
         }
         return searchArray;
     
     }
    public void displayResult(){
         view.setCellValueFactory(new PropertyValueFactory<>("VIEW"));

        Callback<TableColumn<Products, String>, TableCell<Products, String>> viewCellFactory = new Callback<TableColumn<Products, String>, TableCell<Products, String>>() {
            @Override
            public TableCell call(final TableColumn<Products, String> param) {
                final TableCell<Products, String> cell = new TableCell<Products, String>() {

                    final Button view_btn = new Button("View");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            view_btn.setOnAction(event -> {
                                 prodName.setText("");
                                 prodType.setText("");
                                 prodPrice.setText("");
                                 prodLocation.setText("");
                                 prodQuantity.setText("");
                                 prodInfo.setText("");
                                 expDate.setText("");
                                Products person = getTableView().getItems().get(getIndex());
                                custSingleArray=getDeatilProduct(person.productId);
                                System.out.println("view clicked");
                                System.out.println(person.productName);
                                System.out.println(custSingleArray.isEmpty());
                                System.out.println(custSingleArray.toString());
                                txt.setText("view");
                                productgrid.setVisible(false);
                                aboutgrid.setVisible(false);
                                homegrid.setVisible(false);
                                viewgrid.setVisible(true);
                                viewgrid.toFront();
                                String pName=custSingleArray.get(0).productName;
                                singleProd= new Products(person.productId,person.productName,person.type,person.price,person.location);
                                prodName.setText(person.productName);
                                 prodType.setText(person.type);
                                 prodPrice.setText(person.price.toString());
                                prodLocation.setText(person.location);
                            });
                            view_btn.setAlignment(Pos.CENTER);
//                          view_btn.setPrefSize(15, 15);
                            view_btn.setStyle("-fx-background-color:#600080");
//                            view_btn.setStyle("-fx-color: white");
                            view_btn.setStyle("margin-left: 3px");
                            view_btn.setStyle("padding-left: 5px");
                            setGraphic(view_btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        add.setCellValueFactory(new PropertyValueFactory<>("ADD"));

        Callback<TableColumn<Products, String>, TableCell<Products, String>> addCellFactory = new Callback<TableColumn<Products, String>, TableCell<Products, String>>() {
            @Override
            public TableCell call(final TableColumn<Products, String> param) {
                final TableCell<Products, String> cell = new TableCell<Products, String>() {

                    final Button add_btn = new Button("Add");
                     

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            add_btn.setOnAction(event -> {
                                Products person = getTableView().getItems().get(getIndex());
                                System.out.println("add clicked");
//                                 fixedAmountProd.setAmount(1);
                                 fixedAmountProd= new Products(person.productId,person.productName,person.type,person.price,1);
                                AddSingleToCart();
                            });
                            
                            add_btn.setAlignment(Pos.CENTER);
//                            add_btn.setPrefSize(15, 15);
                            add_btn.setStyle("-fx-background-color:#600080");
//                            add_btn.setStyle("-fx-color: white");
                            add_btn.setStyle("margin: 3px");
                            add_btn.setStyle("padding-left: 5px");
//                            add_btn.setStyle("-fx-color:white");
                            setGraphic(add_btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

            view.setCellFactory(viewCellFactory);
            add.setCellFactory(addCellFactory);
            id.setCellValueFactory(new PropertyValueFactory<>("productId"));
            mname.setCellValueFactory(new PropertyValueFactory<>("productName"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            location.setCellValueFactory(new PropertyValueFactory<>("location"));
            
           
    }
    
     public void displayCartResult(){
         view.setCellValueFactory(new PropertyValueFactory<>("DELETE"));

        Callback<TableColumn<Products, String>, TableCell<Products, String>> viewFactory = new Callback<TableColumn<Products, String>, TableCell<Products, String>>() {
            @Override
            public TableCell call(final TableColumn<Products, String> param) {
                final TableCell<Products, String> cell = new TableCell<Products, String>() {

                    final Button delete_btn = new Button("delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            delete_btn.setOnAction(event -> {
                                
                                Products person = getTableView().getItems().get(getIndex());
                                custSingleArray=getDeatilProduct(person.productId);
                                System.out.println("delete clicked");
                                System.out.println(person.productName);
                                deleteProductFromCart(person.productId);
                                 cartArray.clear();
                                listProductInCart();
                                
                            });
                            delete_btn.setAlignment(Pos.CENTER);
//                          view_btn.setPrefSize(15, 15);
                            delete_btn.setStyle("-fx-background-color:#600080");
//                            view_btn.setStyle("-fx-color: white");
                            delete_btn.setStyle("margin-left: 3px");
                            delete_btn.setStyle("padding-left: 5px");
                            setGraphic(delete_btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

            view1.setCellFactory(viewFactory);
            id1.setCellValueFactory(new PropertyValueFactory<>("productId"));
            mname1.setCellValueFactory(new PropertyValueFactory<>("productName"));
            type1.setCellValueFactory(new PropertyValueFactory<>("productType"));
            price1.setCellValueFactory(new PropertyValueFactory<>("price"));
            location1.setCellValueFactory(new PropertyValueFactory<>("amount"));
            
           
    }
     
     public void deleteProductFromCart(int index){
         if(DBConnection.connect()){
         query="delete from cart where productId='"+index+"'";
         DBConnection.query(query);
          int res =DBConnection.query(query);
                 if( res != -1){
                System.out.println("Your query is executed");
//                 productgrid.setVisible(true);
//                 viewgrid.setVisible(false);
//                 productgrid.toFront();
                txt.setStyle("-fx-background-color: #8A2BE2");
                txt.setStyle("-fx-color : white");
                txt.setText("deleted from cart");
               
                myTimer.schedule(new TimerTask(){

                @Override
                public void run() {
                     txt.setText("cart");
                }
                },3500);
                 }
                 else{
                    Alert alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Info Dialog");
                    alert.setHeaderText("Delete Error ");
                    alert.setContentText("delete operation unsuccessful!");
                 
                    alert.setX(510);
                     alert.setY(285);
//                    alert.setHeight(300);
//                     alert.setWidth(400);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.initModality(Modality.WINDOW_MODAL);
                    alert.showAndWait();
                 
                 }
         }
     
     }
}

