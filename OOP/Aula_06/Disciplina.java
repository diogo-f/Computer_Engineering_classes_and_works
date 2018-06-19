/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_06;

/**
 *
 * @author diogo
 */
public class Disciplina {

    private String nome;
    private Professor profDisc;
    private int size;

    public Disciplina(String nome, Professor profDisc) {
        this.size = 0;
        this.nome = nome;
        this.profDisc = profDisc;
    }

    @Override
    public String toString() {
        String disc = "Disciplina: " + nome;
        String prof = "\nProfessor da Disc.: " + profDisc.toString();
        return disc + prof;
    }

}
