/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula05.ex03_InnerClassBuilder;

/**
 *
 * @author diogo
 */
public class Person {

    private final String lastName;
    private final String firstName;
    private final String middleName;
    private String salutation;
    private String suffix;
    private final String streetAddress;
    private final String city;
    private final String state;
    private boolean isFemale;
    private boolean isEmployed;
    private boolean isHomewOwner;

    private Person(Builder b) {
        this.lastName = b.lastName;
        this.firstName = b.firstName;
        this.middleName = b.middleName;
        this.streetAddress = b.streetAddress;
        this.city = b.city;
        this.state = b.state;
        this.salutation = b.salutation;
        this.suffix = b.suffix;
        this.isEmployed = b.isEmployed;
        this.isFemale = b.isFemale;
        this.isHomewOwner = b.isHomewOwner;
    }

    public static class Builder {

        //Required
        private final String lastName;
        private final String firstName;
        private final String middleName;
        private final String streetAddress;
        private final String city;
        private final String state;
        //Optional
        private String salutation = "none";
        private String suffix = "none";
        private boolean isFemale = false;
        private boolean isEmployed = false;
        private boolean isHomewOwner = false;

        public Builder(String lastName, String firstName, String middleName, String streetAddress, String city, String state) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.streetAddress = streetAddress;
            this.city = city;
            this.state = state;
        }

        public Builder salutation(String salut) {
            salutation = salut;
            return this;
        }

        public Builder suffix(String suf) {
            suffix = suf;
            return this;
        }

        public Builder isEmployed(boolean isEmp) {
            isEmployed = isEmp;
            return this;
        }

        public Builder isFemale(boolean isFem) {
            isFemale = isFem;
            return this;
        }

        public Builder isHomeOwner(boolean isHmOw) {
            isHomewOwner = isHmOw;
            return this;
        }

        //Cria a pessoa com o construtor privado
        public Person build() {
            return new Person(this);
        }

    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean isIsFemale() {
        return isFemale;
    }

    public boolean isIsEmployed() {
        return isEmployed;
    }

    public boolean isIsHomewOwner() {
        return isHomewOwner;
    }

    @Override
    public String toString() {
        return "\nPerson:" + "\nfirstName=" + firstName + "\nmiddleName=" + middleName + "\nlastName=" + lastName + "\nsalutation=" + salutation + "\nsuffix=" + suffix + "\nstreetAddress=" + streetAddress + "\ncity=" + city + "\nstate=" + state + "\nisFemale=" + (isFemale ? "sim" : "nao") + "\nisEmployed=" + (isEmployed ? "sim" : "nao") + "\nisHomewOwner=" + (isHomewOwner ? "sim" : "nao");
    }

}
