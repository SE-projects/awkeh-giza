/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awkeh.giza.Controllers;

public class Products {
    
    String productName, type,location, query;
    String brand,rating,quantity,category,productInfo,expiryDate,shelf,isle,source, status;
    public int productId,amount,singlePrice;
    Double price;
    
    Products(){}
    Products(int productId, String productName, String type,Double price, String location ){
        this.productId=productId;
        this.productName=productName;
        this.type=type;
        this.price=price;
        this.location=location;
    
    }
     Products(int productId,String productName,String brand, int amount,String status ){
       this.productId=productId;
       this.productName=productName;
       this.brand=brand;
       this.amount=amount;
       this.status=status;
    }
    
    Products(int productId, String productName, String type,Double price ){
        this.productId=productId;
        this.productName=productName;
        this.type=type;
        this.price=price;
    
    }
    Products(int productId, String productName, String type,Double price, int amount ){
        this.productId=productId;
        this.productName=productName;
        this.type=type;
        this.price=price;
        this.amount=amount;
    
    }
     Products(int productId,String productName, String type,String brand, int amount,int singlePrice, String productInfo, String source,String status ){
       this.productName=productName;
       this.type=type;
       this.brand=brand;
       this.amount=amount;
       this.singlePrice=singlePrice;
       this.productInfo=productInfo;
       this.source=source;
       this.status=status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(int singlePrice) {
        this.singlePrice = singlePrice;
    }
    Products(String brand, String rating, String quantity, String productInfo, String shelf,String isle, int amount, String category,String expiryDate ){
       this.brand=brand;
       this.rating=rating;
       this.quantity=quantity;
       this.productInfo=productInfo;
       this.shelf=shelf;
       this.isle=isle;
       this.amount=amount;
       this.category=category;
       this.expiryDate=expiryDate;
    
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
    
    
}
