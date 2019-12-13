package com.model.shop;

import com.model.Product;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class StorageManager implements Store{
    private boolean notifyShelfManagerOfNewEntries;
    private Storage storage = new Storage();

    public void createStorageTable(String tableName) {
        storage.createStorageTable(tableName);
    }

    public void addProductToStorage(String tableName, int id, String productName, String category, double rating,
                                    int quantity, String brandName, LocalDate expirationDate,
                                    String description, double price, int shelfNumber) {
        Product product = new Product();
        product.setId(id);
        product.setProductName(productName);
        product.setCategory(category);
        product.setRating(rating);
        product.setQuantity(quantity);
        product.setBrandName(brandName);
        product.setExpirationDate(expirationDate);
        product.setDescription(description);
        product.setPrice(price);
        product.setShelfNumber(shelfNumber);

        storage.addProductToStorage(tableName, product);
    }

    public ObservableList<Product> getProductListInStorage(String tablename){
        return storage.getProductListInStorage(tablename);
    }

    public void removeProductFromStorage(String tableName, String productName, String brandName){
        storage.removeProductFromStorage(tableName, productName, brandName);
    }
}
