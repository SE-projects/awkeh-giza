package com.model.shelf;

import com.model.Product;
import javafx.collections.ObservableList;

public interface Stock {

    void createShelfTable(String tableName);
    ObservableList<Product> queryProductsFromShelf(String tableName);
    void addProductToShelf(String tableName, Product product);
    void removeProductFromShelf(String tableName, int productId);
    void updateProductInShelf(String tableName, Product product);
    void closeConnection();
}
