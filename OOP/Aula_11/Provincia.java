/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_11;

/**
 *
 * @author diogo
 */
public class Provincia extends Regiao {

    private String governador;

    public Provincia(String nome, int pop, String gov) {
        super(nome, pop);
        this.governador = gov;
    }

    @Override
    public String toString() {
        return super.toString() + " " + governador;
    }

}
