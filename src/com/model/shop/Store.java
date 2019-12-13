package com.model.shop;

import com.model.Product;
import javafx.collections.ObservableList;

public interface Store {
    ObservableList<Product> getProductListInStorage(String tablename);
    void removeProductFromStorage(String tableName, String productName, String brandName);
}
