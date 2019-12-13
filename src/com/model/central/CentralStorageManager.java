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

    public void createCentralStorageTable() {
        CentralStorage.getInstance().createCentralStorageTable();
    }

    public ObservableList<Product> getProductsListInCentralStorage(int sortOrder) {
        ObservableList<Product> productList = CentralStorage.getInstance().getProductsListInCentralStorage(sortOrder);
        if (productList == null) {
            System.out.println("The db query ended with null result");
            return null;
        }
        return productList;
    }

    public void addProductToCentralStorage(int id, String productName, String category, double rating,
                                           int quantity, String brandName, LocalDate expirationDate,
                                           String description, double price, int shelfNumber) {

        Product product = new Product();
        product.setId(id);
        product.setProductName(productName);
        product.setCategory(category);
        product.setRating(rating);
        product.setQuantity(quantity);
        product.setBrandName(brandName);
        product.setExpirationDate(expirationDate);
        product.setDescription(description);
        product.setPrice(price);
        product.setShelfNumber(shelfNumber);

        CentralStorage.getInstance().addProductToCentralStorage(product);
    }

    public void removeProductFromCentralStorage(String productName, String brandName) {
        CentralStorage.getInstance().removeProductFromCentralStorage(productName, brandName);
    }

    public ObservableList<Product> searchProductInCentralStorage(String productSearchText) {
        return CentralStorage.getInstance().searchProductInCentralStorage(productSearchText);
    }

    public void updateProductInCentralStorage(String productName, String brandName, int quantity) {
        CentralStorage.getInstance().updateProductInCentralStorage(productName, brandName, quantity);
    }
}
