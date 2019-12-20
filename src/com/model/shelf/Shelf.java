package com.model.shelf;

import com.model.Product;
import javafx.collections.ObservableList;

public class Shelf {
    private ObservableList<Product> productList;
    private ShelfQueries sq = new ShelfQueries();

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
        sq.createShelfTable(tableName);
        sq.closeConnection();
    }

    public ObservableList<Product> queryProductsInShelf(String tableName){
        if(!sq.establishConnection()){
            System.out.println("Couldn't establish connection");
            return null;
        }

        ObservableList<Product> productList = sq.queryProductsInShelf(tableName);

        sq.closeConnection();
        return productList;
    }

    public void addProductToShelf(String tableName, Product product) {
        if (!sq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        sq.insertIntoShelf(tableName, product);
        sq.closeConnection();
    }

    public void removeProductFromShelf(String tableName, int productId) {
        if (!sq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        sq.removeProductFromShelf(tableName, productId);
        sq.closeConnection();
    }

    public void updateProductInShelf(String tableName, Product product) {
        if (!sq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        sq.updateProductInShelf(tableName, product);
        sq.closeConnection();
    }
}
