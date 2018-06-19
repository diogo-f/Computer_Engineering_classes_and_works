/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula05.ex03_Builder;

import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class NewPersonBuilder implements PersonBuilder {

    final Scanner sc = new Scanner(System.in);

    Person person;

    public NewPersonBuilder() {
        this.person = new Person();
    }

    @Override
    public void setLastName() {
        System.out.println("last name: ");
        person.setLastName(sc.nextLine());
    }

    @Override
    public void setFirstName() {
        System.out.println("first name: ");
        person.setFirstName(sc.nextLine());
    }

    @Override
    public void setMiddleName() {
        System.out.println("middle name: ");
        person.setMiddleName(sc.nextLine());
    }

    @Override
    public void setSalutation() {
        System.out.println("salutation: ");
        person.setSalutation(sc.nextLine());
    }

    @Override
    public void setSuffix() {
        System.out.println("suffix: ");
        person.setSuffix(sc.nextLine());
    }

    @Override
    public void setStreetAddress() {
        System.out.println("street address: ");
        person.setStreetAddress(sc.nextLine());
    }

    @Override
    public void setCity() {
        System.out.println("cidade: ");
        person.setCity(sc.nextLine());
    }

    @Override
    public void setState() {
        System.out.println("state: ");
        person.setState(sc.nextLine());
    }

    private boolean askBoolean() {
        boolean temp = false;
        String sn;

        do {
            sn = sc.nextLine();
            if (sn.equals("s")) {
                temp = true;
                return true;
            } else if (sn.equals("n")) {
                temp = true;
                return false;
            }
        } while (temp);
        return true;
    }

    @Override
    public void setIsFemale() {
        System.out.println("é mulher(s/n): ");
        person.setIsFemale(askBoolean());
    }

    @Override
    public void setIsEmployed() {
        System.out.println("esta empregada(s/n): ");
        person.setIsEmployed(askBoolean());
    }

    @Override
    public void setIsHomewOwner() {
        System.out.println("é dona de casa(s/n): ");
        person.setIsHomewOwner(askBoolean());
    }

    @Override
    public Person getPerson() {
        return this.person;
    }

}
