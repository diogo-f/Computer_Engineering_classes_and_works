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
public class PersonMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PersonBuilder personBuilder = new NewPersonBuilder();
        PersonDirector personDirector = new PersonDirector(personBuilder);
        personDirector.makePerson();
        System.out.println(personDirector.getPerson());
    }
    
}
