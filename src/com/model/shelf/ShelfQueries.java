package com.model.shelf;

import com.model.Connexion;
import com.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//TODO Isle name can be unique thus a primary key but in shelf table it is a foreign key
public class ShelfQueries {
    private Connection connection;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    private static final int ORDER_BY_NONE = 1;
    private static final int ORDER_BY_DESC = 2;
    private static final int ORDER_BY_ASC = 3;

    String shelf_name;
    private static final String COLUMN_SHELF_PRODUCT_ID = "product_id";
    private static final String COLUMN_SHELF_PRODUCT_NAME = "product_name";
    private static final String COLUMN_SHELF_PRODUCT_CATEGORY = "category";
    private static final String COLUMN_SHELF_PRODUCT_RATING = "rating";
    private static final String COLUMN_SHELF_PRODUCT_QUANTITY = "quantity";
    private static final String COLUMN_SHELF_PRODUCT_BRAND = "brand_name";
    private static final String COLUMN_SHELF_PRODUCT_EXPIRATION_DATE = "expiration_date";
    private static final String COLUMN_SHELF_PRODUCT_DESCRIPTION = "description";
    private static final String COLUMN_SHELF_PRODUCT_PRICE = "price";
    private static final String COLUMN_SHELF_NUMBER = "shelf_number";
    private static final String COLUMN_SHELF_ISLE_NAME = "isle_name";
    private static final String COLUMN_SHELF_SUPERMARKET_ID = "supermarket_id";
    private static final int INDEX_SHELF_PRODUCT_ID = 1;
    private static final int INDEX_SHELF_PRODUCT_NAME = 2;
    private static final int INDEX_SHELF_CATEGORY = 3;
    private static final int INDEX_SHELF_RATING = 4;
    private static final int INDEX_SHELF_QUANTITY = 5;
    private static final int INDEX_SHELF_BRAND_NAME = 6;
    private static final int INDEX_SHELF_EXPIRATION_DATE = 7;
    private static final int INDEX_SHELF_DESCRIPTION = 8;
    private static final int INDEX_SHELF_PRICE = 9;
    private static final int INDEX_SHELF_NUMBER = 10;
    private static final int INDEX_SHELF_ISLE_NAME = 11;
    private static final int INDEX_SHELF_SUPERMARKET_ID = 12;

    private PreparedStatement insertIntoShelf;
    private PreparedStatement queryProductInShelfByNameAndBrand;
    private PreparedStatement removeProductFromShelf;
    private PreparedStatement updateProductInShelf;

    public ShelfQueries(){
        connection = Connexion.getInstance().getConnection();
        if(connection==null){
            System.out.println("Couldn't establish connection");
            System.exit(1);
        }
    }

