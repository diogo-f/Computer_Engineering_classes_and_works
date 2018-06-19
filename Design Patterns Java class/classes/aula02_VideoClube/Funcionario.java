/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula02_VideoClube;

import java.util.Date;

/**
 *
 * @author diogo
 */
public class Funcionario extends Pessoa {

    private final int numFunc;
    private final int nif;

    /**
     *
     * @param nome
     * @param cc
     * @param dataNasc
     * @param numFunc
     * @param nif
     */
    public Funcionario(String nome, int cc, Date dataNasc, int numFunc, int nif) {
        super(nome, cc, dataNasc);
        this.numFunc = numFunc;
        this.nif = nif;
    }

    @Override
    public String toString() {
        return "\n" + super.toString() + "\nNumero funcionario: " + numFunc + "\nNIF: " + nif;
    }

}
