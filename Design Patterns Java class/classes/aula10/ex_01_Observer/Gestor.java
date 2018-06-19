/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula10.ex_01_Observer;

/**
 *
 * @author diogo
 */
public class Gestor implements ClienteInterface_observer {

    private String nome;

    public Gestor(String nome) {
        this.nome = nome;
    }

    @Override
    public void update(Produto p) {
        System.out.println("Notificação do gestor " + nome);
        System.out.println(p.toString());
    }
}
