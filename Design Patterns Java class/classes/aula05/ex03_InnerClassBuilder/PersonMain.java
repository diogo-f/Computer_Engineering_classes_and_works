/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula05.ex03_InnerClassBuilder;

import aula05.ex03_Builder.*;

/**
 *
 * @author diogo
 */
public class PersonMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Person pessoa1 = new Person.Builder("lastname 1", "firstname 1", "middlename 1", "street 1", "city 1", "state 1").isEmployed(true).isFemale(true).salutation("ola").build();
        Person pessoa2 = new Person.Builder("lastname 2", "firstname 2", "middlename 2", "street 2", "city 2", "state 2").isFemale(true).salutation("ola ola").build();
        Person pessoa3 = new Person.Builder("lastname 3", "firstname 3", "middlename 3", "street 3", "city 3", "state 3").isFemale(true).salutation("ola ola ola").isHomeOwner(true).suffix("sufix3").build();
        
        System.out.println(pessoa1);
        System.out.println(pessoa2);
        System.out.println(pessoa3);
    }
    
}
