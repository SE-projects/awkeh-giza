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
        if (!sq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        sq.createStorageTable(tableName);
        sq.closeConnection();
    }

    public ObservableList<Product> getProductListInStorage(String tableName) {
        if (!sq.establishConnection()) {
            System.out.println("Couldn't connect");
            return null;
        }
        ObservableList<Product> productList = sq.queryProductsInStorage(tableName);
        return productList;
    }

    public void addProductToStorage(String tableName, Product product) {
        if (!sq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        sq.insertIntoStorage(tableName, product);
        sq.closeConnection();
    }

    public void removeProductFromStorage(String tableName, int productId) {
        if (!sq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        sq.removeProductFromStorage(tableName, productId);
        sq.closeConnection();
    }

    public void updateProductInStorage(String tableName, Product product) {
        if (!sq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        sq.updateProductInStorage(tableName, product);
        sq.closeConnection();
    }
}
