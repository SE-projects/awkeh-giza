package com.model.central;

import com.model.Product;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface Central {
    ObservableList<Product> getProductsListInCentralStorage(int sortOrder);

    void addProductToCentralStorage(String productName, String category, double rating,
                                    int quantity, String brandName, LocalDate expirationDate,
                                    String description, double price, int shelfNumber);

    void removeProductFromCentralStorage(String productName, String brandName);

    ObservableList<Product> searchProductInCentralStorage(String productSearchText);

    void updateProductInCentralStorage(String productName, String brandName, int quantity);
}
