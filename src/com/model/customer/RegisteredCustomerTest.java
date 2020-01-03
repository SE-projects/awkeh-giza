package com.model.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class RegisteredCustomerTest {

    RegisteredCustomer Joel = new RegisteredCustomer();
    ObservableList<ProductInCart> products = FXCollections.observableArrayList();

    @Test
    public void deleteCustomer() {
        Joel.deleteCustomer("Joel", "fht34");
    }

    @Test
    public void updateCustomerInfo() {
        Joel.updateCustomerInfo("JoelZ", "hfgt@fg", "Joel", "fht34",
                "956445", "joel@gmail.com", "Megenagna", 546, "Yeka", "A.A");
    }

    @Test
    public void createACart() {
        Joel.createACart("A test cart", 9);
    }

    @Test
    public void addProductToCart() {
        Joel.addProductToCart(45633, "Coke Soda", 20, 5, 10, "A test cart",
                9);
    }

    @Test
    public void removeProductFromProduct() {
       Joel.removeProductFromCart(new CartProduct(45633, "Coke Soda", 20,
                5, 10, Joel.createACart("My Cart", 9)));
    }

    @Test
    public void viewCartContents() {
        products = Joel.viewCartContents(new Cart(6, "A test cart", 9), 2);
        products.forEach(product-> System.out.println(product));
    }

    @Test
    public void updateProductInCart() {
        Joel.updateProductInCart(new CartProduct(45633, "Coke Soda",
                20, 10, 200, 6));
    }

    @Test
    public void orderCart() {
        Joel.orderCart(new Cart(6, "A test cart", 9));
    }

    @Test
    public void cancelOrder() {
        Joel.cancelOrder(new Order(4, LocalDate.of(2020, Month.JANUARY, 3), 6,
                9, "NEW", 200));
    }
}