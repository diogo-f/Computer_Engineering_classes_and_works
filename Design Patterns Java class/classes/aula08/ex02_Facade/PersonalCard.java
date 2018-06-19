/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula08.ex02_Facade;

/**
 *
 * @author diogo
 */
public class PersonalCard {

    String nome;

    public PersonalCard(String nome) {
        this.nome = nome;
    }

    public String getCardInfo() {
        return nome;
    }

}
