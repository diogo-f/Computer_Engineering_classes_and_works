/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula08.ex02_Facade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
public class Parking {

    private List<Person> lista = new ArrayList<>();

    public void allow(Person p) {
        System.out.println("Estacionamento permitido a " + p.getName());
        lista.add(p);
    }

    public boolean check(Person p) {
        for (Person pessoa : lista) {
            if (pessoa.equals(p)) {
                return true;
            }
        }
        return false;
    }
}
