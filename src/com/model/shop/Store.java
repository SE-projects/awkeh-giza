package com.model.shop;

import com.model.Product;
import javafx.collections.ObservableList;

public interface Store {

    void createStorageTable(String tableName);

    ObservableList<Product> getProductListInStorage(String tableName);

    void addProductToStorage(String tableName, Product product);

    void removeProductFromStorage(String tableName, int productId);

    void updateProductInStorage(String tableName, Product product);

    void closeConnection();

}
