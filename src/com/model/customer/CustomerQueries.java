package com.model.customer;

import com.model.Connexion;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class CustomerQueries {

    private Connection connection;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    private static final String TABLE_REGISTERED_CUSTOMER = "Registered_Customer";
    private static final String COLUMN_REGISTERED_CUSTOMER_ID = "id";
    private static final String COLUMN_REGISTERED_CUSTOMER_USER_NAME = "user_name";
    private static final String COLUMN_REGISTERED_CUSTOMER_PASSWORD = "password";
    private static final int INDEX_COLUMN_REGISTERED_CUSTOMER_ID = 1;
    private static final int INDEX_COLUMN_REGISTERED_CUSTOMER_USER_NAME = 2;
    private static final int INDEX_COLUMN_REGISTERED_CUSTOMER_PASSWORD = 3;

    private static final String TABLE_CUSTOMER_DATA = "Customer_Data";
    private static final String COLUMN_CUSTOMER_DATA_ID = "id";
    private static final String COLUMN_CUSTOMER_DATA_FIRST_NAME = "first_name";
    private static final String COLUMN_CUSTOMER_DATA_LAST_NAME = "last_name";
    private static final String COLUMN_CUSTOMER_DATA_PHONE = "phone";
    private static final String COLUMN_CUSTOMER_DATA_EMAIL = "email";
    private static final String COLUMN_CUSTOMER_DATA_STREET_NAME = "street_name";
    private static final String COLUMN_CUSTOMER_DATA_HOME_NUMBER = "home_number";
    private static final String COLUMN_CUSTOMER_DATA_SUBCITY = "subCity";
    private static final String COLUMN_CUSTOMER_DATA_CITY = "city";
    private static final String COLUMN_CUSTOMER_DATA_CUSTOMER_ID = "customer_id";
    private static final int INDEX_CUSTOMER_DATA_ID = 1;
    private static final int INDEX_CUSTOMER_DATA_FIRST_NAME = 2;
    private static final int INDEX_CUSTOMER_DATA_LAST_NAME = 3;
    private static final int INDEX_CUSTOMER_DATA_PHONE = 4;
    private static final int INDEX_CUSTOMER_DATA_EMAIL = 5;
    private static final int INDEX_CUSTOMER_DATA_STREET_NAME = 6;
    private static final int INDEX_CUSTOMER_DATA_HOME_NUMBER = 7;
    private static final int INDEX_CUSTOMER_DATA_SUB_CITY = 8;
    private static final int INDEX_CUSTOMER_DATA_CITY = 9;
    private static final int INDEX_CUSTOMER_DATA_CUSTOMER_ID = 10;

    private static final String TABLE_CART_PRODUCT = "Cart_Product";
    private static final String COLUMN_CART_PRODUCT_ID = "product_id";
    private static final String COLUMN_CART_PRODUCT_NAME = "product_name";
    private static final String COLUMN_CART_PRODUCT_PRICE = "price";
    private static final String COLUMN_CART_PRODUCT_QUANTITY = "quantity";
    private static final String COLUMN_CART_PRODUCT_TOTAL_AMOUNT = "total_amount";
    private static final String COLUMN_CART_PRODUCT_CART_ID = "cart_id";
    private static final int INDEX_CART_PRODUCT_ID = 1;
    private static final int INDEX_CART_PRODUCT_NAME = 2;
    private static final int INDEX_CART_PRODUCT_PRICE = 3;
    private static final int INDEX_CART_PRODUCT_QUANTITY = 4;
    private static final int INDEX_CART_PRODUCT_TOTAL_AMOUNT = 5;
    private static final int INDEX_CART_PRODUCT_CART_ID = 6;

    public static final String TABLE_CART = "Cart";
    public static final String COLUMN_CART_ID = "cart_id";
    public static final String COLUMN_CART_NAME = "cart_name";
    public static final int INDEX_COLUMN_CART_ID = 1;
    public static final int INDEX_COLUMN_CART_NAME = 2;

    private PreparedStatement insertNewlyRegisteredCustomer;
    private PreparedStatement queryCustomerByUserName;
    private PreparedStatement insertNewlyRegisteredCustomerData;
    private PreparedStatement deleteRegisteredCustomer;
    private PreparedStatement deleteRegisteredCustomerData;
    private PreparedStatement queryCustomerByUserNameAndPassword;
    private PreparedStatement updateRegisteredCustomer;
    private PreparedStatement updateRegisteredCustomerData;

    private PreparedStatement insertProductIntoCart;
    private PreparedStatement createCart;
    private PreparedStatement queryCart;

    public boolean establishConnection() {
        connection = Connexion.getInstance().getConnection();
        if (connection == null) {
            System.out.println("Couldn't establish connection");
            return false;
        }
        return true;
    }

    public void closeConnection() {
        try {
            if (queryCart != null) {
                queryCart.close();
            }
            if (createCart != null) {
                createCart.close();
            }
            if (insertProductIntoCart != null) {
                insertProductIntoCart.close();
            }
            if (updateRegisteredCustomerData != null) {
                updateRegisteredCustomerData.close();
            }
            if (updateRegisteredCustomer != null) {
                updateRegisteredCustomer.close();
            }
            if (queryCustomerByUserNameAndPassword != null) {
                queryCustomerByUserNameAndPassword.close();
            }
            if (deleteRegisteredCustomerData != null) {
                deleteRegisteredCustomerData.close();
            }
            if (deleteRegisteredCustomer != null) {
                deleteRegisteredCustomer.close();
            }
            if (insertNewlyRegisteredCustomerData != null) {
                insertNewlyRegisteredCustomerData.close();
            }
            if (insertNewlyRegisteredCustomer != null) {
                insertNewlyRegisteredCustomer.close();
            }
            if (queryCustomerByUserName != null) {
                queryCustomerByUserName.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertNewlyRegisteredCustomer(String username, String password, String firstName, String lastName,
                                              String phone, String email, String streetName, int homeNumber, String subCity,
                                              String city) {
        int generatedId;

        String QUERY_CUSTOMER_BY_USER_NAME = "SELECT " + COLUMN_REGISTERED_CUSTOMER_ID +
                " FROM " + TABLE_REGISTERED_CUSTOMER + " WHERE " + COLUMN_REGISTERED_CUSTOMER_USER_NAME + " = ?";

        String INSERT_NEWLY_REGISTERED_CUSTOMER_TO_DB = "INSERT INTO " + TABLE_REGISTERED_CUSTOMER + '(' +
                COLUMN_REGISTERED_CUSTOMER_USER_NAME + ", " + COLUMN_REGISTERED_CUSTOMER_PASSWORD + ')' + " VALUES " + "(?, ?)";

        String INSERT_NEWLY_REGISTERED_CUSTOMER_DATA = "INSERT INTO " + TABLE_CUSTOMER_DATA + '(' + COLUMN_CUSTOMER_DATA_FIRST_NAME
                + ", " + COLUMN_CUSTOMER_DATA_LAST_NAME + ", " + COLUMN_CUSTOMER_DATA_PHONE + ", " + COLUMN_CUSTOMER_DATA_EMAIL + ", " +
                COLUMN_CUSTOMER_DATA_STREET_NAME + ", " + COLUMN_CUSTOMER_DATA_HOME_NUMBER + ", " + COLUMN_CUSTOMER_DATA_SUBCITY + ", " +
                COLUMN_CUSTOMER_DATA_CITY + ", " + COLUMN_CUSTOMER_DATA_CUSTOMER_ID + ')' + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            insertNewlyRegisteredCustomer = connection.prepareStatement(INSERT_NEWLY_REGISTERED_CUSTOMER_TO_DB,
                    Statement.RETURN_GENERATED_KEYS);
            queryCustomerByUserName = connection.prepareStatement(QUERY_CUSTOMER_BY_USER_NAME);
            insertNewlyRegisteredCustomerData = connection.prepareStatement(INSERT_NEWLY_REGISTERED_CUSTOMER_DATA);


            queryCustomerByUserName.setString(1, username);
            ResultSet results = queryCustomerByUserName.executeQuery();
            if (results.next()) {
                System.out.println("The user already exists");
                return;
            } else {
                connection.setAutoCommit(false);

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
                insertNewlyRegisteredCustomerData.setString(1, firstName);
                insertNewlyRegisteredCustomerData.setString(2, lastName);
                insertNewlyRegisteredCustomerData.setString(3, phone);
                insertNewlyRegisteredCustomerData.setString(4, email);
                insertNewlyRegisteredCustomerData.setString(5, streetName);
                insertNewlyRegisteredCustomerData.setInt(6, homeNumber);
                insertNewlyRegisteredCustomerData.setString(7, subCity);
                insertNewlyRegisteredCustomerData.setString(8, city);
                insertNewlyRegisteredCustomerData.setInt(9, generatedId);

                int affectedRowss = insertNewlyRegisteredCustomerData.executeUpdate();
                if (affectedRowss == 1) {
                    connection.commit();
                } else {
                    throw new SQLException("Error inserting customer data");
                }
            }
        } catch (Exception e) {
            System.out.println("Error inserting customer: " + e.getMessage());
            try {
                System.out.println("Rolling back changes");
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Error with rolling back: " + e.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default autocommit behaviour");
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting default behaviour: " + e.getMessage());
            }
        }
    }

    public void deleteRegisteredCustomer(String userName, String password) {
        int id;
        String QUERY_CUSTOMER_BY_USER_NAME = "SELECT " + COLUMN_REGISTERED_CUSTOMER_ID +
                " FROM " + TABLE_REGISTERED_CUSTOMER + " WHERE " + COLUMN_REGISTERED_CUSTOMER_USER_NAME + " = ?";

        String QUERY_CUSTOMER_BY_USERNAME_AND_PASSWORD = "SELECT " + COLUMN_REGISTERED_CUSTOMER_ID + " FROM "
                + TABLE_REGISTERED_CUSTOMER + " WHERE " + COLUMN_REGISTERED_CUSTOMER_USER_NAME + " = ?" + " AND " +
                COLUMN_REGISTERED_CUSTOMER_PASSWORD + " = ?";

        String DELETE_REGISTERED_CUSTOMER = "DELETE FROM " + TABLE_REGISTERED_CUSTOMER + " WHERE " +
                COLUMN_REGISTERED_CUSTOMER_USER_NAME + " = ?" + " AND " + COLUMN_REGISTERED_CUSTOMER_PASSWORD + " = ?";

        String DELETE_REGISTERED_CUSTOMER_DATA = "DELETE FROM " + TABLE_CUSTOMER_DATA + " WHERE " +
                COLUMN_CUSTOMER_DATA_CUSTOMER_ID + " = ?";

        try {
//            queryCustomerByUserName = connection.prepareStatement(QUERY_CUSTOMER_BY_USER_NAME);
            queryCustomerByUserNameAndPassword = connection.prepareStatement(QUERY_CUSTOMER_BY_USERNAME_AND_PASSWORD);
            deleteRegisteredCustomer = connection.prepareStatement(DELETE_REGISTERED_CUSTOMER);
            deleteRegisteredCustomerData = connection.prepareStatement(DELETE_REGISTERED_CUSTOMER_DATA);

            queryCustomerByUserNameAndPassword.setString(1, userName);
            queryCustomerByUserNameAndPassword.setString(2, password);
            ResultSet result = queryCustomerByUserNameAndPassword.executeQuery();
            if (result.next()) {
                id = result.getInt(1);
            } else {
                System.out.println("There is no customer by that user name");
                return;
            }
            connection.setAutoCommit(false);

            deleteRegisteredCustomer.setString(1, userName);
            deleteRegisteredCustomer.setString(2, password);
            int affectedRows = deleteRegisteredCustomer.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Problem with deletion");
            }

            deleteRegisteredCustomerData.setInt(1, id);
            int affectedRowss = deleteRegisteredCustomerData.executeUpdate();
            if (affectedRowss == 1) {
                connection.commit();
            } else {
                throw new SQLException("Couldn't delete customer data");
            }

        } catch (Exception e) {
            System.out.println("Error deleting customer");
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Error performing rollback: " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default behaviour");
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error setting autocommit behaviour to default: " + e.getMessage());
            }
        }
    }

    public void updateRegisteredCustomerInfo(String oldUsername, String oldPassword, String newUsername,
                                             String newPassword, String phone, String email, String streetName,
                                             int homeNumber, String subCity, String city) {
        int id;

        String QUERY_CUSTOMER_BY_USERNAME_AND_PASSWORD = "SELECT " + COLUMN_REGISTERED_CUSTOMER_ID + " FROM "
                + TABLE_REGISTERED_CUSTOMER + " WHERE " + COLUMN_REGISTERED_CUSTOMER_USER_NAME + " = ?" + " AND " +
                COLUMN_REGISTERED_CUSTOMER_PASSWORD + " = ?";

        String UPDATE_REGISTERED_CUSTOMER = "UPDATE " + TABLE_REGISTERED_CUSTOMER + " SET " +
                COLUMN_REGISTERED_CUSTOMER_USER_NAME + " = ?," + COLUMN_REGISTERED_CUSTOMER_PASSWORD + " = ?" +
                " WHERE " + COLUMN_REGISTERED_CUSTOMER_ID + " = ?";

        String UPDATE_REGISTERED_CUSTOMER_DATA = "UPDATE " + TABLE_CUSTOMER_DATA + " SET " +
                COLUMN_CUSTOMER_DATA_PHONE + " = ?, " + COLUMN_CUSTOMER_DATA_EMAIL + " = ?," +
                COLUMN_CUSTOMER_DATA_STREET_NAME + " = ?," + COLUMN_CUSTOMER_DATA_HOME_NUMBER + " = ?," +
                COLUMN_CUSTOMER_DATA_SUBCITY + " = ?," + COLUMN_CUSTOMER_DATA_CITY + " = ?" + " WHERE "
                + COLUMN_CUSTOMER_DATA_ID + " = ?";
        try {
            updateRegisteredCustomer = connection.prepareStatement(UPDATE_REGISTERED_CUSTOMER);
            updateRegisteredCustomerData = connection.prepareStatement(UPDATE_REGISTERED_CUSTOMER_DATA);
            queryCustomerByUserNameAndPassword = connection.prepareStatement(QUERY_CUSTOMER_BY_USERNAME_AND_PASSWORD);

            queryCustomerByUserNameAndPassword.setString(1, oldUsername);
            queryCustomerByUserNameAndPassword.setString(2, oldPassword);

            ResultSet result = queryCustomerByUserNameAndPassword.executeQuery();
            if (result.next()) {
                id = result.getInt(1);
            } else {
                System.out.println("There is no customer data by that user name");
                return;
            }
            connection.setAutoCommit(false);

            updateRegisteredCustomer.setString(1, newUsername);
            updateRegisteredCustomer.setString(2, newPassword);
            updateRegisteredCustomer.setInt(3, id);

            int affectedRows = updateRegisteredCustomer.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't update customer username and password");
            }

            updateRegisteredCustomerData.setString(1, phone);
            updateRegisteredCustomerData.setString(2, email);
            updateRegisteredCustomerData.setString(3, streetName);
            updateRegisteredCustomerData.setInt(4, homeNumber);
            updateRegisteredCustomerData.setString(5, subCity);
            updateRegisteredCustomerData.setString(6, city);
            updateRegisteredCustomerData.setInt(7, id);

            int affectedRowss = updateRegisteredCustomerData.executeUpdate();
            if (affectedRowss == 1) {
                connection.commit();
            } else {
                throw new SQLException("Couldn't update customer data");
            }

        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                connection.rollback();
            } catch (SQLException e2) {
                System.out.println("Error performing rollback: " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default autocommit behaviour");
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting default autocommit behaviour: " + e.getMessage());
            }
        }
    }

    public void createACart(String name) {
        String QUERY_CART = "SELECT " + COLUMN_CART_ID + " FROM " + TABLE_CART + " WHERE " + COLUMN_CART_NAME + " = ?";
        String CREATE_A_CART = "INSERT INTO " + TABLE_CART + '(' + COLUMN_CART_NAME + ')' + " VALUES (?)";
        try {
            queryCart = connection.prepareStatement(QUERY_CART);
            createCart = connection.prepareStatement(CREATE_A_CART);

            queryCart.setString(1, name);
            ResultSet result = queryCart.executeQuery();
            if(result.next()){
                System.out.println("Cart by that name already exists");
            } else{
                createCart.setString(1, name);
                int affectedRows = createCart.executeUpdate();
                if(affectedRows == 1){
                    System.out.println("Cart creation successful");
                } else{
                    throw new SQLException("Cart creation unsuccessful");
                }
            }


        } catch (SQLException e) {
            System.out.println("Couldn't create cart");
        }
    }

    public void addProductToCart(int productId, String productName, double price,
                                 int quantity, double totalAmount, String cartName) {
        String QUERY_CART = "SELECT " + COLUMN_CART_ID + " FROM " + TABLE_CART + " WHERE " + COLUMN_CART_NAME + " = ?";
        String ADD_PRODUCT_TO_CART = "INSERT INTO " + TABLE_CART_PRODUCT + " VALUES (?,?,?,?,?,?)";
        int cartId;
        try {
            queryCart = connection.prepareStatement(QUERY_CART);
            insertProductIntoCart = connection.prepareStatement(ADD_PRODUCT_TO_CART);

            queryCart.setString(1, cartName);
            ResultSet result = queryCart.executeQuery();
            if(result.next()) {
                cartId = result.getInt(1);
            } else{
                System.out.println("No cart by that name");
                return;
            }

            insertProductIntoCart.setInt(1, productId);
            insertProductIntoCart.setString(2, productName);
            insertProductIntoCart.setDouble(3, price);
            insertProductIntoCart.setInt(4, quantity);
            insertProductIntoCart.setDouble(5, totalAmount);
            insertProductIntoCart.setInt(6, cartId);

            int affectedRows = insertProductIntoCart.executeUpdate();
            if (affectedRows == 1) {
                System.out.println("Insertion successful");
            } else {
                throw new SQLException("Insertion failed");
            }

        } catch (SQLException e) {
            System.out.println("Couldn't add product to cart: " + e.getMessage());
        }
    }
}
