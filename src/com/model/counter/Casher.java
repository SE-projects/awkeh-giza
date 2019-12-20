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
        if (!caq.establishConnection()) {
            System.out.println("Couldn't open transaction");
        }
        caq.createTransactionTable();

        caq.closeConnection();
    }

    public ObservableList<Transaction> getTransactions() {
        if (!caq.establishConnection()) {
            System.out.println("Couldn't open transaction");
        }

//        caq.closeConnection();

       ObservableList<Transaction> transactions = caq.queryTransaction();

       caq.closeConnection();
       return transactions;
    }

    public void addTransaction(Transaction transaction) {
        if (!caq.establishConnection()) {
            System.out.println("Couldn't open connection");
        }

        caq.addTransaction(transaction);

        caq.closeConnection();
    }

    public void updateTransaction(Transaction transaction) {
        if (!caq.establishConnection()) {
            System.out.println("Couldn't open transaction");
        }

        caq.updateTransaction(transaction);

        caq.closeConnection();
    }

    public void removeTransaction(Transaction transaction) {
        if (!caq.establishConnection()) {
            System.out.println("Couldn't open connection");
        }

        caq.removeTransaction(transaction);

        caq.closeConnection();
    }
}
