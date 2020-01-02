package com.model.shop;

import com.model.Product;
import javafx.collections.ObservableList;

public class Storage implements Store {
    private ObservableList<Product> productList;
    private StorageQueries sq = new StorageQueries();

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ObservableList<Product> productList) {
        this.productList = productList;
    }

    public void createStorageTable(String tableName) {
        sq.createStorageTable(tableName);
    }

    public ObservableList<Product> getProductListInStorage(String tableName) {
        ObservableList<Product> productList = sq.queryProductsInStorage(tableName);
        return productList;
    }

    public void addProductToStorage(String tableName, Product product) {
        sq.insertIntoStorage(tableName, product);
    }

    public void removeProductFromStorage(String tableName, int productId) {
        sq.removeProductFromStorage(tableName, productId);
    }

    public void updateProductInStorage(String tableName, Product product) {
        sq.updateProductInStorage(tableName, product);
    }

    public void closeConnection(){
        sq.closeConnection();
    }
}
