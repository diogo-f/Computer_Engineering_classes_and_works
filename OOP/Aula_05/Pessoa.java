/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_05;

/**
 *
 * @author diogo
 */
public class Pessoa {

    private String nome;
    private int cc;
    private Data dataNasc;

    public Pessoa(String n, int c, Data d){
        this.nome = n;
        this.cc = c;
        this.dataNasc = d;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public Data getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Data dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public String toString() {
        return "\nNome: " + nome + "\ncc: " + cc + dataNasc;
    }
}
