/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula05.ex03_Builder;

/**
 *
 * @author diogo
 */
public class Person {

    private String lastName;
    private String firstName;
    private String middleName;
    private String salutation;
    private String suffix;
    private String streetAddress;
    private String city;
    private String state;
    private boolean isFemale;
    private boolean isEmployed;
    private boolean isHomewOwner;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isIsFemale() {
        return isFemale;
    }

    public void setIsFemale(boolean isFemale) {
        this.isFemale = isFemale;
    }

    public boolean isIsEmployed() {
        return isEmployed;
    }

    public void setIsEmployed(boolean isEmployed) {
        this.isEmployed = isEmployed;
    }

    public boolean isIsHomewOwner() {
        return isHomewOwner;
    }

    public void setIsHomewOwner(boolean isHomewOwner) {
        this.isHomewOwner = isHomewOwner;
    }

    @Override
    public String toString() {
        return "\nPerson:" + "\nfirstName=" + firstName + "\nmiddleName=" + middleName + "\nlastName=" + lastName + "\nsalutation=" + salutation + "\nsuffix=" + suffix + "\nstreetAddress=" + streetAddress + "\ncity=" + city + "\nstate=" + state + "\nisFemale=" + (isFemale ? "sim" : "nao") + "\nisEmployed=" + (isEmployed ? "sim" : "nao") + "\nisHomewOwner=" + (isHomewOwner ? "sim" : "nao");
    }

}
