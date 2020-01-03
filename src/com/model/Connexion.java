package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connexion instance = new Connexion();
    private static final String DB_NAME = "Supermarket.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\meme_\\IdeaProjects" +
            "\\JavaFxExercise" + "\\awkeh-giza\\" + DB_NAME;

    private Connection connection;

    private Connexion() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connexion getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}