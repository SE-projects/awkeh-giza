package com.model.customer;

import java.util.Objects;

public class RegisteredCustomerData {
    private String userName;
    private String phone;
    private String email;
    private String streetName;
    private int homeNumber;
    private String subCity;
    private String city;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getSubCity() {
        return subCity;
    }

    public void setSubCity(String subCity) {
        this.subCity = subCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredCustomerData that = (RegisteredCustomerData) o;
        return homeNumber == that.homeNumber &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(streetName, that.streetName) &&
                Objects.equals(subCity, that.subCity) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, phone, email, streetName, homeNumber, subCity, city);
    }

    @Override
    public String toString() {
        return "RegisteredCustomerData{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", streetName='" + streetName + '\'' +
                ", homeNumber=" + homeNumber +
                ", subCity='" + subCity + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
