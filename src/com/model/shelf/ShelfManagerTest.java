package com.model.shelf;

import com.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

//TODO better if the methods are tested individually

public class ShelfManagerTest {

    private ShelfManager shelfManager = new ShelfManager();
    ObservableList<Product> productsList = FXCollections.observableArrayList();

    @Test
    public void createShelfTable() {
        shelfManager.createShelfTable("Shelf1");
    }

    @Test
    public void queryProductsFromShelf() {
        productsList = shelfManager.queryProductsFromShelf("Shelf1");
        productsList.forEach(product-> System.out.println(product));
    }

    @Test
    public void addProductToShelf() {
        Product Coke = new Product();

        Coke.setId(47578698); Coke.setProductName("Coke Soda"); Coke.setCategory("Beverage");Coke.setRating(4.5);
        Coke.setQuantity(150);Coke.setBrandName("Coca Cola");
        Coke.setExpirationDate(LocalDate.of(2020, Month.APRIL, 12)); Coke.setDescription("16.9 Fl Oz");
        Coke.setPrice(20.99);Coke.setShelfNumber(17);Coke.setSupermarketId(4635234);
        Coke.setIsleName("Food&Beverage");

        shelfManager.addProductToShelf("Shelf1",Coke);
    }

    @Test
    public void updateProductInShelf(){
        Product Coke = new Product();

        Coke.setId(47578698); Coke.setProductName("Diet Coke"); Coke.setCategory("Beverage");Coke.setRating(4.3);
        Coke.setQuantity(145);Coke.setBrandName("Coca Cola");
        Coke.setExpirationDate(LocalDate.of(2021, Month.MAY, 10)); Coke.setDescription("2 liter bottle");
        Coke.setPrice(39.99);Coke.setShelfNumber(14);Coke.setSupermarketId(4635234);
        Coke.setIsleName("Food&Beverage");

        shelfManager.updateProductInShelf("Shelf1",Coke);
    }

    @Test
    public void removeProductFromShelf() {
        shelfManager.removeProductFromShelf("Shelf1", 47578698);
    }

    @Test
    public void closeConnection(){
        shelfManager.closeConnection();
    }
}