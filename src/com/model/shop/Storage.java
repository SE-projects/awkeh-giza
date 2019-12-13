package com.model;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Storage {
    private ObservableList<Product> productList;

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ObservableList<Product> productList) {
        this.productList = productList;
    }

    public void addProductToStorage(String tableName, String productName, String category, double rating,
                                    int quantity, String brandName, LocalDate expirationDate,
                                    String description, double price, int shelfNumber){
        if (!DataSource.getInstance().open()) {
            System.out.println("Couldn't open DataSource");
            return;
        }

        DataSource.getInstance().insertIntoStorage(tableName, productName, category, rating, quantity, brandName,
                expirationDate, description, price, shelfNumber);

        DataSource.getInstance().close();
    }

    public ObservableList<Product> getProductListInStorage(String tableName) {
        if (!DataSource.getInstance().open()) {
            System.out.println("Couldn't open DataSource");
            return null;
        }

        productList = DataSource.getInstance().queryProductsInStorage(tableName);

        DataSource.getInstance().close();

        return productList;
    }
}
