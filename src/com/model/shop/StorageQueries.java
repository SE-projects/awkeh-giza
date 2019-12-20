package com.model.shop;

import com.model.Connexion;
import com.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//TODO Add supermarket id as a column
public class StorageQueries {
    private Connection connection;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    private static final int ORDER_BY_NONE = 1;
    private static final int ORDER_BY_DESC = 2;
    private static final int ORDER_BY_ASC = 3;

    String storageTable;
    private static final String COLUMN_STORAGE_PRODUCT_ID = "id";
    private static final String COLUMN_STORAGE_PRODUCT_NAME = "product_name";
    private static final String COLUMN_STORAGE_PRODUCT_CATEGORY = "category";
    private static final String COLUMN_STORAGE_PRODUCT_RATING = "rating";
    private static final String COLUMN_STORAGE_PRODUCT_QUANTITY = "quantity";
    private static final String COLUMN_STORAGE_BRAND_NAME = "brand_name";
    private static final String COLUMN_STORAGE_EXPIRATION_DATE = "expiration_date";
    private static final String COLUMN_STORAGE_PRODUCT_DESCRIPTION = "description";
    private static final String COLUMN_STORAGE_PRODUCT_PRICE = "price";
    private static final String COLUMN_STORAGE_PRODUCT_SHELF_NUMBER = "shelf_number";
    private static final String COLUMN_STORAGE_SUPERMARKET_ID = "supermarket_id";
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
    private static final int INDEX_STORAGE_SUPERMARKET_ID = 11;

