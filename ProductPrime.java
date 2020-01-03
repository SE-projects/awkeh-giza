package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class ProductPrime {

    private StringProperty customerID;
    private StringProperty productID;
    private StringProperty productName;
    private StringProperty Brand;
    private StringProperty Quantity;
    private StringProperty Category;
    private StringProperty Rating;
    private StringProperty ExpirationDate;
    //private final StringProperty iselNo;
    //private final StringProperty shelfNo;
    //private final StringProperty branch;
    private StringProperty price;
    private StringProperty Totprice;


    //for request table
    public ProductPrime(String pName, String quantity, String brand) {
        //this.ID = new SimpleStringProperty(id);

        this.productName = new SimpleStringProperty(pName);
        this.Quantity = new SimpleStringProperty(quantity);
        this.Brand = new SimpleStringProperty(brand);
       // this.Category = null;
        // this.Rating = null;
        //this.ExpirationDate = null;
       // this.productID = null;
        //this.customerID = null;
        //this.Totprice = null;
        //this.iselNo = null;
        //this.shelfNo = null;
        //this.branch = null;
        //this.price = null;
    }

    //for branch storage
    public ProductPrime(String Pname, String categ, String quant, String brand, String expDate, String price) {
        //this.ID = new SimpleStringProperty(id);
        this.productName = new SimpleStringProperty(Pname);
        this.Category = new SimpleStringProperty(categ);
        // this.Rating = new SimpleStringProperty(rating);
        this.Quantity = new SimpleStringProperty(quant);
        this.Brand = new SimpleStringProperty(brand);
        this.ExpirationDate = new SimpleStringProperty(expDate);
        this.price = new SimpleStringProperty(price);
        //this.iselNo = new SimpleStringProperty(islenum);
        //this.shelfNo = new SimpleStringProperty(shelfnum);
        //this.branch = new SimpleStringProperty(branch);
    }

    //for order
    public ProductPrime(String cID, String pID, String pr, String quan, String tpr){
        this.customerID = new SimpleStringProperty(cID);
        this.productID = new SimpleStringProperty(pID);
        this.price = new SimpleStringProperty(pr);
        this.Quantity = new SimpleStringProperty(quan);
        this.Totprice = new SimpleStringProperty(tpr);
    }
    //for central storage
    public ProductPrime(String pID, String pname, String pcat, String brnd, String rt, String qn, String exp, String pr){
        this.productID = new SimpleStringProperty(pID);
        this.productName = new SimpleStringProperty(pname);
        this.Category = new SimpleStringProperty(pcat);
        this.Brand = new SimpleStringProperty(brnd);
        this.Rating = new SimpleStringProperty(rt);
        this.Quantity = new SimpleStringProperty(qn);
        this.ExpirationDate = new SimpleStringProperty(exp);
        this.price = new SimpleStringProperty(pr);
    }

    public String getRating() {
        return Rating.get();
    }

    public StringProperty ratingProperty() {
        return Rating;
    }

    public void setRating(String rating) {
        this.Rating.set(rating);
    }

    public String getCustomerID() {
        return customerID.get();
    }

    public StringProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID.set(customerID);
    }

    public String getProductID() {
        return productID.get();
    }

    public StringProperty productIDProperty() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID.set(productID);
    }

    public String getTotprice() {
        return Totprice.get();
    }

    public StringProperty totpriceProperty() {
        return Totprice;
    }

    public void setTotprice(String totprice) {
        this.Totprice.set(totprice);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getBrand() {
        return Brand.get();
    }

    public StringProperty brandProperty() {
        return Brand;
    }

    public void setBrand(String brand) {
        this.Brand.set(brand);
    }

    public String getQuantity() {
        return Quantity.get();
    }

    public StringProperty quantityProperty() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        this.Quantity.set(quantity);
    }

    public String getCategory() {
        return Category.get();
    }

    public StringProperty categoryProperty() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category.set(category);
    }

    public String getExpirationDate() {
        return ExpirationDate.get();
    }

    public StringProperty expirationDateProperty() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.ExpirationDate.set(expirationDate);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}




