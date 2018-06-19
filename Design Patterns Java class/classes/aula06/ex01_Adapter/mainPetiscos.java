/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex01_Adapter;

import java.util.List;

/**
 *
 * @author diogo
 */
public class mainPetiscos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Registos regData = new Registos();

        Empregado emp1 = new Empregado("alguem1", "silva", 1, 1000);
        Empregado emp2 = new Empregado("alguem2", "martins", 2, 2000);
        Empregado emp3 = new Empregado("alguem3", "reis", 3, 3000);

        regData.insere(emp1);
        regData.insere(emp2);
        regData.insere(emp3);

        System.out.println("emp1: " + (regData.isEmpregado(1) ? "sim" : "nao"));
        System.out.println("emp2: " + (regData.isEmpregado(2) ? "sim" : "nao"));
        System.out.println("emp3: " + (regData.isEmpregado(3) ? "sim" : "nao"));

        regData.remove(1);

        System.out.println("emp1: " + (regData.isEmpregado(1) ? "sim" : "nao"));
        System.out.println("emp2: " + (regData.isEmpregado(2) ? "sim" : "nao"));
        System.out.println("emp3: " + (regData.isEmpregado(3) ? "sim" : "nao"));

        List<Empregado> listaEmps = regData.listaDeEmpregados();

        for (Empregado emps : listaEmps) {
            System.out.println(emps.nome() + " " + emps.apelido() + " " + emps.salario());
        }
    }

}
