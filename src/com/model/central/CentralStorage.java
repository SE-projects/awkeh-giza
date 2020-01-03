package com.model.central;

import com.model.Product;
import javafx.collections.ObservableList;

public class CentralStorage implements Central {
    private static CentralStorage instance = new CentralStorage();
    private CentralStorageQueries csq = new CentralStorageQueries();

    private ObservableList<Product> productList;

    //Central Storage has a private constructor
    // because there can only be one object of central storage
    private CentralStorage() {

    }

    public static CentralStorage getInstance() {
        return instance;
    }

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ObservableList<Product> productList) {
        this.productList = productList;
    }

    public void createCentralStorageTable() {
        csq.createCentralStorageTable();
    }

    public void addProductToCentralStorage(Product product) {
        csq.insertIntoCentralStorage(product);
    }

    public ObservableList<Product> getProductsListInCentralStorage(int sortOrder) {
        ObservableList<Product> productList = csq.queryCentralStorage(sortOrder);
        return productList;
    }

    public void removeProductFromCentralStorage(String productName, String brandName) {
        csq.removeProductFromCentralStorage(productName, brandName);
    }

   /* public ObservableList<Product> searchProductInCentralStorage(String productSearchText) {
        ObservableList<Product> productList = csq.searchProductInCentralStorage(productSearchText);
        return productList;
    }*/

    public void updateProductInCentralStorage(String productName, String brandName, int quantity) {
        csq.updateProductQuantityInCentralStorage(productName, brandName, quantity);
    }

    public void closeConnection(){
        csq.closeConnection();
    }
}
