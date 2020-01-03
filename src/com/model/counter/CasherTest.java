package com.model.counter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.Assert.*;

public class CasherTest {

    Casher casher = new Casher();
    ObservableList transactions = FXCollections.observableArrayList();

    @Test
    public void createTransactionTable() {
        casher.createTransactionTable();
    }

    @Test
    public void getTransactions() {
        transactions = casher.getTransactions();
        transactions.forEach(transaction -> System.out.println(transaction));
    }

    @Test
    public void addTransaction() {
        Transaction transaction = new Transaction();
        transaction.setProductId(687545); transaction.setDescription("16.5 Fl Oz"); transaction.setQuantity(8);
        transaction.setPrice(70); transaction.setTotalAmount(420); transaction.setCustomerId(5647);
        casher.addTransaction(transaction);
    }

    @Test
    public void updateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setProductId(687545); transaction.setDescription("2 liter bottle"); transaction.setQuantity(7);
        transaction.setPrice(80); transaction.setTotalAmount(560); transaction.setCustomerId(5647);
        casher.updateTransaction(transaction);
    }

    @Test
    public void removeTransaction() {
        Transaction transaction = new Transaction();
        transaction.setProductId(687545); transaction.setDescription("2 liter bottle"); transaction.setQuantity(7);
        transaction.setPrice(80); transaction.setTotalAmount(560); transaction.setCustomerId(5647);
        casher.removeTransaction(transaction);
    }
}