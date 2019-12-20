package com.model.customer;

import com.model.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//TODO two features are available for customers. They can either create the cart manually or
// the cart will be created once they try to add a product to cart
public class CustomerQueries {

    private Connection connection;
    private LocalDate date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    private static final int ORDER_BY_NONE = 1;
    private static final int ORDER_BY_DESC = 2;
    private static final int ORDER_BY_ASC = 3;

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
    public static final String COLUMN_CART_CUSTOMER_ID = "customer_id";
    public static final int INDEX_CART_ID = 1;
    public static final int INDEX_CART_NAME = 2;
    public static final int INDEX_CART_CUSTOMER_ID = 3;

    public static final String TABLE_ORDER = "Orders";
    public static final String COLUMN_ORDER_ID = "order_id";
    public static final String COLUMN_ORDER_DATE = "order_date";
    public static final String COLUMN_ORDER_CART_ID = "cart_id";
    public static final String COLUMN_ORDER_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_ORDER_STATUS = "order_status";
    public static final String COLUMN_ORDER_TOTAL_AMOUNT = "total_amount";
    public static final int INDEX_ORDER_ID = 1;
    public static final int INDEX_ORDER_DATE = 2;
    public static final int INDEX_ORDER_CART_ID = 3;
    public static final int INDEX_ORDER_CUSTOMER_ID = 4;
    public static final int INDEX_ORDER_STATUS = 5;
    public static final int INDEX_ORDER_TOTAL_AMOUNT = 6;

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
    private PreparedStatement queryProductInCart;
    private PreparedStatement removeProductFromCart;
    private PreparedStatement updateProductInCart;
    private PreparedStatement insertOrder;
    private PreparedStatement queryProductInCartByQuantity;
    private PreparedStatement queryNumberOfDistinctProductsInCart;
    private PreparedStatement getTotalAmount;
    private PreparedStatement viewCartContents;
    private PreparedStatement cancelOrder;
    private PreparedStatement checkIfCartBeenOrderedBefore;

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
            if (checkIfCartBeenOrderedBefore != null) {
                checkIfCartBeenOrderedBefore.close();
            }
            if (getTotalAmount != null) {
                getTotalAmount.close();
            }
            if (queryNumberOfDistinctProductsInCart != null) {
                queryNumberOfDistinctProductsInCart.close();
            }
            if (cancelOrder != null) {
                cancelOrder.close();
            }
            if (viewCartContents != null) {
                viewCartContents.close();
            }
            if (queryProductInCartByQuantity != null) {
                queryProductInCartByQuantity.close();
            }
            if (insertOrder != null) {
                insertOrder.close();
            }
            if (updateProductInCart != null) {
                updateProductInCart.close();
            }
            if (queryProductInCart != null) {
                queryProductInCart.close();
            }
            if (removeProductFromCart != null) {
                removeProductFromCart.close();
            }
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

            results.close();
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
            result.close();
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
            result.close();
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

