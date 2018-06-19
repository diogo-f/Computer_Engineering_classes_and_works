/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_09;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
public class Prato {

    private String nome;
    private List<Alimento> listaAlimentos;

    public Prato(String nome, List listaAlimentos) {
        this.nome = nome;
        listaAlimentos = new ArrayList<>(listaAlimentos);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
