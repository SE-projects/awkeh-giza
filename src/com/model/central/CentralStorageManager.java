package com.model.central;

import com.model.Product;
import javafx.collections.ObservableList;

import java.time.LocalDate;

//when should the order to distribute be set
public class CentralStorageManager implements Central {
    private boolean orderToDistribute;

    public boolean isOrderToDistribute() {
        return orderToDistribute;
    }

    public void setOrderToDistribute(boolean orderToDistribute) {
        this.orderToDistribute = orderToDistribute;
    }

    //setting the whole list of products shouldn't be used
    public void setProductListInCentralStorage(ObservableList<Product> products) {
        CentralStorage.getInstance().setProductList(products);
    }

    public ObservableList<Product> getProductsListInCentralStorage(int sortOrder) {
        return CentralStorage.getInstance().getProductsListInCentralStorage(sortOrder);
    }

    public void addProductToCentralStorage(String productName, String category, double rating,
                                           int quantity, String brandName, LocalDate expirationDate,
                                           String description, double price, int shelfNumber) {

        CentralStorage.getInstance().addProductToCentralStorage(productName, category, rating, quantity, brandName,
                expirationDate, description, price, shelfNumber);
    }

    public void removeProductFromCentralStorage(String productName, String brandName) {
        CentralStorage.getInstance().removeProductFromCentralStorage(productName, brandName);
    }

    public ObservableList<Product> searchProductInCentralStorage(String productSearchText) {
        return CentralStorage.getInstance().searchProductInCentralStorage(productSearchText);
    }

    public void updateProductInCentralStorage(String productName, String brandName, int quantity){
        CentralStorage.getInstance().updateProductInCentralStorage(productName, brandName, quantity);
    }
}
