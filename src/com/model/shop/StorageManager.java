package com.model.shop;

import com.model.Product;
import javafx.collections.ObservableList;

public class StorageManager implements Store{
    private boolean notifyShelfManagerOfNewEntries;
    private Storage storage = new Storage();

    public void createStorageTable(String tableName) {
        storage.createStorageTable(tableName);
    }

    public void addProductToStorage(String tableName, Product product) {

        storage.addProductToStorage(tableName, product);
    }

    public ObservableList<Product> getProductListInStorage(String tableName){
        return storage.getProductListInStorage(tableName);
    }

    public void removeProductFromStorage(String tableName, int productId){
        storage.removeProductFromStorage(tableName, productId);
    }

    public void updateProductInStorage(String tableName, Product product){
        storage.updateProductInStorage(tableName, product);
    }
}
