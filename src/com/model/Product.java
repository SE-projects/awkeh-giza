package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Product {
    private int id;
    private String productName;
    private String category;
    private double rating;
    private int quantity;
    private String brandName;
    private LocalDate expirationDate;
    private String description;
    private double price;
    private int shelfNumber;
    private String isleName;
    private String supermarketName;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getIsleName() {
        return isleName;
    }

    public void setIsleName(String isleName) {
        this.isleName = isleName;
    }

    public String getSupermarketName() {
        return supermarketName;
    }

    public void setSupermarketName(String supermarketName) {
        this.supermarketName = supermarketName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.rating, rating) == 0 &&
                quantity == product.quantity &&
                Double.compare(product.price, price) == 0 &&
                shelfNumber == product.shelfNumber &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(category, product.category) &&
                Objects.equals(brandName, product.brandName) &&
                Objects.equals(expirationDate, product.expirationDate) &&
                Objects.equals(description, product.description) &&
                Objects.equals(isleName, product.isleName) &&
                Objects.equals(supermarketName, product.supermarketName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, category, rating, quantity, brandName,
                expirationDate, description, price, shelfNumber, isleName, supermarketName);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                ", quantity=" + quantity +
                ", brandName='" + brandName + '\'' +
                ", expirationDate=" + expirationDate.format(formatter) +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", shelfNumber=" + shelfNumber +
                ", isleName='" + isleName + '\'' +
                ", supermarketName='" + supermarketName + '\'' +
                '}';
    }
}
