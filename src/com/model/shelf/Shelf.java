package com.model.shelf;

import com.model.Product;
import javafx.collections.ObservableList;

public class Shelf implements Stock {
    private ObservableList<Product> productList;
    private ShelfQueries sq = new ShelfQueries();

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ObservableList<Product> productList) {
        this.productList = productList;
    }

    public void createShelfTable(String tableName) {
        sq.createShelfTable(tableName);
    }

    public ObservableList<Product> queryProductsFromShelf(String tableName) {
        ObservableList<Product> productList = sq.queryProductsInShelf(tableName);
        return productList;
    }

    public void addProductToShelf(String tableName, Product product) {
        sq.insertIntoShelf(tableName, product);
    }

    public void removeProductFromShelf(String tableName, int productId) {
        sq.removeProductFromShelf(tableName, productId);
    }

    public void updateProductInShelf(String tableName, Product product) {
        sq.updateProductInShelf(tableName, product);
    }

    public void closeConnection() {
        sq.closeConnection();
    }
}
