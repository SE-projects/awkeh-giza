/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awkeh.giza.Controllers;

public class ProductRequest {
    String productName, productType, productBrand, status,amount;

    int noProd, reqTime;
    ProductRequest (){};
    ProductRequest(String productName,String productType,String productBrand, int noProd){
        this.productName=productName;
        this.productType=productType;
        this.productBrand=productBrand;
        this.noProd=noProd;
    }
      ProductRequest(String productName,String productBrand, String amount, String status){
        this.productName=productName;
        this.productBrand=productBrand;
        this.amount=amount;
        this.status=status;
    }

 
      ProductRequest(String productName,String productType,String productBrand, int noProd, int reqTime){
        this.productName=productName;
        this.productType=productType;
        this.productBrand=productBrand;
        this.noProd=noProd;
        this.reqTime=reqTime;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
     public int getRequestTime() {
        return noProd;
    }

    public void setRequestTime(int reqTime) {
        this.reqTime = reqTime;
    }
       public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
