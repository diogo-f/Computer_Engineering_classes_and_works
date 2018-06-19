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
public class Regiao {

    private String nome;
    private int populacao;

    public Regiao(String nome, int populacao) {
        this.nome = nome;
        this.populacao = populacao;
    }

    @Override
    public String toString() {
        return nome + " População: " + populacao;
    }
}
