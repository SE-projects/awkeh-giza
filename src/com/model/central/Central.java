package com.model.central;

import com.model.Product;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface Central {
    ObservableList<Product> getProductsListInCentralStorage(int sortOrder);

    void removeProductFromCentralStorage(String productName, String brandName);

    void updateProductInCentralStorage(String productName, String brandName, int quantity);

    void closeConnection();
}
