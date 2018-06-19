/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex01_Iterator;

import java.util.Iterator;

/**
 *
 * @author diogo
 */
public class IteratorMain {

    public static void main(String[] args) {
        PersonList lista = new PersonList();

        for (int i = 0; i < 5; i++) {
            lista.addPerson("nome " + i, 20 + i);
        }

        printInfoPeople(lista.returnListIterator());
        System.out.println("a remover " + lista.removePerson("nome 1", 21).toString());;
        printInfoPeople(lista.returnListIterator());
    }

    public static void printInfoPeople(Iterator it) {
        while (it.hasNext()) {
            Person p = (Person) it.next();
            System.out.println(p.toString());
        }
    }
}
