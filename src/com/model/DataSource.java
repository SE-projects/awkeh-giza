package com.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataSource {
    private static DataSource instance = new DataSource();

    private static final String DB_NAME = "Supermarket.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\meme_\\IdeaProjects\\JavaFxExercise\\SEv1\\"
            + DB_NAME;
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


    public static final String TABLE_STORAGE = "Storage1";
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

    private static final int ORDER_BY_NONE = 1;
    private static final int ORDER_BY_DESC = 2;
    private static final int ORDER_BY_ASC = 3;

    private static final String QUERY_CENTRAL_STORAGE_START = "SELECT * FROM " + TABLE_CENTRAL_STORAGE;

    private static final String INSERT_INTO_CENTRAL_STORAGE = "INSERT INTO " + TABLE_CENTRAL_STORAGE +
            '(' + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + ", " + COLUMN_CENTRAL_STORAGE_CATEGORY + ", " +
            COLUMN_CENTRAL_STORAGE_RATING + ", " + COLUMN_CENTRAL_STORAGE_QUANTITY + ", " +
            COLUMN_CENTRAL_STORAGE_BRAND_NAME + ", " + COLUMN_CENTRAL_STORAGE_EXPIRATION_DATE + ", " +
            COLUMN_CENTRAL_STORAGE_DESCRIPTION + ", " + COLUMN_CENTRAL_STORAGE_PRICE + ", " +
            COLUMN_CENTRAL_STORAGE_SHELF_NUMBER + ')' + " VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String QUERY_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_CENTRAL_STORAGE_ID +
            " FROM " + TABLE_CENTRAL_STORAGE + " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " = ?" +
            " AND " + COLUMN_CENTRAL_STORAGE_BRAND_NAME + " = ?";

    private static final String QUERY_PRODUCT_IN_CENTRAL_STORAGE_SORT = " ORDER BY " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME +
            " COLLATE NOCASE ";

    private static final String REMOVE_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND = "DELETE FROM " +
            TABLE_CENTRAL_STORAGE + " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " = ?" + " AND " +
            COLUMN_CENTRAL_STORAGE_BRAND_NAME + " = ?";

    private static final String SEARCH_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME = "SELECT * FROM " + TABLE_CENTRAL_STORAGE +
            " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " LIKE ?";

    private static final String UPDATE_QUANTITY_OF_A_PRODUCT_IN_CENTRAL_STORAGE = "UPDATE " + TABLE_CENTRAL_STORAGE +
            " SET " + COLUMN_CENTRAL_STORAGE_QUANTITY + " = ?" + " WHERE " + COLUMN_CENTRAL_STORAGE_PRODUCT_NAME + " = ?"
            + " AND " + COLUMN_CENTRAL_STORAGE_BRAND_NAME + " = ?";


    public static final String QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_STORAGE_ID + " FROM " +
            TABLE_STORAGE + " WHERE " + COLUMN_STORAGE_PRODUCT_NAME + " = ?" + " AND " + COLUMN_STORAGE_BRAND_NAME + " = ?";

    public static final String QUERY_PRODUCT_IN_STORAGE_SORT = " ORDER BY " + COLUMN_STORAGE_PRODUCT_NAME;

    private static final String INSERT_INTO_STORAGE = "INSERT INTO " + TABLE_STORAGE +
            '(' + COLUMN_STORAGE_PRODUCT_NAME + ", " + COLUMN_STORAGE_CATEGORY + ", " +
            COLUMN_STORAGE_RATING + ", " + COLUMN_STORAGE_QUANTITY + ", " +
            COLUMN_STORAGE_BRAND_NAME + ", " + COLUMN_STORAGE_EXPIRATION_DATE + ", " +
            COLUMN_STORAGE_DESCRIPTION + ", " + COLUMN_STORAGE_PRICE + ", " +
            COLUMN_STORAGE_SHELF_NUMBER + ')' + " VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private PreparedStatement insertIntoStorage;
    private PreparedStatement queryProductInStorage;

    private PreparedStatement updateProductQuantityInCentralStorage;
    private PreparedStatement searchProductFromCentralStorageByName;
    private PreparedStatement removeProductFromCentralStorage;
    private PreparedStatement queryProductInCentralStorage;
    private PreparedStatement insertIntoCentralStorage;

    private DateTimeFormatter formatter;
    private Connection conn;

    private DataSource() {
        formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    }

    public static DataSource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            queryProductInCentralStorage = conn.prepareStatement(QUERY_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND);
            insertIntoCentralStorage = conn.prepareStatement(INSERT_INTO_CENTRAL_STORAGE);
            removeProductFromCentralStorage = conn.prepareStatement(REMOVE_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME_AND_BRAND);
            searchProductFromCentralStorageByName = conn.prepareStatement(SEARCH_PRODUCT_IN_CENTRAL_STORAGE_BY_NAME);
            updateProductQuantityInCentralStorage = conn.prepareStatement(UPDATE_QUANTITY_OF_A_PRODUCT_IN_CENTRAL_STORAGE);

            queryProductInStorage = conn.prepareStatement(QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND);
            insertIntoStorage = conn.prepareStatement(INSERT_INTO_STORAGE);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (insertIntoStorage != null) {
                insertIntoStorage.close();
            }
            if (queryProductInStorage != null) {
                queryProductInStorage.close();
            }


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
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {

        }
    }


    //description
    //price
    //shelfNumber

    public ObservableList<Product> queryCentralStorage(int sortOrder) {
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
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            while (results.next()) {
                Product product = new Product();
                product.setId(results.getInt(COLUMN_CENTRAL_STORAGE_ID));
                product.setProductName(results.getString(COLUMN_CENTRAL_STORAGE_PRODUCT_NAME));
                product.setCategory(results.getString(COLUMN_CENTRAL_STORAGE_CATEGORY));
                product.setRating(results.getDouble(COLUMN_CENTRAL_STORAGE_RATING));
                product.setQuantity(results.getInt(COLUMN_CENTRAL_STORAGE_QUANTITY));
                product.setBrandName(results.getString(COLUMN_CENTRAL_STORAGE_BRAND_NAME));
                String dateString = results.getString(COLUMN_CENTRAL_STORAGE_EXPIRATION_DATE);
                LocalDate date = LocalDate.parse(dateString, formatter);
                product.setExpirationDate(date);
                product.setDescription(results.getString(COLUMN_CENTRAL_STORAGE_DESCRIPTION));
                product.setPrice(results.getDouble(COLUMN_CENTRAL_STORAGE_PRICE));
                product.setShelfNumber(results.getInt(COLUMN_CENTRAL_STORAGE_SHELF_NUMBER));

                productList.add(product);
            }

            return productList;

        } catch (SQLException e) {
            System.out.println("Central Storage query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertIntoCentralStorage(String productName, String category, double rating,
                                         int quantity, String brandName, LocalDate expirationDate,
                                         String description, double price, int shelfNumber) {
        try {
            queryProductInCentralStorage.setString(1, productName);
            queryProductInCentralStorage.setString(2, brandName);
            ResultSet results = queryProductInCentralStorage.executeQuery();
            if (results.next()) {
                System.out.println("The product already exists");
                return;
            } else {
                insertIntoCentralStorage.setString(1, productName);
                insertIntoCentralStorage.setString(2, category);
                insertIntoCentralStorage.setDouble(3, rating);
                insertIntoCentralStorage.setInt(4, quantity);
                insertIntoCentralStorage.setString(5, brandName);
                insertIntoCentralStorage.setString(6, String.format("%s", expirationDate.format(formatter)));
                insertIntoCentralStorage.setString(7, description);
                insertIntoCentralStorage.setDouble(8, price);
                insertIntoCentralStorage.setInt(9, shelfNumber);

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
        try {
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
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try {
            searchProductFromCentralStorageByName.setString(1, "%" + productSearchText + "%");
            ResultSet results = searchProductFromCentralStorageByName.executeQuery();
            if (results.next()) {
                Product product = new Product();
                product.setId(results.getInt(COLUMN_CENTRAL_STORAGE_ID));
                product.setProductName(results.getString(COLUMN_CENTRAL_STORAGE_PRODUCT_NAME));
                product.setCategory(results.getString(COLUMN_CENTRAL_STORAGE_CATEGORY));
                product.setRating(results.getDouble(COLUMN_CENTRAL_STORAGE_RATING));
                product.setQuantity(results.getInt(COLUMN_CENTRAL_STORAGE_QUANTITY));
                product.setBrandName(results.getString(COLUMN_CENTRAL_STORAGE_BRAND_NAME));
                String dateString = results.getString(COLUMN_CENTRAL_STORAGE_EXPIRATION_DATE);
                LocalDate date = LocalDate.parse(dateString, formatter);
                product.setExpirationDate(date);
                product.setDescription(results.getString(COLUMN_CENTRAL_STORAGE_DESCRIPTION));
                product.setPrice(results.getDouble(COLUMN_CENTRAL_STORAGE_PRICE));
                product.setShelfNumber(results.getInt(COLUMN_CENTRAL_STORAGE_SHELF_NUMBER));

                productList.add(product);
            }

            return productList;

        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
            return null;
        }
    }

    public void updateProductQuantityInCentralStorage(String productName, String brandName, int quantity) {
        try {
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

    //STORAGE SECTION
    public ObservableList<Product> queryProductInStorage(String tableName) {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try {
            queryProductInStorage.setString(1, tableName);
            ResultSet results = queryProductInStorage.executeQuery();
            while (results.next()) {
                Product product = new Product();
                product.setId(results.getInt(COLUMN_STORAGE_ID));
                product.setProductName(results.getString(COLUMN_STORAGE_PRODUCT_NAME));
                product.setCategory(results.getString(COLUMN_STORAGE_CATEGORY));
                product.setRating(results.getDouble(COLUMN_STORAGE_RATING));
                product.setQuantity(results.getInt(COLUMN_STORAGE_QUANTITY));
                product.setBrandName(results.getString(COLUMN_STORAGE_BRAND_NAME));
                String dateString = results.getString(COLUMN_STORAGE_EXPIRATION_DATE);
                LocalDate date = LocalDate.parse(dateString, formatter);
                product.setExpirationDate(date);
                product.setDescription(results.getString(COLUMN_STORAGE_DESCRIPTION));
                product.setPrice(results.getDouble(COLUMN_STORAGE_PRICE));
                product.setShelfNumber(results.getInt(COLUMN_STORAGE_SHELF_NUMBER));

                productList.add(product);
            }

            return productList;

        } catch (SQLException e) {
            System.out.println("Storage query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertIntoStorage(String tableName, String productName, String category, double rating,
                                  int quantity, String brandName, LocalDate expirationDate,
                                  String description, double price, int shelfNumber) {
        try {
            queryProductInStorage.setString(1, productName);
            queryProductInStorage.setString(2, brandName);
            ResultSet results = queryProductInStorage.executeQuery();
            if (results.next()) {
                System.out.println("The product already exists");
                return;
            } else {
                insertIntoStorage.setString(1, productName);
                insertIntoStorage.setString(2, category);
                insertIntoStorage.setDouble(3, rating);
                insertIntoStorage.setInt(4, quantity);
                insertIntoStorage.setString(5, brandName);
                insertIntoStorage.setString(6, String.format("%s", expirationDate.format(formatter)));
                insertIntoStorage.setString(7, description);
                insertIntoStorage.setDouble(8, price);
                insertIntoStorage.setInt(9, shelfNumber);

                int affectedRows = insertIntoCentralStorage.executeUpdate();
                if (affectedRows == 1) {
                    System.out.println("Insertion is successful");
                } else {
                    throw new SQLException("Problem occurred with insertion");
                }
            }
        } catch (SQLException e) {
            System.out.println("Insertion failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
