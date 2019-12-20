package com.model.shelf;

import com.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShelfManager {

    private Shelf shelf = new Shelf();
    ObservableList<Product> productsList = FXCollections.observableArrayList();

    public void createShelfTable(String tableName) {
        shelf.createStorageTable(tableName);
    }

    public ObservableList<Product> queryProductsFromShelf(String tableName){
        productsList = shelf.queryProductsInShelf(tableName);
        return productsList;
    }

    public void addProductToShelf(String tableName, Product product) {

        shelf.addProductToShelf(tableName, product);
    }

    public void removeProductFromShelf(String tableName, int productId){
        shelf.removeProductFromShelf(tableName, productId);
    }

    public void updateProductInShelf(String tableName, Product product){
        shelf.updateProductInShelf(tableName, product);
    }

}
