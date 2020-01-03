package com.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class ProductPrime {

   // private final StringProperty ID;
    private final StringProperty productName;
    private final StringProperty Brand;
    private final StringProperty Quantity;
    private final StringProperty Category;
    //private final StringProperty Rating;
    private final StringProperty ExpirationDate;
    //private final StringProperty iselNo;
    //private final StringProperty shelfNo;
    //private final StringProperty branch;
    private final StringProperty price;

    public ProductPrime(String pName, String quantity, String brand){
        //this.ID = new SimpleStringProperty(id);
        this.productName = new SimpleStringProperty(pName);
        this.Quantity = new SimpleStringProperty(quantity);
        this.Brand = new SimpleStringProperty(brand);
        this.Category = null;
       // this.Rating = null;
        this.ExpirationDate = null;
        //this.iselNo = null;
        //this.shelfNo = null;
        //this.branch = null;
        this.price = null;
    }
    public ProductPrime(String Pname, String categ, String quant, String brand, String expDate, String price){
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
