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
public class Estudante extends Pessoa {

    private final String curso;
    private final int numMec;

    /**
     *
     * @param nome
     * @param cc
     * @param dataNasc
     * @param curso
     * @param numMec
     */
    public Estudante(String nome, int cc, Date dataNasc, String curso, int numMec) {
        super(nome, cc, dataNasc);
        this.curso = curso;
        this.numMec = numMec;
    }

    @Override
    public String toString() {
        return "\n" + super.toString() + "\nCurso: " + curso + "\nnumero mec: " + numMec;
    }

}
