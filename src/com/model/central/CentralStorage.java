package com.model.central;

import com.model.Connexion;
import com.model.DataSource;
import com.model.Product;
import javafx.collections.ObservableList;

import java.time.LocalDate;

//add a search feature, search a product by a brand

public class CentralStorage implements Central {
    private static CentralStorage instance = new CentralStorage();
    private CentralStorageQueries csq = new CentralStorageQueries();

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

  /*  public void addProductToCentralStorage(Product product) {
        if (!DataSource.getInstance().open()) {
            System.out.println("Couldn't open DataSource");
            return;
        }

        DataSource.getInstance().insertIntoCentralStorage(product);

        DataSource.getInstance().close();
    }*/

    public void createCentralStorageTable() {
        if (!csq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        csq.createCentralStorageTable();
        csq.closeConnection();
    }

    public void addProductToCentralStorage(Product product) {
        if (!csq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        csq.insertIntoCentralStorage(product);
//        csq.closeConnection();
    }

    public ObservableList<Product> getProductsListInCentralStorage(int sortOrder) {
        if (!csq.establishConnection()) {
            System.out.println("Couldn't connect");
            return null;
        }
        ObservableList<Product> productList = csq.queryCentralStorage(sortOrder);
//        csq.closeConnection();
        return productList;
    }

    public void removeProductFromCentralStorage(String productName, String brandName) {
        if (!csq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        csq.removeProductFromCentralStorage(productName, brandName);
//        csq.closeConnection();
    }

    //searches by name that matches the keyword entered
    public ObservableList<Product> searchProductInCentralStorage(String productSearchText) {
        if (!csq.establishConnection()) {
            System.out.println("Couldn't connect");
            return null;
        }
        ObservableList<Product> productList = csq.searchProductInCentralStorage(productSearchText);
//        csq.closeConnection();
        return productList;
    }

    public void updateProductInCentralStorage(String productName, String brandName, int quantity) {
        if (!csq.establishConnection()) {
            System.out.println("Couldn't connect");
            return;
        }
        csq.updateProductQuantityInCentralStorage(productName, brandName, quantity);

//        csq.closeConnection();
    }
}