    public void closeConnection(){
        try {
            if(updateProductInShelf != null){
                updateProductInShelf.close();
            }
            if(removeProductFromShelf != null){
                removeProductFromShelf.close();
            }
            if(queryProductInShelfByNameAndBrand != null){
                queryProductInShelfByNameAndBrand.close();
            }
            if(insertIntoShelf != null){
                insertIntoShelf.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e){
            System.out.println("Couldn't close");
        }
    }

    public void createShelfTable(String tableName) {
        String CREATE_SHELF_TABLE = "CREATE TABLE IF NOT EXISTS " + tableName + '(' + COLUMN_SHELF_PRODUCT_ID
                + " INTEGER NOT NULL UNIQUE, " + COLUMN_SHELF_PRODUCT_NAME + " TEXT, " + COLUMN_SHELF_PRODUCT_CATEGORY +
                " TEXT, " + COLUMN_SHELF_PRODUCT_RATING + " REAL, " + COLUMN_SHELF_PRODUCT_QUANTITY + " INTEGER, " +
                COLUMN_SHELF_PRODUCT_BRAND + " TEXT, " + COLUMN_SHELF_PRODUCT_EXPIRATION_DATE + " TEXT, " +
                COLUMN_SHELF_PRODUCT_DESCRIPTION + " TEXT, " + COLUMN_SHELF_PRODUCT_PRICE + " REAL, " + COLUMN_SHELF_NUMBER
                + " INTEGER, " + COLUMN_SHELF_ISLE_NAME + " TEXT, " + COLUMN_SHELF_SUPERMARKET_ID +
                " INTEGER" + ')';
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_SHELF_TABLE);
        } catch (SQLException e) {
            System.out.println("Couldn't create table: " + e.getMessage());
        }
    }

    public ObservableList<Product> queryProductsInShelf(String tableName){
        String sql = "SELECT * FROM " + tableName;
        ObservableList<Product> productsList = FXCollections.observableArrayList();
        try(Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql)){

                while(results.next()){
                    Product product = new Product();
                    product.setId(results.getInt(INDEX_SHELF_PRODUCT_ID));
                    product.setProductName(results.getString(INDEX_SHELF_PRODUCT_NAME));
                    product.setCategory(results.getString(INDEX_SHELF_CATEGORY));
                    product.setRating(results.getDouble(INDEX_SHELF_RATING));
                    product.setQuantity(results.getInt(INDEX_SHELF_QUANTITY));
                    product.setBrandName(results.getString(INDEX_SHELF_BRAND_NAME));
                    String dateString = results.getString(INDEX_SHELF_EXPIRATION_DATE);
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    product.setExpirationDate(date);
                    product.setDescription(results.getString(INDEX_SHELF_DESCRIPTION));
                    product.setPrice(results.getDouble(INDEX_SHELF_PRICE));
                    product.setShelfNumber(results.getInt(INDEX_SHELF_NUMBER));
                    product.setIsleName(results.getString(INDEX_SHELF_ISLE_NAME));
                    product.setSupermarketId(results.getInt(INDEX_SHELF_SUPERMARKET_ID));

                    productsList.add(product);
                }

                return productsList;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public void insertIntoShelf(String tableName, Product product) {
        String QUERY_PRODUCT_IN_SHELF = "SELECT " + COLUMN_SHELF_PRODUCT_ID +
        " FROM " + tableName + " WHERE " + COLUMN_SHELF_PRODUCT_NAME + " = ?" + " AND " + COLUMN_SHELF_PRODUCT_BRAND + " = ?";


        String INSERT_INTO_SHELF = "INSERT INTO " + tableName +
        '(' + COLUMN_SHELF_PRODUCT_ID + ", " + COLUMN_SHELF_PRODUCT_NAME + ", " + COLUMN_SHELF_PRODUCT_CATEGORY + ", " +
        COLUMN_SHELF_PRODUCT_RATING + ", " + COLUMN_SHELF_PRODUCT_QUANTITY + ", " + COLUMN_SHELF_PRODUCT_BRAND + ", " +
        COLUMN_SHELF_PRODUCT_EXPIRATION_DATE + ", " + COLUMN_SHELF_PRODUCT_DESCRIPTION + ", " + COLUMN_SHELF_PRODUCT_PRICE + ", " +
        COLUMN_SHELF_NUMBER + ", " + COLUMN_SHELF_ISLE_NAME + ", " + COLUMN_SHELF_SUPERMARKET_ID + ')' +
        " VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            queryProductInShelfByNameAndBrand = connection.prepareStatement(QUERY_PRODUCT_IN_SHELF);
            insertIntoShelf = connection.prepareStatement(INSERT_INTO_SHELF);

            queryProductInShelfByNameAndBrand.setString(1, product.getProductName());
            queryProductInShelfByNameAndBrand.setString(2, product.getBrandName());
            ResultSet results = queryProductInShelfByNameAndBrand.executeQuery();
            if (results.next()) {
                System.out.println("The product already exists");
                return;
            } else {
                insertIntoShelf.setInt(1, product.getId());
                insertIntoShelf.setString(2, product.getProductName());
                insertIntoShelf.setString(3, product.getCategory());
                insertIntoShelf.setDouble(4, product.getRating());
                insertIntoShelf.setInt(5, product.getQuantity());
                insertIntoShelf.setString(6, product.getBrandName());
                insertIntoShelf.setString(7, String.format("%s", product.getExpirationDate().format(formatter)));
                insertIntoShelf.setString(8, product.getDescription());
                insertIntoShelf.setDouble(9, product.getPrice());
                insertIntoShelf.setInt(10, product.getShelfNumber());
                insertIntoShelf.setString(11, product.getIsleName());
                insertIntoShelf.setInt(12, product.getSupermarketId());

                int affectedRows = insertIntoShelf.executeUpdate();
                if (affectedRows == 1) {
                    System.out.println("Insertion in to shelf is successful");
                } else {
                    throw new SQLException("Problem with insertion");
                }

            }
            results.close();
        } catch (SQLException e) {
            System.out.println("insertion failed");
        }

    }

    public void removeProductFromShelf(String tableName, int productId) {
        /*String QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_STORAGE_PRODUCT_ID +
                " FROM " + tableName + " WHERE " + COLUMN_STORAGE_PRODUCT_NAME + " = ?" + " AND " +
                COLUMN_STORAGE_BRAND_NAME + " = ?";*/

        String REMOVE_PRODUCT_IN_SHELF = "DELETE FROM " + tableName + " WHERE " +
                COLUMN_SHELF_PRODUCT_ID + " = ?";

        try {
//            queryProductInStorageByNameAndBrand = connection.prepareStatement(QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND);
            removeProductFromShelf = connection.prepareStatement(REMOVE_PRODUCT_IN_SHELF);

          /*  queryProductInStorageByNameAndBrand.setString(1, productName);
            queryProductInStorageByNameAndBrand.setString(2, brandName);
            ResultSet results = queryProductInStorageByNameAndBrand.executeQuery();
            if (!results.next()) {
                System.out.println("The product you specified does not exist");
                return;
            } */
            removeProductFromShelf.setInt(1, productId);
            int affectedRow = removeProductFromShelf.executeUpdate();
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

    public void updateProductInShelf(String tableName, Product product) {
        String UPDATE_PRODUCT_IN_SHELF = "UPDATE " + tableName + " SET " + COLUMN_SHELF_PRODUCT_ID + " = ?, " +
                COLUMN_SHELF_PRODUCT_NAME + " = ?, " + COLUMN_SHELF_PRODUCT_CATEGORY + " = ?, " + COLUMN_SHELF_PRODUCT_RATING
                + " = ?, " + COLUMN_SHELF_PRODUCT_QUANTITY + " = ?, " + COLUMN_SHELF_PRODUCT_BRAND + " = ?, " +
                COLUMN_SHELF_PRODUCT_EXPIRATION_DATE + " = ?, " + COLUMN_SHELF_PRODUCT_DESCRIPTION + " = ?, " +
                COLUMN_SHELF_PRODUCT_PRICE + " = ?, " + COLUMN_SHELF_NUMBER + " = ?, " + COLUMN_SHELF_ISLE_NAME + " = ?, " +
                COLUMN_SHELF_SUPERMARKET_ID + " = ?";

        try {
            updateProductInShelf = connection.prepareStatement(UPDATE_PRODUCT_IN_SHELF);
            updateProductInShelf.setInt(1, product.getId());
            updateProductInShelf.setString(2, product.getProductName());
            updateProductInShelf.setString(3, product.getCategory());
            updateProductInShelf.setDouble(4, product.getRating());
            updateProductInShelf.setInt(5, product.getQuantity());
            updateProductInShelf.setString(6, product.getBrandName());
            updateProductInShelf.setString(7, String.format("%s", product.getExpirationDate().format(formatter)));
            updateProductInShelf.setString(8, product.getDescription());
            updateProductInShelf.setDouble(9, product.getPrice());
            updateProductInShelf.setInt(10, product.getShelfNumber());
            updateProductInShelf.setString(11, product.getIsleName());
            updateProductInShelf.setInt(12, product.getSupermarketId());

            int affectedRows = updateProductInShelf.executeUpdate();
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
