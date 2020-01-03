package com.model.central;

import com.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class CentralStorageManagerTest {

    CentralStorageManager abebe = new CentralStorageManager();
    ObservableList<Product> productsList = FXCollections.observableArrayList();

    @Test
    public void createCentralStorageTable() {
        abebe.createCentralStorageTable();
    }

    @Test
    public void getProductsListInCentralStorage() {
        productsList = abebe.getProductsListInCentralStorage(2);
        productsList.forEach(product-> System.out.println(product));
    }

    @Test
    public void addProductToCentralStorage() {
        abebe.addProductToCentralStorage(345234,"Soda Pop", "Beverage", 4.2, 250,
                "Pepsi", LocalDate.of(2020, Month.OCTOBER, 22), "16.9 Fl oz",
                19.99, 15);
    }

    @Test
    public void removeProductFromCentralStorage() {
        abebe.removeProductFromCentralStorage("Soda Pop", "Pepsi");
    }

    //Updates quantity of a product
    @Test
    public void updateProductInCentralStorage() {
        abebe.updateProductInCentralStorage("Soda Pop", "Pepsi", 342);
    }

    @Test
    public void closeConnection() {
        abebe.closeConnection();
    }
}