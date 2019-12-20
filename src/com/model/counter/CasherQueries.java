package com.model.counter;

import java.sql.*;

import com.model.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CasherQueries {
    private Connection connection;

    private static final String TABLE_TRANSACTION = "Transactions";
    private static final String COLUMN_TRANSACTION_ID = "transaction_id";
    private static final String COLUMN_TRANSACTION_PRODUCT_ID = "product_id";
    private static final String COLUMN_TRANSACTION_DESCRIPTION = "description";
    private static final String COLUMN_TRANSACTION_QUANTITY = "quantity";
    private static final String COLUMN_TRANSACTION_PRICE = "price";
    private static final String COLUMN_TRANSACTION_TOTAL_AMOUNT = "total_amount";
    private static final String COLUMN_TRANSACTION_CUSTOMER_ID = "customer_id";
    private static final int INDEX_TRANSACTION_ID = 1;
    private static final int INDEX_TRANSACTION_PRODUCT_ID = 2;
    private static final int INDEX_TRANSACTION_DESCRIPTION = 3;
    private static final int INDEX_TRANSACTION_QUANTITY = 4;
    private static final int INDEX_TRANSACTION_PRICE = 5;
    private static final int INDEX_TRANSACTION_TOTAL_AMOUNT = 6;
    private static final int INDEX_TRANSACTION_CUSTOMER_ID = 7;

    private PreparedStatement addTransaction;
    private PreparedStatement removeTransaction;
    private PreparedStatement updateTransaction;
    private PreparedStatement queryTransactionIdByProductId;

    public void closeConnection() {
        try {
            if(queryTransactionIdByProductId != null){
                queryTransactionIdByProductId.close();
            }
            if (updateTransaction != null) {
                updateTransaction.close();
            }
            if (removeTransaction != null) {
                removeTransaction.close();
            }
            if (addTransaction != null) {
                addTransaction.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public boolean establishConnection() {
        connection = Connexion.getInstance().getConnection();
        if (connection == null) {
            System.out.println("Couldn't establish connection");
            return false;
        }
        return true;
    }

    public void createTransactionTable() {
        String CREATE_TRANSACTION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTION + '(' + COLUMN_TRANSACTION_ID +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + COLUMN_TRANSACTION_PRODUCT_ID + " INTEGER, " +
                COLUMN_TRANSACTION_DESCRIPTION + " TEXT, " + COLUMN_TRANSACTION_QUANTITY + " INTEGER, " +
                COLUMN_TRANSACTION_PRICE + " REAL, " + COLUMN_TRANSACTION_TOTAL_AMOUNT + " REAL, " +
                COLUMN_TRANSACTION_CUSTOMER_ID + " INTEGER" + ')';

        try(Statement statement = connection.createStatement()){
            statement.execute(CREATE_TRANSACTION_TABLE);
        } catch (SQLException e){
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }

    public ObservableList<Transaction> queryTransaction() {
        String QUERY_TRANSACTION = "SELECT * FROM " + TABLE_TRANSACTION;
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(QUERY_TRANSACTION)) {

            while (results.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(results.getInt(INDEX_TRANSACTION_ID));
                transaction.setProductId(results.getInt(INDEX_TRANSACTION_PRODUCT_ID));
                transaction.setDescription(results.getString(INDEX_TRANSACTION_DESCRIPTION));
                transaction.setQuantity(results.getInt(INDEX_TRANSACTION_QUANTITY));
                transaction.setPrice(results.getDouble(INDEX_TRANSACTION_PRICE));
                transaction.setTotalAmount(results.getDouble(INDEX_TRANSACTION_TOTAL_AMOUNT));
                transaction.setCustomerId(results.getInt(INDEX_TRANSACTION_CUSTOMER_ID));

                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return null;
    }

    public void addTransaction(Transaction transaction) {
        String INSERT_TRANSACTION = "INSERT INTO " + TABLE_TRANSACTION + '(' + COLUMN_TRANSACTION_PRODUCT_ID + ", " +
                COLUMN_TRANSACTION_DESCRIPTION + ", " + COLUMN_TRANSACTION_QUANTITY + ", " + COLUMN_TRANSACTION_PRICE +
                ", " + COLUMN_TRANSACTION_TOTAL_AMOUNT + ", " + COLUMN_TRANSACTION_CUSTOMER_ID + ')' +
                " VALUES " + "(?,?,?,?,?,?)";

        try {
            addTransaction = connection.prepareStatement(INSERT_TRANSACTION);

            addTransaction.setInt(1, transaction.getProductId());
            addTransaction.setString(2, transaction.getDescription());
            addTransaction.setInt(3, transaction.getQuantity());
            addTransaction.setDouble(4, transaction.getPrice());
            addTransaction.setDouble(5, transaction.getTotalAmount());
            addTransaction.setInt(6, transaction.getCustomerId());

            int affectedRows = addTransaction.executeUpdate();
            if (affectedRows == 1) {
                System.out.println("Insertion successful");
            } else {
                throw new SQLException("Couldn't insert");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't insert transaction");
        }
    }

    public void removeTransaction(Transaction transaction) {
        String QUERY_TRANSACTION_ID = "SELECT " + COLUMN_TRANSACTION_ID + " FROM " + TABLE_TRANSACTION + " WHERE "
                + COLUMN_TRANSACTION_PRODUCT_ID + " = ?";
        String REMOVE_TRANSACTION = "DELETE FROM " + TABLE_TRANSACTION + " WHERE " + COLUMN_TRANSACTION_ID + " = ?";
        int transactionId;
        try {
            queryTransactionIdByProductId = connection.prepareStatement(QUERY_TRANSACTION_ID);
            removeTransaction = connection.prepareStatement(REMOVE_TRANSACTION);

            queryTransactionIdByProductId.setInt(1, transaction.getProductId());
            ResultSet result = queryTransactionIdByProductId.executeQuery();

            transactionId = result.getInt(1);
            removeTransaction.setInt(1, transactionId);
            int affectedRows = removeTransaction.executeUpdate();
            if (affectedRows == 1) {
                System.out.println("Removal successful");
            } else {
                throw new SQLException("Removal was unsuccessful");
            }

        } catch (SQLException e) {
            System.out.println("Couldn't remove: " + e.getMessage());
        }
    }

    public void updateTransaction(Transaction transaction) {
        String UPDATE_TRANSACTION = "UPDATE " + TABLE_TRANSACTION + " SET " + COLUMN_TRANSACTION_PRODUCT_ID + " = ?, " +
                COLUMN_TRANSACTION_DESCRIPTION + " = ?, " + COLUMN_TRANSACTION_QUANTITY + " = ?, " + COLUMN_TRANSACTION_PRICE
                + " = ?, " + COLUMN_TRANSACTION_TOTAL_AMOUNT + " = ?, " + COLUMN_TRANSACTION_CUSTOMER_ID + " = ?";

        try {
            updateTransaction = connection.prepareStatement(UPDATE_TRANSACTION);

            updateTransaction.setInt(1, transaction.getProductId());
            updateTransaction.setString(2, transaction.getDescription());
            updateTransaction.setInt(3, transaction.getQuantity());
            updateTransaction.setDouble(4, transaction.getPrice());
            updateTransaction.setDouble(5, transaction.getTotalAmount());
            updateTransaction.setInt(6, transaction.getCustomerId());

            int affectedRows = updateTransaction.executeUpdate();
            if (affectedRows == 1) {
                System.out.println("Update successful");
            } else {
                throw new SQLException("update was not successful");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't update: " + e.getMessage());
        }

    }
}


