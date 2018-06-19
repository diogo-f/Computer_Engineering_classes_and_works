/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_11;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
public class Pais {

    private String nome;
    private Localidade capital;
    private List<Regiao> regiao;
    private int totalPop;

    public Pais(String nome) {
        this(nome, null);
    }

    public Pais(String nome, Localidade capital) {
        this.nome = nome;
        this.capital = capital;
        this.regiao = new ArrayList<>();
        this.totalPop = 0;
    }

    public void addRegiao(Regiao reg) {
        regiao.add(reg);
    }

    @Override
    public String toString() {
        return "País: " nome + ", População: " + totalPop + "...";
    }
}