    private PreparedStatement queryProductInStorageByNameAndBrand;
    private PreparedStatement insertIntoStorage;
    private PreparedStatement removeProductFromStorage;
    private PreparedStatement updateProductInStorage;


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
            if (updateProductInStorage != null) {
                updateProductInStorage.close();
            }
            if (queryProductInStorageByNameAndBrand != null) {
                queryProductInStorageByNameAndBrand.close();
            }
            if (insertIntoStorage != null) {
                insertIntoStorage.close();
            }
            if (removeProductFromStorage != null) {
                removeProductFromStorage.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close");
        }
    }

    public void createStorageTable(String tableName) {
        String CREATE_STORAGE_TABLE = "CREATE TABLE IF NOT EXISTS " + tableName + '(' + COLUMN_STORAGE_PRODUCT_ID
                + " INTEGER NOT NULL UNIQUE, " + COLUMN_STORAGE_PRODUCT_NAME + " TEXT, " + COLUMN_STORAGE_PRODUCT_CATEGORY +
                " TEXT, " + COLUMN_STORAGE_PRODUCT_RATING + " REAL, " + COLUMN_STORAGE_PRODUCT_QUANTITY + " INTEGER, " +
                COLUMN_STORAGE_BRAND_NAME + " TEXT, " + COLUMN_STORAGE_EXPIRATION_DATE + " TEXT, " +
                COLUMN_STORAGE_PRODUCT_DESCRIPTION + " TEXT, " + COLUMN_STORAGE_PRODUCT_PRICE + " REAL, " + COLUMN_STORAGE_PRODUCT_SHELF_NUMBER
                + " INTEGER, " + COLUMN_STORAGE_SUPERMARKET_ID + " INTEGER, " + " PRIMARY KEY(" + COLUMN_STORAGE_PRODUCT_ID + ')' + ')';
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
                product.setSupermarketId(results.getInt(INDEX_STORAGE_SUPERMARKET_ID));

                productList.add(product);
            }

            return productList;

        } catch (SQLException e) {
            System.out.println("Storage query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertIntoStorage(String tableName, Product product) {
        String QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_STORAGE_PRODUCT_ID +
        " FROM " + tableName + " WHERE " + COLUMN_STORAGE_PRODUCT_NAME + " = ?" + " AND " + COLUMN_STORAGE_BRAND_NAME + " = ?";


        String INSERT_INTO_STORAGE = "INSERT INTO " + tableName +
        '(' + COLUMN_STORAGE_PRODUCT_ID + ", " + COLUMN_STORAGE_PRODUCT_NAME + ", " + COLUMN_STORAGE_PRODUCT_CATEGORY + ", " +
        COLUMN_STORAGE_PRODUCT_RATING + ", " + COLUMN_STORAGE_PRODUCT_QUANTITY + ", " + COLUMN_STORAGE_BRAND_NAME + ", " +
        COLUMN_STORAGE_EXPIRATION_DATE + ", " + COLUMN_STORAGE_PRODUCT_DESCRIPTION + ", " + COLUMN_STORAGE_PRODUCT_PRICE + ", " +
        COLUMN_STORAGE_PRODUCT_SHELF_NUMBER + ", " + COLUMN_STORAGE_SUPERMARKET_ID + ')' +
        " VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
                insertIntoStorage.setInt(11, product.getSupermarketId());

                int affectedRows = insertIntoStorage.executeUpdate();
                if (affectedRows == 1) {
                    System.out.println("Insertion in to storage is successful");
                } else {
                    throw new SQLException("Problem with insertion");
                }

            }
            results.close();
        } catch (SQLException e) {
            System.out.println("insertion failed");
        }
    }

    //    String productName, String brandName
    public void removeProductFromStorage(String tableName, int productId) {
        /*String QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_STORAGE_PRODUCT_ID +
                " FROM " + tableName + " WHERE " + COLUMN_STORAGE_PRODUCT_NAME + " = ?" + " AND " +
                COLUMN_STORAGE_BRAND_NAME + " = ?";*/

        String REMOVE_PRODUCT_FROM_STORAGE = "DELETE FROM " + tableName + " WHERE " +
                COLUMN_STORAGE_PRODUCT_ID + " = ?";

        try {
//            queryProductInStorageByNameAndBrand = connection.prepareStatement(QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND);
            removeProductFromStorage = connection.prepareStatement(REMOVE_PRODUCT_FROM_STORAGE);

          /*  queryProductInStorageByNameAndBrand.setString(1, productName);
            queryProductInStorageByNameAndBrand.setString(2, brandName);
            ResultSet results = queryProductInStorageByNameAndBrand.executeQuery();
            if (!results.next()) {
                System.out.println("The product you specified does not exist");
                return;
            } */
            removeProductFromStorage.setInt(1, productId);
            int affectedRow = removeProductFromStorage.executeUpdate();
            if (affectedRow == 1) {
                System.out.println("Deletion was successful");
            } else {
                throw new SQLException("Problem occurred with deletion");
            }
//            results.close();
        } catch (SQLException e) {
            System.out.println("Deletion was unsuccessful");
        }
    }

    public void updateProductInStorage(String tableName, Product product) {
        String UPDATE_PRODUCT_IN_STORAGE = "UPDATE " + tableName + " SET " + COLUMN_STORAGE_PRODUCT_ID + " = ?, " +
        COLUMN_STORAGE_PRODUCT_NAME + " = ?, " + COLUMN_STORAGE_PRODUCT_CATEGORY + " = ?, " + COLUMN_STORAGE_PRODUCT_RATING
        + " = ?, " + COLUMN_STORAGE_PRODUCT_QUANTITY + " = ?, " + COLUMN_STORAGE_BRAND_NAME + " = ?, " +
        COLUMN_STORAGE_EXPIRATION_DATE + " = ?, " + COLUMN_STORAGE_PRODUCT_DESCRIPTION + " = ?, " + COLUMN_STORAGE_PRODUCT_PRICE
        + " = ?, " + COLUMN_STORAGE_PRODUCT_SHELF_NUMBER + " = ?, " + COLUMN_STORAGE_SUPERMARKET_ID + " = ?";

        try {
            updateProductInStorage = connection.prepareStatement(UPDATE_PRODUCT_IN_STORAGE);
            updateProductInStorage.setInt(1, product.getId());
            updateProductInStorage.setString(2, product.getProductName());
            updateProductInStorage.setString(3, product.getCategory());
            updateProductInStorage.setDouble(4, product.getRating());
            updateProductInStorage.setInt(5, product.getQuantity());
            updateProductInStorage.setString(6, product.getBrandName());
            updateProductInStorage.setString(7, String.format("%s", product.getExpirationDate().format(formatter)));
            updateProductInStorage.setString(8, product.getDescription());
            updateProductInStorage.setDouble(9, product.getPrice());
            updateProductInStorage.setInt(10, product.getShelfNumber());
            updateProductInStorage.setInt(11, product.getSupermarketId());

            int affectedRows = updateProductInStorage.executeUpdate();
            if (affectedRows == 1) {
                System.out.println("Update successful");
            } else {
                throw new SQLException("Update unsuccessful");
            }
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

}
