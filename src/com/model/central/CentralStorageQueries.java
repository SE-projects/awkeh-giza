package com.model.central;

import com.model.Connexion;
import com.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//TODO There should be a functionality to update every field
public class CentralStorageQueries {
    private Connection connection;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    private static final int ORDER_BY_NONE = 1;
    private static final int ORDER_BY_DESC = 2;
    private static final int ORDER_BY_ASC = 3;

    private static final String TABLE_CENTRAL_STORAGE = "Central_Storage";
    private static final String COLUMN_CENTRAL_STORAGE_ID = "id";
    private static final String COLUMN_CENTRAL_STORAGE_PRODUCT_NAME = "product_name";
    private static final String COLUMN_CENTRAL_STORAGE_CATEGORY = "category";
    private static final String COLUMN_CENTRAL_STORAGE_RATING = "rating";
    private static final String COLUMN_CENTRAL_STORAGE_QUANTITY = "quantity";
    private static final String COLUMN_CENTRAL_STORAGE_BRAND_NAME = "brand_name";
    private static final String COLUMN_CENTRAL_STORAGE_EXPIRATION_DATE = "expiration_date";
    private static final String COLUMN_CENTRAL_STORAGE_DESCRIPTION = "description";
    private static final String COLUMN_CENTRAL_STORAGE_PRICE = "price";
    private static final String COLUMN_CENTRAL_STORAGE_SHELF_NUMBER = "shelf_number";
    private static final int INDEX_CENTRAL_STORAGE_ID = 1;
    private static final int INDEX_CENTRAL_STORAGE_PRODUCT_NAME = 2;
    private static final int INDEX_CENTRAL_STORAGE_CATEGORY = 3;
    private static final int INDEX_CENTRAL_STORAGE_RATING = 4;
    private static final int INDEX_CENTRAL_STORAGE_QUANTITY = 5;
    private static final int INDEX_CENTRAL_STORAGE_BRAND_NAME = 6;
    private static final int INDEX_CENTRAL_STORAGE_EXPIRATION_DATE = 7;
    private static final int INDEX_CENTRAL_STORAGE_DESCRIPTION = 8;
    private static final int INDEX_CENTRAL_STORAGE_PRICE = 9;
    private static final int INDEX_CENTRAL_STORAGE_SHELF_NUMBER = 10;

