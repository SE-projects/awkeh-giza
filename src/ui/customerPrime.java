package ui;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class customerPrime {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty streetname;
    private final StringProperty subcity;
    private final StringProperty city;
    private final StringProperty homenumber;

    public customerPrime(String fname, String lname, String em, String pnumber, String hnumber,  String sname, String subc, String cty ){
        this.firstName=new SimpleStringProperty(fname);
        this.lastName=new SimpleStringProperty(lname);
        this.email=new SimpleStringProperty(em);
        this.phone=new SimpleStringProperty(pnumber);
        this.streetname=new SimpleStringProperty(sname);
        this.homenumber=new SimpleStringProperty(hnumber);
        this.subcity=new SimpleStringProperty(cty);
        this.city=new SimpleStringProperty(subc);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getStreetname() {
        return streetname.get();
    }

    public StringProperty streetnameProperty() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname.set(streetname);
    }

    public String getSubcity() {
        return subcity.get();
    }

    public StringProperty subcityProperty() {
        return subcity;
    }

    public void setSubcity(String subcity) {
        this.subcity.set(subcity);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getHomenumber() {
        return homenumber.get();
    }

    public StringProperty homenumberProperty() {
        return homenumber;
    }

    public void setHomenumber(String homenumber) {
        this.homenumber.set(homenumber);
    }
}
