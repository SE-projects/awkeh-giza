package com.model.shop;

import com.model.Connexion;
import com.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StorageQueries {
    private Connection connection;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    private static final int ORDER_BY_NONE = 1;
    private static final int ORDER_BY_DESC = 2;
    private static final int ORDER_BY_ASC = 3;

    String storageTable;
    private static final String COLUMN_STORAGE_ID = "id";
    private static final String COLUMN_STORAGE_PRODUCT_NAME = "product_name";
    private static final String COLUMN_STORAGE_CATEGORY = "category";
    private static final String COLUMN_STORAGE_RATING = "rating";
    private static final String COLUMN_STORAGE_QUANTITY = "quantity";
    private static final String COLUMN_STORAGE_BRAND_NAME = "brand_name";
    private static final String COLUMN_STORAGE_EXPIRATION_DATE = "expiration_date";
    private static final String COLUMN_STORAGE_DESCRIPTION = "description";
    private static final String COLUMN_STORAGE_PRICE = "price";
    private static final String COLUMN_STORAGE_SHELF_NUMBER = "shelf_number";
    private static final int INDEX_STORAGE_ID = 1;
    private static final int INDEX_STORAGE_PRODUCT_NAME = 2;
    private static final int INDEX_STORAGE_CATEGORY = 3;
    private static final int INDEX_STORAGE_RATING = 4;
    private static final int INDEX_STORAGE_QUANTITY = 5;
    private static final int INDEX_STORAGE_BRAND_NAME = 6;
    private static final int INDEX_STORAGE_EXPIRATION_DATE = 7;
    private static final int INDEX_STORAGE_DESCRIPTION = 8;
    private static final int INDEX_STORAGE_PRICE = 9;
    private static final int INDEX_STORAGE_SHELF_NUMBER = 10;



    private PreparedStatement queryProductInStorageByNameAndBrand;
    private PreparedStatement insertIntoStorage;
    private PreparedStatement removeProductFromStorage;


    public boolean establishConnection() {
        connection = Connexion.getInstance().getConnection();
        if (connection == null) {
            System.out.println("Connection to database couldn't be established");
            return false;
        }
        return true;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close");
        }
    }

    public void createStorageTable(String tableName) {
        String CREATE_STORAGE_TABLE = "CREATE TABLE IF NOT EXISTS " + tableName + '(' + COLUMN_STORAGE_ID
                + " INTEGER NOT NULL UNIQUE, " + COLUMN_STORAGE_PRODUCT_NAME + " TEXT, " + COLUMN_STORAGE_CATEGORY +
                " TEXT, " + COLUMN_STORAGE_RATING + " REAL, " + COLUMN_STORAGE_QUANTITY + " INTEGER, " +
                COLUMN_STORAGE_BRAND_NAME + " TEXT, " + COLUMN_STORAGE_EXPIRATION_DATE + " TEXT, " +
                COLUMN_STORAGE_DESCRIPTION + " TEXT, " + COLUMN_STORAGE_PRICE + " REAL, " + COLUMN_STORAGE_SHELF_NUMBER
                + " INTEGER, PRIMARY KEY(" + COLUMN_STORAGE_ID + ')' + ')';
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_STORAGE_TABLE);
        } catch (SQLException e) {
            System.out.println("Couldn't create table: " + e.getMessage());
        }
    }

    public ObservableList<Product> queryProductsInStorage(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(sql)) {
            while (results.next()) {
                Product product = new Product();
                product.setId(results.getInt(INDEX_STORAGE_ID));
                product.setProductName(results.getString(INDEX_STORAGE_PRODUCT_NAME));
                product.setCategory(results.getString(INDEX_STORAGE_CATEGORY));
                product.setRating(results.getDouble(INDEX_STORAGE_RATING));
                product.setQuantity(results.getInt(INDEX_STORAGE_QUANTITY));
                product.setBrandName(results.getString(INDEX_STORAGE_BRAND_NAME));
                String dateString = results.getString(INDEX_STORAGE_EXPIRATION_DATE);
                LocalDate date = LocalDate.parse(dateString, formatter);
                product.setExpirationDate(date);
                product.setDescription(results.getString(INDEX_STORAGE_DESCRIPTION));
                product.setPrice(results.getDouble(INDEX_STORAGE_PRICE));
                product.setShelfNumber(results.getInt(INDEX_STORAGE_SHELF_NUMBER));

                productList.add(product);
            }

            return productList;

        } catch (SQLException e) {
            System.out.println("Storage query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertIntoStorage(String tableName, Product product) {
        String QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_STORAGE_ID +
                " FROM " + tableName + " WHERE " + COLUMN_STORAGE_PRODUCT_NAME + " = ?" + " AND " + COLUMN_STORAGE_BRAND_NAME + " = ?";


        String INSERT_INTO_STORAGE = "INSERT INTO " + tableName +
        '(' + COLUMN_STORAGE_ID + ", " + COLUMN_STORAGE_PRODUCT_NAME + ", " + COLUMN_STORAGE_CATEGORY + ", " +
        COLUMN_STORAGE_RATING + ", " + COLUMN_STORAGE_QUANTITY + ", " + COLUMN_STORAGE_BRAND_NAME + ", " +
        COLUMN_STORAGE_EXPIRATION_DATE + ", " + COLUMN_STORAGE_DESCRIPTION + ", " + COLUMN_STORAGE_PRICE + ", " +
        COLUMN_STORAGE_SHELF_NUMBER + ')' + " VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            queryProductInStorageByNameAndBrand = connection.prepareStatement(QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND);
            insertIntoStorage = connection.prepareStatement(INSERT_INTO_STORAGE);

            queryProductInStorageByNameAndBrand.setString(1, product.getProductName());
            queryProductInStorageByNameAndBrand.setString(2, product.getBrandName());
            ResultSet results = queryProductInStorageByNameAndBrand.executeQuery();
            if (results.next()) {
                System.out.println("The product already exists");
                return;
            } else {
                insertIntoStorage.setInt(1, product.getId());
                insertIntoStorage.setString(2, product.getProductName());
                insertIntoStorage.setString(3, product.getCategory());
                insertIntoStorage.setDouble(4, product.getRating());
                insertIntoStorage.setInt(5, product.getQuantity());
                insertIntoStorage.setString(6, product.getBrandName());
                insertIntoStorage.setString(7, String.format("%s", product.getExpirationDate().format(formatter)));
                insertIntoStorage.setString(8, product.getDescription());
                insertIntoStorage.setDouble(9, product.getPrice());
                insertIntoStorage.setInt(10, product.getShelfNumber());

                int affectedRows = insertIntoStorage.executeUpdate();
                if (affectedRows == 1) {
                    System.out.println("Insertion in to central storage is successful");
                } else {
                    throw new SQLException("Problem with insertion");
                }

            }

        } catch (SQLException e) {
            System.out.println("insertion failed");
        }
    }

    public void removeProductFromCentralStorage(String tableName, String productName, String brandName) {
        String QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_STORAGE_ID +
        " FROM " + tableName + " WHERE " + COLUMN_STORAGE_PRODUCT_NAME + " = ?" + " AND " + COLUMN_STORAGE_BRAND_NAME + " = ?";

        String REMOVE_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND = "DELETE FROM " + tableName + " WHERE " +
                COLUMN_STORAGE_PRODUCT_NAME + " = ?" + " AND " + COLUMN_STORAGE_BRAND_NAME + " = ?";

        try {
            queryProductInStorageByNameAndBrand = connection.prepareStatement(QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND);
            removeProductFromStorage = connection.prepareStatement(REMOVE_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND);

            queryProductInStorageByNameAndBrand.setString(1, productName);
            queryProductInStorageByNameAndBrand.setString(2, brandName);
            ResultSet results = queryProductInStorageByNameAndBrand.executeQuery();
            if (!results.next()) {
                System.out.println("The product you specified does not exist");
                return;
            } else {
                removeProductFromStorage.setString(1, productName);
                removeProductFromStorage.setString(2, brandName);
                int affectedRow = removeProductFromStorage.executeUpdate();
                if (affectedRow == 1) {
                    System.out.println("Deletion was successful");
                } else {
                    throw new SQLException("Problem occurred with deletion");
                }
            }

        } catch (SQLException e) {
            System.out.println("Deletion was unsuccessful");
        }
    }

}
