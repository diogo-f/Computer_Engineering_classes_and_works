/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula02_VideoClube;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diogo
 */
public class Pessoa {

    private final String nome;
    private final int numCC;
    private final Date dataNasc;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     *
     * @param nome
     * @param numCC
     * @param dataNasc
     */
    public Pessoa(String nome, int numCC, Date dataNasc) {
        this.nome = nome;
        this.numCC = numCC;
        this.dataNasc = dataNasc;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nNumero CC: " + numCC + "\nData Nascimento: " + dateFormat.format(dataNasc);
    }

}
