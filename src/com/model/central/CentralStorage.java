package com.model.central;

import com.model.DataSource;
import com.model.Product;
import javafx.collections.ObservableList;

import java.time.LocalDate;

//add a search feature, search a product by a brand

public class CentralStorage implements Central {
    private static CentralStorage instance = new CentralStorage();

    private ObservableList<Product> productList;

    private CentralStorage() {
        //
    }

    public static CentralStorage getInstance() {
        return instance;
    }

    public ObservableList<Product> getProductList() {
        return productList;
    }

    //setProductList shouldn't be used
    public void setProductList(ObservableList<Product> productList) {
        this.productList = productList;
    }

    public void addProductToCentralStorage(String productName, String category, double rating,
                                           int quantity, String brandName, LocalDate expirationDate,
                                           String description, double price, int shelfNumber) {
        if (!DataSource.getInstance().open()) {
            System.out.println("Couldn't open DataSource");
            return;
        }

        DataSource.getInstance().insertIntoCentralStorage(productName, category, rating, quantity, brandName,
                expirationDate, description, price, shelfNumber);

        DataSource.getInstance().close();
    }

    public ObservableList<Product> getProductsListInCentralStorage(int sortOrder) {
        if (!DataSource.getInstance().open()) {
            System.out.println("Couldn't open DataSource");
            return null;
        }

        productList = DataSource.getInstance().queryCentralStorage(sortOrder);

        DataSource.getInstance().close();

        return productList;
    }
    //write methods for removal, update, etc

    public void removeProductFromCentralStorage(String productName, String brandName) {
        if (!DataSource.getInstance().open()) {
            System.out.println("Couldn't open DataSource");
            return;
        }
        DataSource.getInstance().removeProductFromCentralStorage(productName, brandName);

        DataSource.getInstance().close();
    }

    //searches by name that matches the keyword entered
    public ObservableList<Product> searchProductInCentralStorage(String productSearchText) {
        if (!DataSource.getInstance().open()) {
            System.out.println("Couldn't open DataSource");
            return null;
        }

        ObservableList<Product> productList = DataSource.getInstance().searchProductInCentralStorage(productSearchText);

        DataSource.getInstance().close();

        return productList;
    }

    public void updateProductInCentralStorage(String productName, String brandName, int quantity){
        if(!DataSource.getInstance().open()){
            System.out.println("Couldn't open DataSource");
            return;
        }
        DataSource.getInstance().updateProductQuantityInCentralStorage(productName, brandName, quantity);

        DataSource.getInstance().close();
    }
}
