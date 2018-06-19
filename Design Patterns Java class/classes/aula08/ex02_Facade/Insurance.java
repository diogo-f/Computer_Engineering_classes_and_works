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
public class Insurance {

    private List<Person> listaReg;

    public Insurance() {
        listaReg = new ArrayList<>();
    }

    public void regist(Person p) {
        System.out.println("Adicionado seguro a " + p.getName());
        listaReg.add(p);
    }

    public List getAllReg() {
        return listaReg;
    }

}
