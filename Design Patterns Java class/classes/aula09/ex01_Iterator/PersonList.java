/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex01_Iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author diogo
 */
public class PersonList implements PersonIterator {

    ArrayList<Person> lista;

    public PersonList() {
        this.lista = new ArrayList<>();
    }

    public boolean addPerson(String name, int age) {
        Person p = new Person(name, age);
        if (lista.add(p)) {
            return true;
        } else {
            return false;
        }
    }

    public Person removePerson(String name, int age) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getName().equals(name) && lista.get(i).getAge() == age) {
                return lista.remove(i);
            }
        }
        return null;
    }

    public Iterator returnListIterator() {
        return lista.iterator();
    }
}
