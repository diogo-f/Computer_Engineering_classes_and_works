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
public class PersonDirector {

    PersonBuilder personBuilder;

    public PersonDirector(PersonBuilder personBuilder) {
        this.personBuilder = personBuilder;
    }

    public void makePerson() {
        personBuilder.setFirstName();
        personBuilder.setMiddleName();
        personBuilder.setLastName();
        personBuilder.setSuffix();
        personBuilder.setStreetAddress();
        personBuilder.setState();
        personBuilder.setCity();
        personBuilder.setSalutation();
        personBuilder.setIsFemale();
        personBuilder.setIsEmployed();
        personBuilder.setIsHomewOwner();
    }

    public Person getPerson() {
        return personBuilder.getPerson();
    }
}
