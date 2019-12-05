package com.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataSource implements Headers {
    private static DataSource instance = new DataSource();

    private static final String DB_NAME = "Supermarket.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\meme_\\IdeaProjects" +
            "\\JavaFxExercise" + "\\awkeh-giza\\" + DB_NAME;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_DESC = 2;
    public static final int ORDER_BY_ASC = 3;
    //STORAGE SECTION
    /*public static final String QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND = "SELECT " + COLUMN_STORAGE_ID + " FROM ?" +
            " WHERE " + COLUMN_STORAGE_PRODUCT_NAME + " = ?" + " AND " + COLUMN_STORAGE_BRAND_NAME + " = ?";*/

    public static final String QUERY_PRODUCTS_IN_STORAGE = "SELECT * FROM " + TABLE_STORAGE;
    public static final String QUERY_PRODUCT_IN_STORAGE_SORT = " ORDER BY " + COLUMN_STORAGE_PRODUCT_NAME;

    /*private static final String INSERT_INTO_STORAGE = "INSERT INTO ?" +
            '(' + COLUMN_STORAGE_PRODUCT_NAME + ", " + COLUMN_STORAGE_CATEGORY + ", " +
            COLUMN_STORAGE_RATING + ", " + COLUMN_STORAGE_QUANTITY + ", " +
            COLUMN_STORAGE_BRAND_NAME + ", " + COLUMN_STORAGE_EXPIRATION_DATE + ", " +
            COLUMN_STORAGE_DESCRIPTION + ", " + COLUMN_STORAGE_PRICE + ", " +
            COLUMN_STORAGE_SHELF_NUMBER + ')' + " VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";*/


    /*    private PreparedStatement selectCustomerByUserName;
        private PreparedStatement selectCustomerByPassword;
        private PreparedStatement insertNewCustomer;
        private PreparedStatement getAllUSers;
        private PreparedStatement deleteCustomer;
        private PreparedStatement updateCustomer;*/
    //CUSTOMER SECTION
    //FOR LOGIN, INFORMATION HAS TO BE RETRIEVED FROM DATABASE
    public static final String TABLE_REGISTERED_CUSTOMER = "Registered_Customer";
    public static final String COLUMN_REGISTERED_CUSTOMER_ID = "id";
    public static final String COLUMN_REGISTERED_CUSTOMER_USER_NAME = "user_name";
    public static final String COLUMN_REGISTERED_CUSTOMER_PASSWORD = "password";
    //id
    //email
    //street_name
    //home_number
    //subcity
    //city
    //customer_id
    public static final String TABLE_CUSTOMER_DATA = "Customer_Data";
    public static final String COLUMN_CUSTOMER_DATA_ID = "id";
    public static final String COLUMN_CUSTOMER_DATA_EMAIL = "email";
    public static final String COLUMN_CUSTOMER_DATA_STREET_NAME = "street_name";
    public static final String COLUMN_CUSTOMER_DATA_HOME_NUMBER = "home_number";
    public static final String COLUMN_CUSTOMER_DATA_SUBCITY = "subCity";
    public static final String COLUMN_CUSTOMER_DATA_CITY = "city";
    public static final String COLUMN_CUSTOMER_DATA_CUSTOMER_ID = "customer_id";

    public static final String QUERY_CUSTOMER_BY_USER_NAME = "SELECT " + COLUMN_REGISTERED_CUSTOMER_ID +
            " FROM " + TABLE_REGISTERED_CUSTOMER + " WHERE " + COLUMN_REGISTERED_CUSTOMER_USER_NAME + " = ?";
    //TO INSERT A NEW CUSTOMER THAT HAS REGISTERED
    public static final String INSERT_NEWLY_REGISTERED_CUSTOMER_TO_DB = "INSERT INTO " + TABLE_REGISTERED_CUSTOMER + '(' +
            COLUMN_REGISTERED_CUSTOMER_USER_NAME + ", " + COLUMN_REGISTERED_CUSTOMER_PASSWORD + ')' + " VALUES " +
            "(?, ?)";
    public static final String INSERT_NEWLY_REGISTERED_CUSTOMER_DATA = "INSERT INTO " + TABLE_CUSTOMER_DATA + '(' +
            COLUMN_CUSTOMER_DATA_EMAIL + ", " + COLUMN_CUSTOMER_DATA_STREET_NAME + ", " + COLUMN_CUSTOMER_DATA_HOME_NUMBER +
            ", " + COLUMN_CUSTOMER_DATA_SUBCITY + ", " + COLUMN_CUSTOMER_DATA_CITY + ", " + COLUMN_CUSTOMER_DATA_CUSTOMER_ID + ')'
            + " VALUES (?, ?, ?, ?, ?, ?)";

    public static final String DELETE_REGISTERED_CUSTOMER = "DELETE FROM " + TABLE_REGISTERED_CUSTOMER + " WHERE " +
            COLUMN_REGISTERED_CUSTOMER_USER_NAME + " = ?" + " AND " + COLUMN_REGISTERED_CUSTOMER_PASSWORD + " = ?";

    public static final String DELETE_REGISTERED_CUSTOMER_DATA = "DELETE FROM " + TABLE_CUSTOMER_DATA + " WHERE " +
            COLUMN_CUSTOMER_DATA_CUSTOMER_ID + " = ?";

    //CUSTOMER
    private PreparedStatement deleteRegisteredCustomerData;
    private PreparedStatement deleteRegisteredCustomer;
    private PreparedStatement insertNewlyRegisteredCustomer;
    private PreparedStatement insertNewlyRegisteredCustomerData;
    private PreparedStatement queryCustomerByUserName;

    //STORAGE
    private PreparedStatement insertIntoStorage;
    private PreparedStatement queryStorage;
    private PreparedStatement queryProductInStorage;
    //CENTRAL STORAGE
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
            //STORAGE
            queryStorage = conn.prepareStatement(QUERY_PRODUCTS_IN_STORAGE);
//            queryProductInStorage = conn.prepareStatement(QUERY_PRODUCT_IN_STORAGE_BY_NAME_AND_BRAND);
//            insertIntoStorage = conn.prepareStatement(INSERT_INTO_STORAGE);

            //CUSTOMER
            queryCustomerByUserName = conn.prepareStatement(QUERY_CUSTOMER_BY_USER_NAME);
            insertNewlyRegisteredCustomer = conn.prepareStatement(INSERT_NEWLY_REGISTERED_CUSTOMER_TO_DB, Statement.RETURN_GENERATED_KEYS);
            insertNewlyRegisteredCustomerData = conn.prepareStatement(INSERT_NEWLY_REGISTERED_CUSTOMER_DATA);
            deleteRegisteredCustomer = conn.prepareStatement(DELETE_REGISTERED_CUSTOMER);
            deleteRegisteredCustomerData = conn.prepareStatement(DELETE_REGISTERED_CUSTOMER_DATA);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (deleteRegisteredCustomerData != null) {
                deleteRegisteredCustomerData.close();
            }
            if (deleteRegisteredCustomer != null) {
                deleteRegisteredCustomer.close();
            }
            if (queryCustomerByUserName != null) {
                queryCustomerByUserName.close();
            }
            if (insertNewlyRegisteredCustomer != null) {
                insertNewlyRegisteredCustomer.close();
            }
            if (insertNewlyRegisteredCustomerData != null) {
                insertNewlyRegisteredCustomerData.close();
            }
            if (queryStorage != null) {
                queryStorage.close();
            }
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
    public ObservableList<Product> queryProductsInStorage(String tableName) {
        /*StringBuilder sb = new StringBuilder(QUERY_PRODUCTS_IN_STORAGE);
        sb.append(tableName);
        sb.append("\"");*/
//        String sql = QUERY_PRODUCTS_IN_STORAGE.replace("$tableName", tableName);
        ObservableList<Product> productList = FXCollections.observableArrayList();
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(QUERY_PRODUCTS_IN_STORAGE)) {
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

    public void insertIntoStorage(String tableName, String productName, String category, double rating,
                                  int quantity, String brandName, LocalDate expirationDate,
                                  String description, double price, int shelfNumber) {
        try {
            queryProductInStorage.setString(1, tableName);
            queryProductInStorage.setString(2, productName);
            queryProductInStorage.setString(3, brandName);
            ResultSet results = queryProductInStorage.executeQuery();
            if (results.next()) {
                System.out.println("The product already exists");
                return;
            } else {
                insertIntoStorage.setString(1, tableName);
                insertIntoStorage.setString(2, productName);
                insertIntoStorage.setString(3, category);
                insertIntoStorage.setDouble(4, rating);
                insertIntoStorage.setInt(5, quantity);
                insertIntoStorage.setString(6, brandName);
                insertIntoStorage.setString(7, String.format("%s", expirationDate.format(formatter)));
                insertIntoStorage.setString(8, description);
                insertIntoStorage.setDouble(9, price);
                insertIntoStorage.setInt(10, shelfNumber);

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


    //CUSTOMER SECTION

    public void insertNewlyRegisteredCustomer(String username, String password,
                                              String email, String streetName, int homeNumber, String subCity,
                                              String city) {
        int generatedId;
        try {
            queryCustomerByUserName.setString(1, username);
            ResultSet results = queryCustomerByUserName.executeQuery();
            if (results.next()) {
                System.out.println("The user already exists");
                return;
            } else {
                conn.setAutoCommit(false);

                insertNewlyRegisteredCustomer.setString(1, username);
                insertNewlyRegisteredCustomer.setString(2, password);
                int affectedRows = insertNewlyRegisteredCustomer.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Couldn't insert the new customer");
                }
                ResultSet generatedKeys = insertNewlyRegisteredCustomer.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get id for customer");
                }
                insertNewlyRegisteredCustomerData.setString(1, email);
                insertNewlyRegisteredCustomerData.setString(2, streetName);
                insertNewlyRegisteredCustomerData.setInt(3, homeNumber);
                insertNewlyRegisteredCustomerData.setString(4, subCity);
                insertNewlyRegisteredCustomerData.setString(5, city);
                insertNewlyRegisteredCustomerData.setInt(6, generatedId);

                int affectedRowss = insertNewlyRegisteredCustomerData.executeUpdate();
                if (affectedRows == 1) {
                    conn.commit();
                } else {
                    throw new SQLException("Error inserting customer data");
                }
            }
        } catch (Exception e) {
            System.out.println("Error inserting customer: " + e.getMessage());
            try {
                System.out.println("Rolling back changes");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Error with rolling back: " + e.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default autocommit behaviour");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting default behaviour: " + e.getMessage());
            }
        }
    }

    public void deleteRegisteredCustomer(String userName, String password) {
        int id;
        try {
            queryCustomerByUserName.setString(1, userName);
            ResultSet result = queryCustomerByUserName.executeQuery();
            if (result.next()) {
                id = result.getInt(1);
            } else {
                System.out.println("There is no customer by that user name");
                return;
            }
            conn.setAutoCommit(false);

            deleteRegisteredCustomer.setString(1, userName);
            deleteRegisteredCustomer.setString(2, password);
            int affectedRows = deleteRegisteredCustomer.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Problem with deletion");
            }

            deleteRegisteredCustomerData.setInt(1, id);
            int affectedRowss = deleteRegisteredCustomerData.executeUpdate();
            if (affectedRowss == 1) {
                conn.commit();
            } else {
                throw new SQLException("Couldn't delete customer data");
            }

        } catch (Exception e) {
            System.out.println("Error deleting customer");
            try{
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2){
                System.out.println("Error performing rollback: " + e2.getMessage());
            }
        }

        finally {
            try{
                System.out.println("Resetting default behaviour");
                conn.setAutoCommit(true);
            }catch (SQLException e){
                System.out.println("Error setting autocommit behaviour to default: " + e.getMessage());
            }
        }
    }

    
}