    public int createACart(String cartName, int customerId) {
        String QUERY_CART = "SELECT " + COLUMN_CART_ID + " FROM " + TABLE_CART + " WHERE " + COLUMN_CART_NAME + " = ?";
        String CREATE_A_CART = "INSERT INTO " + TABLE_CART + '(' + COLUMN_CART_NAME + ", " +
                COLUMN_CART_CUSTOMER_ID + ')' + " VALUES (?,?)";
        try {
            queryCart = connection.prepareStatement(QUERY_CART);
            createCart = connection.prepareStatement(CREATE_A_CART, Statement.RETURN_GENERATED_KEYS);

            queryCart.setString(1, cartName);
            ResultSet result = queryCart.executeQuery();
            if (result.next()) {
                System.out.println("Cart by that name already exists");
                return result.getInt(1);
            } else {
                createCart.setString(1, cartName);
                createCart.setInt(2, customerId);
                int affectedRows = createCart.executeUpdate();
                if (affectedRows == 1) {
                    System.out.println("Cart creation successful");

                    ResultSet resultSet = createCart.getGeneratedKeys();
                    if (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                } else {
                    throw new SQLException("Cart creation unsuccessful");
                }
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Couldn't create cart");

        }
        return -1;
    }

    public void addProductToCart(int productId, String productName, double price,
                                 int quantity, double totalAmount, String cartName, int customerId) {
        String QUERY_CART = "SELECT " + COLUMN_CART_ID + " FROM " + TABLE_CART + " WHERE " + COLUMN_CART_NAME + " = ?";
        String ADD_PRODUCT_TO_CART = "INSERT INTO " + TABLE_CART_PRODUCT + " VALUES (?,?,?,?,?,?)";
        int cartId;
        try {
            queryCart = connection.prepareStatement(QUERY_CART);
            insertProductIntoCart = connection.prepareStatement(ADD_PRODUCT_TO_CART);

            queryCart.setString(1, cartName);
            ResultSet result = queryCart.executeQuery();
            if (result.next()) {
                cartId = result.getInt(1);
            } else {
                cartId = createACart(cartName, customerId);
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
            result.close();
        } catch (SQLException e) {
            System.out.println("Couldn't add product to cart: " + e.getMessage());
        }
    }

    public void removeProductFromCart(CartProduct cartProduct) {
        String QUERY_PRODUCT_IN_CART = "SELECT " + COLUMN_CART_PRODUCT_NAME + " FROM " + TABLE_CART_PRODUCT + " WHERE " +
                COLUMN_CART_PRODUCT_ID + " = ?" + " AND " + COLUMN_CART_PRODUCT_CART_ID + " = ?";
        String REMOVE_PRODUCT_FROM_CART = "DELETE FROM " + TABLE_CART_PRODUCT + " WHERE " + COLUMN_CART_PRODUCT_ID + " = ?" +
                " AND " + COLUMN_CART_PRODUCT_CART_ID + " = ?";

        try {
            queryProductInCart = connection.prepareStatement(QUERY_PRODUCT_IN_CART);
            removeProductFromCart = connection.prepareStatement(REMOVE_PRODUCT_FROM_CART);

            queryProductInCart.setInt(1, cartProduct.getProductId());
            queryProductInCart.setInt(2, cartProduct.getCartId());

            ResultSet results = queryProductInCart.executeQuery();
            if (!results.next()) {
                System.out.println("No such product exists on database");
            } else {
                removeProductFromCart.setInt(1, cartProduct.getProductId());
                removeProductFromCart.setInt(2, cartProduct.getCartId());

                int affectedRows = removeProductFromCart.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Couldn't remove product from cart");
                } else {
                    removeProductFromCart.setInt(1, cartProduct.getProductId());
                    removeProductFromCart.setInt(2, cartProduct.getCartId());

                    int affectedRowss = removeProductFromCart.executeUpdate();
                    if (affectedRows == 1) {
                        System.out.println("Product removal was a success");
                    } else {
                        throw new SQLException("Couldn't remove");
                    }
                }
            }

            results.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*SELECT Cart.cart_name, Cart_Product.product_name, Cart_Product.price,
    Cart_Product.quantity, Cart_Product.total_amount FROM Cart_Product
    INNER JOIN Cart ON Cart_Product.cart_id = Cart.cart_id
    ORDER BY Cart.cart_name, Cart_Product.product_name COLLATE NOCASE ASC*/

    public ObservableList<ProductInCart> viewCartContents(Cart cart, int sortOrder) {
        String VIEW_CART_CONTENTS_START = "SELECT " + TABLE_CART + '.' + COLUMN_CART_NAME + ", " + TABLE_CART_PRODUCT +
                '.' + COLUMN_CART_PRODUCT_NAME + ", " + TABLE_CART_PRODUCT + '.' + COLUMN_CART_PRODUCT_PRICE + ", " +
                TABLE_CART_PRODUCT + '.' + COLUMN_CART_PRODUCT_QUANTITY + ", " + TABLE_CART_PRODUCT + '.' +
                COLUMN_CART_PRODUCT_TOTAL_AMOUNT + " FROM " + TABLE_CART_PRODUCT + " INNER JOIN " + TABLE_CART + " ON " +
                TABLE_CART_PRODUCT + '.' + COLUMN_CART_PRODUCT_CART_ID + " = " + TABLE_CART + '.' + COLUMN_CART_ID +
                " WHERE " + TABLE_CART + '.' + COLUMN_CART_ID + " = ?";

        String VIEW_CART_CONTENTS_SORT = " ORDER BY " + TABLE_CART + '.' + COLUMN_CART_NAME + ", " + TABLE_CART_PRODUCT +
                '.' + COLUMN_CART_PRODUCT_NAME + " COLLATE NOCASE ";

        StringBuilder sb = new StringBuilder(VIEW_CART_CONTENTS_START);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(VIEW_CART_CONTENTS_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        ObservableList<ProductInCart> productInCart = FXCollections.observableArrayList();

        try {

            viewCartContents = connection.prepareStatement(sb.toString());
            viewCartContents.setInt(1, cart.getCartId());

            ResultSet results = viewCartContents.executeQuery();

            while (results.next()) {
                ProductInCart product = new ProductInCart();
                product.setCartName(results.getString(1));
                product.setProductName(results.getString(2));
                product.setPrice(results.getDouble(3));
                product.setQuantity(results.getInt(4));
                product.setTotalAmount(results.getDouble(5));

                productInCart.add(product);
            }

            return productInCart;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return null;
    }

    public void updateProductInCart(CartProduct cartProduct) {
        String QUERY_PRODUCT_IN_CART = "SELECT " + COLUMN_CART_PRODUCT_NAME + " FROM " + TABLE_CART_PRODUCT + " WHERE " +
                COLUMN_CART_PRODUCT_ID + " = ?" + " AND " + COLUMN_CART_PRODUCT_CART_ID + " = ?";
        String UPDATE_PRODUCT_IN_CART = "UPDATE " + TABLE_CART_PRODUCT + " SET " + COLUMN_CART_PRODUCT_QUANTITY + " = ?" +
                ", " + COLUMN_CART_PRODUCT_TOTAL_AMOUNT + " = ?" + " WHERE " + COLUMN_CART_PRODUCT_ID + " = ?";
        try {
            queryProductInCart = connection.prepareStatement(QUERY_PRODUCT_IN_CART);
            updateProductInCart = connection.prepareStatement(UPDATE_PRODUCT_IN_CART);

            queryProductInCart.setInt(1, cartProduct.getProductId());
            queryProductInCart.setInt(2, cartProduct.getCartId());
            ResultSet results = queryProductInCart.executeQuery();
            if (!results.next()) {
                System.out.println("No product by that name");
            } else {
                updateProductInCart.setInt(1, cartProduct.getQuantity());
                updateProductInCart.setDouble(2, cartProduct.getTotalAmount());
                updateProductInCart.setInt(3, cartProduct.getProductId());

                int affectedRows = updateProductInCart.executeUpdate();
                if (affectedRows == 1) {
                    System.out.println("Update successful");
                } else {
                    throw new SQLException("Update not successful");
                }

            }

            results.close();
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }

    }

    //TODO A customer can oly order when he has at least 4 different products
    // or when he has a product with quantity >= 4
    public boolean orderCart(Cart cart) {
        String QUERY_DISTINCT_PRODUCTS_IN_CART = "SELECT COUNT(*)" + " FROM " + TABLE_CART_PRODUCT
                + " WHERE " + COLUMN_CART_PRODUCT_CART_ID + " = ?";
        String QUERY_QUANTITY_OF_A_PRODUCT_IN_CART = "SELECT " + COLUMN_CART_PRODUCT_QUANTITY + " FROM " +
                TABLE_CART_PRODUCT + " WHERE " + COLUMN_CART_PRODUCT_CART_ID + " = ?";
        String ORDER_CART = "INSERT INTO " + TABLE_ORDER + '(' + COLUMN_ORDER_DATE + ", " + COLUMN_ORDER_CART_ID +
                ", " + COLUMN_ORDER_CUSTOMER_ID + ", " + COLUMN_ORDER_STATUS + ", " + COLUMN_ORDER_TOTAL_AMOUNT + ')' +
                " VALUES " + "(?,?,?,?,?)";
        String GET_TOTAL_AMOUNT = "SELECT " + COLUMN_CART_PRODUCT_TOTAL_AMOUNT + " FROM " + TABLE_CART_PRODUCT +
                " WHERE " + COLUMN_CART_PRODUCT_CART_ID + " = ?";
        date = LocalDate.now();

        ObservableList<Double> totalAmounts = FXCollections.observableArrayList();
        ObservableList<Integer> quantities = FXCollections.observableArrayList();
        double totalAmount = 0;
        int quantity = 0, count = 0;
        try {
            boolean checkOrder = isCartOrderedBefore(cart);
            if(!checkOrder) {
                insertOrder = connection.prepareStatement(ORDER_CART);
                queryProductInCartByQuantity = connection.prepareStatement(QUERY_QUANTITY_OF_A_PRODUCT_IN_CART);
                queryNumberOfDistinctProductsInCart = connection.prepareStatement(QUERY_DISTINCT_PRODUCTS_IN_CART);
                getTotalAmount = connection.prepareStatement(GET_TOTAL_AMOUNT);

                queryProductInCartByQuantity.setInt(1, cart.getCartId());
                queryNumberOfDistinctProductsInCart.setInt(1, cart.getCartId());
                getTotalAmount.setInt(1, cart.getCartId());


                ResultSet result1 = queryProductInCartByQuantity.executeQuery();
                ResultSet result2 = queryNumberOfDistinctProductsInCart.executeQuery();
                while (result1.next()) {
                    quantities.add(result1.getInt(1));
                    if ((quantity = result1.getInt(1)) > 4) {
                        break;
                    }
                }

                while (result2.next()) {
                    count = result2.getInt(1);
                }

                if (quantity > 4 || count > 4) {
                    ResultSet result3 = getTotalAmount.executeQuery();
                    while (result3.next()) {
                        totalAmounts.add(result3.getDouble(1));
                    }

                    for (int i = 0; i < totalAmounts.size(); i++) {
                        totalAmount += totalAmounts.get(i);
                    }

                    result3.close();

                    insertOrder.setString(1, String.format("%s", date.format(formatter)));
                    insertOrder.setInt(2, cart.getCartId());
                    insertOrder.setInt(3, cart.getCustomerId());
                    insertOrder.setString(4, OrderStatus.NEW.toString());
                    insertOrder.setDouble(5, totalAmount);

                    int affectedRows = insertOrder.executeUpdate();
                    if (affectedRows == 1) {
                        System.out.println("Order successful");
                        return true;
                    } else {
                        throw new SQLException("Order was not successful");
                    }

                }
                result1.close();
                result2.close();
            } else {
                System.out.println("The cart had already been ordered");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isCartOrderedBefore(Cart cart) {
        String QUERY_CART_ID_FROM_ORDERS = "SELECT " + COLUMN_ORDER_ID + " FROM " + TABLE_ORDER + " WHERE " +
                COLUMN_ORDER_CART_ID + " = ?";

        try {
            checkIfCartBeenOrderedBefore = connection.prepareStatement(QUERY_CART_ID_FROM_ORDERS);

            checkIfCartBeenOrderedBefore.setInt(1, cart.getCartId());
            ResultSet results = checkIfCartBeenOrderedBefore.executeQuery();
            if (results.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Couldn't check if order had been already ordered");
        }
        return true;
    }

    public boolean cancelOrder(Order order) {
        String CANCEL_ORDER = "DELETE FROM " + TABLE_ORDER + " WHERE " + COLUMN_ORDER_CART_ID + " = ?" +
                " AND " + COLUMN_ORDER_CUSTOMER_ID + " = ?";

        try {
            cancelOrder = connection.prepareStatement(CANCEL_ORDER);

            cancelOrder.setInt(1, order.getCartId());
            cancelOrder.setInt(2, order.getCustomer_id());

            int affectedRows = cancelOrder.executeUpdate();
            if (affectedRows == 1) {
                System.out.println("Order cancellation successful");
            } else {
                throw new SQLException("Order cancellation unsuccessful");
            }

        } catch (SQLException e) {
            System.out.println("Deletion failed");
        }
        return false;
    }
}
