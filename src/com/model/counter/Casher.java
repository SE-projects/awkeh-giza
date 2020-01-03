package com.model.counter;

import javafx.collections.ObservableList;

public class Casher {
    private String username;
    private String password;

    private CasherQueries caq = new CasherQueries();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createTransactionTable() {
        caq.createTransactionTable();
    }

    public ObservableList<Transaction> getTransactions() {
        ObservableList<Transaction> transactions = caq.queryTransaction();
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        caq.addTransaction(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        caq.updateTransaction(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        caq.removeTransaction(transaction);
    }

    public void closeConnection() {
        caq.closeConnection();
    }
}