    private PreparedStatement queryProductInCentralStorage;
    private PreparedStatement insertIntoCentralStorage;
    private PreparedStatement removeProductFromCentralStorage;
    private PreparedStatement searchProductFromCentralStorageByName;
    private PreparedStatement updateProductQuantityInCentralStorage;

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
            if (updateProductQuantityInCentralStorage != null) {
                updateProductQuantityInCentralStorage.close();
            }
            if (searchProductFromCentralStorageByName != null) {
                searchProductFromCentralStorageByName.close();
            }
            if (removeProductFromCentralStorage != null) {
                removeProductFromCentralStorage.close();
            }
            if (insertIntoCentralStorage != null) {
                insertIntoCentralStorage.close();
            }
            if (queryProductInCentralStorage != null) {
                queryProductInCentralStorage.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createCentralStorageTable() {
        String CREATE_CENTRAL_STORAGE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CENTRAL_STORAGE + '(' + COLUMN_CENTRAL_STORAGE_ID
                + " INTEGER NOT NULL UNIQUE, " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " TEXT, " + COLUMN_CENTRAL_STORAGE_CATEGORY +
                " TEXT, " + COLUMN_CENTRAL_STORAGE_RATING + " REAL, " + COLUMN_CENTRAL_STORAGE_QUANTITY + " INTEGER, " +
                COLUMN_CENTRAL_STORAGE_BRAND_NAME + " TEXT, " + COLUMN_CENTRAL_STORAGE_EXPIRATION_DATE + " TEXT, " +
                COLUMN_CENTRAL_STORAGE_DESCRIPTION + " TEXT, " + COLUMN_CENTRAL_STORAGE_PRICE + " REAL, " + COLUMN_CENTRAL_STORAGE_SHELF_NUMBER
                + " INTEGER, PRIMARY KEY(" + COLUMN_CENTRAL_STORAGE_ID + ')' + ')';

        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_CENTRAL_STORAGE_TABLE);
        } catch (SQLException e) {
            System.out.println("Couldn't create table: " + e.getMessage());
        }
    }

    public ObservableList<Product> queryCentralStorage(int sortOrder) {
        String QUERY_CENTRAL_STORAGE_START = "SELECT * FROM " + TABLE_CENTRAL_STORAGE;
        String QUERY_PRODUCT_IN_CENTRAL_STORAGE_SORT = " ORDER BY " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME +
                " COLLATE NOCASE ";

        StringBuilder sb = new StringBuilder(QUERY_CENTRAL_STORAGE_START);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_PRODUCT_IN_CENTRAL_STORAGE_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            while (results.next()) {
                Product product = new Product();
                product.setId(results.getInt(INDEX_CENTRAL_STORAGE_ID));
                product.setProductName(results.getString(INDEX_CENTRAL_STORAGE_PRODUCT_NAME));
                product.setCategory(results.getString(INDEX_CENTRAL_STORAGE_CATEGORY));
                product.setRating(results.getDouble(INDEX_CENTRAL_STORAGE_RATING));
                product.setQuantity(results.getInt(INDEX_CENTRAL_STORAGE_QUANTITY));
                product.setBrandName(results.getString(INDEX_CENTRAL_STORAGE_BRAND_NAME));
                String dateString = results.getString(INDEX_CENTRAL_STORAGE_EXPIRATION_DATE);
                LocalDate date = LocalDate.parse(dateString, formatter);
                product.setExpirationDate(date);
                product.setDescription(results.getString(INDEX_CENTRAL_STORAGE_DESCRIPTION));
                product.setPrice(results.getDouble(INDEX_CENTRAL_STORAGE_PRICE));
                product.setShelfNumber(results.getInt(INDEX_CENTRAL_STORAGE_SHELF_NUMBER));

                productList.add(product);
            }

            return productList;

        } catch (SQLException e) {
            System.out.println("Central Storage query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertIntoCentralStorage(Product product) {
        String QUERY_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_CENTRAL_STORAGE_ID +
                " FROM " + TABLE_CENTRAL_STORAGE + " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " = ?" +
                " AND " + COLUMN_CENTRAL_STORAGE_BRAND_NAME + " = ?";

        String INSERT_INTO_CENTRAL_STORAGE = "INSERT INTO " + TABLE_CENTRAL_STORAGE +
                '(' + COLUMN_CENTRAL_STORAGE_ID + ", " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + ", " + COLUMN_CENTRAL_STORAGE_CATEGORY + ", " +
                COLUMN_CENTRAL_STORAGE_RATING + ", " + COLUMN_CENTRAL_STORAGE_QUANTITY + ", " +
                COLUMN_CENTRAL_STORAGE_BRAND_NAME + ", " + COLUMN_CENTRAL_STORAGE_EXPIRATION_DATE + ", " +
                COLUMN_CENTRAL_STORAGE_DESCRIPTION + ", " + COLUMN_CENTRAL_STORAGE_PRICE + ", " +
                COLUMN_CENTRAL_STORAGE_SHELF_NUMBER + ')' + " VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            queryProductInCentralStorage = connection.prepareStatement(QUERY_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND);
            insertIntoCentralStorage = connection.prepareStatement(INSERT_INTO_CENTRAL_STORAGE);

            queryProductInCentralStorage.setString(1, product.getProductName());
            queryProductInCentralStorage.setString(2, product.getBrandName());
            ResultSet results = queryProductInCentralStorage.executeQuery();
            if (results.next()) {
                System.out.println("The product already exists");
                return;
            } else {
                insertIntoCentralStorage.setInt(1, product.getId());
                insertIntoCentralStorage.setString(2, product.getProductName());
                insertIntoCentralStorage.setString(3, product.getCategory());
                insertIntoCentralStorage.setDouble(4, product.getRating());
                insertIntoCentralStorage.setInt(5, product.getQuantity());
                insertIntoCentralStorage.setString(6, product.getBrandName());
                insertIntoCentralStorage.setString(7, String.format("%s", product.getExpirationDate().format(formatter)));
                insertIntoCentralStorage.setString(8, product.getDescription());
                insertIntoCentralStorage.setDouble(9, product.getPrice());
                insertIntoCentralStorage.setInt(10, product.getShelfNumber());

                int affectedRows = insertIntoCentralStorage.executeUpdate();
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

    public void removeProductFromCentralStorage(String productName, String brandName) {

        String QUERY_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_CENTRAL_STORAGE_ID +
                " FROM " + TABLE_CENTRAL_STORAGE + " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " = ?" +
                " AND " + COLUMN_CENTRAL_STORAGE_BRAND_NAME + " = ?";

        String REMOVE_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND = "DELETE FROM " +
                TABLE_CENTRAL_STORAGE + " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " = ?" + " AND " +
                COLUMN_CENTRAL_STORAGE_BRAND_NAME + " = ?";

        try {
            queryProductInCentralStorage = connection.prepareStatement(QUERY_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND);
            removeProductFromCentralStorage = connection.prepareStatement(REMOVE_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND);

            queryProductInCentralStorage.setString(1, productName);
            queryProductInCentralStorage.setString(2, brandName);
            ResultSet results = queryProductInCentralStorage.executeQuery();
            if (!results.next()) {
                System.out.println("The product you specified does not exist");
                return;
            } else {
                removeProductFromCentralStorage.setString(1, productName);
                removeProductFromCentralStorage.setString(2, brandName);
                int affectedRow = removeProductFromCentralStorage.executeUpdate();
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

    public ObservableList<Product> searchProductInCentralStorage(String productSearchText) {
        String SEARCH_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME = "SELECT * FROM " + TABLE_CENTRAL_STORAGE +
                " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " LIKE ?";

        ObservableList<Product> productList = FXCollections.observableArrayList();

        try {
            searchProductFromCentralStorageByName = connection.prepareStatement(SEARCH_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME);
            searchProductFromCentralStorageByName.setString(1, "%" + productSearchText + "%");
            ResultSet results = searchProductFromCentralStorageByName.executeQuery();

            if (results.next()) {
                Product product = new Product();
                product.setId(results.getInt(INDEX_CENTRAL_STORAGE_ID));
                product.setProductName(results.getString(INDEX_CENTRAL_STORAGE_PRODUCT_NAME));
                product.setCategory(results.getString(INDEX_CENTRAL_STORAGE_CATEGORY));
                product.setRating(results.getDouble(INDEX_CENTRAL_STORAGE_RATING));
                product.setQuantity(results.getInt(INDEX_CENTRAL_STORAGE_QUANTITY));
                product.setBrandName(results.getString(INDEX_CENTRAL_STORAGE_BRAND_NAME));
                String dateString = results.getString(INDEX_CENTRAL_STORAGE_EXPIRATION_DATE);
                LocalDate date = LocalDate.parse(dateString, formatter);
                product.setExpirationDate(date);
                product.setDescription(results.getString(INDEX_CENTRAL_STORAGE_DESCRIPTION));
                product.setPrice(results.getDouble(INDEX_CENTRAL_STORAGE_PRICE));
                product.setShelfNumber(results.getInt(INDEX_CENTRAL_STORAGE_SHELF_NUMBER));

                productList.add(product);
            }

            return productList;

        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
            return null;
        }
    }

    public void updateProductQuantityInCentralStorage(String productName, String brandName, int quantity) {
        String QUERY_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_CENTRAL_STORAGE_ID +
                " FROM " + TABLE_CENTRAL_STORAGE + " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " = ?" +
                " AND " + COLUMN_CENTRAL_STORAGE_BRAND_NAME + " = ?";

        String UPDATE_QUANTITY_OF_A_PRODUCT_IN_CENTRAL_STORAGE = "UPDATE " + TABLE_CENTRAL_STORAGE +
                " SET " + COLUMN_CENTRAL_STORAGE_QUANTITY + " = ?" + " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " = ?"
                + " AND " + COLUMN_CENTRAL_STORAGE_BRAND_NAME + " = ?";

        try {
            queryProductInCentralStorage = connection.prepareStatement(QUERY_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND);
            updateProductQuantityInCentralStorage = connection.prepareStatement(UPDATE_QUANTITY_OF_A_PRODUCT_IN_CENTRAL_STORAGE);
            queryProductInCentralStorage.setString(1, productName);
            queryProductInCentralStorage.setString(2, brandName);
            ResultSet results = queryProductInCentralStorage.executeQuery();
            if (!results.next()) {
                System.out.println("There is no such product on database");
                return;
            } else {
                updateProductQuantityInCentralStorage.setInt(1, quantity);
                updateProductQuantityInCentralStorage.setString(2, productName);
                updateProductQuantityInCentralStorage.setString(3, brandName);
                int affectedRows = updateProductQuantityInCentralStorage.executeUpdate();
                if (affectedRows == 1) {
                    System.out.println("Update successful");
                } else {
                    throw new SQLException("Problem with update");
                }
            }
        } catch (SQLException e) {
            System.out.println("Update was not successful: " + e.getMessage());
        }
    }

}
