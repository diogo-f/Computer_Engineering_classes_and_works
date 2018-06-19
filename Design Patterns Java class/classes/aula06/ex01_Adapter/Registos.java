/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex01_Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
public class Registos {
// Data elements

    private ArrayList<Empregado> empregados; // Stores the employees

    public Registos() {
        empregados = new ArrayList<>();
    }

    public void insere(Empregado emp) {
        empregados.add(emp);
    }

    public void remove(int codigo) {
        for (int i = 0; i < empregados.size(); i++) {
            if (empregados.get(i).codigo() == codigo) {
                System.out.println("A remover " + empregados.get(i).nome());
                empregados.remove(i);
            }
        }
    }

    public boolean isEmpregado(int codigo) {
        boolean temp = false;
        for (int i = 0; i < empregados.size(); i++) {
            if (empregados.get(i).codigo() == codigo) {
                temp = true;
            }
        }
        return temp;
    }

    public List<Empregado> listaDeEmpregados() {
        return empregados;
    }

    public void addThingsToDB() {
        System.out.println("a carregar DB Registos");
        for (int i = 10; i < 15; i++) {
            insere(new Empregado("pessoa " + i, "apelido", i, i + 500));
        }
    }
}
