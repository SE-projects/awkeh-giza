package com.model.shop;

import com.model.Product;
import com.model.shelf.ShelfManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class StorageManagerTest {

    private StorageManager storageManager = new StorageManager();
    ObservableList<Product> productsList = FXCollections.observableArrayList();

    @Test
    public void createStorageTable() {
        storageManager.createStorageTable("Storage1");
    }

    @Test
    public void addProductToStorage() {
        Product Coke = new Product();

        Coke.setId(47578698);
        Coke.setProductName("Coke Soda");
        Coke.setCategory("Beverage");
        Coke.setRating(4.5);
        Coke.setQuantity(150);
        Coke.setBrandName("Coca Cola");
        Coke.setExpirationDate(LocalDate.of(2020, Month.APRIL, 12));
        Coke.setDescription("16.9 Fl Oz");
        Coke.setPrice(20.99);
        Coke.setShelfNumber(17);
        Coke.setSupermarketId(4635234);
        Coke.setIsleName("Food&Beverage");

        storageManager.addProductToStorage("Storage1", Coke);
    }

    @Test
    public void getProductListInStorage() {
        productsList = storageManager.getProductListInStorage("Storage1");
        productsList.forEach(product -> System.out.println(product));
    }

    @Test
    public void updateProductInStorage() {
        Product Coke = new Product();

        Coke.setId(47578698);
        Coke.setProductName("Diet Coke");
        Coke.setCategory("Beverage");
        Coke.setRating(4.3);
        Coke.setQuantity(145);
        Coke.setBrandName("Coca Cola");
        Coke.setExpirationDate(LocalDate.of(2021, Month.MAY, 10));
        Coke.setDescription("2 liter bottle");
        Coke.setPrice(39.99);
        Coke.setShelfNumber(14);
        Coke.setSupermarketId(4635234);
        Coke.setIsleName("Food&Beverage");

        storageManager.updateProductInStorage("Storage1", Coke);
    }

    @Test
    public void removeProductFromStorage() {
        storageManager.removeProductFromStorage("Storage1", 47578698);
    }

    @Test
    public void closeConnection() {
        storageManager.closeConnection();
    }

}