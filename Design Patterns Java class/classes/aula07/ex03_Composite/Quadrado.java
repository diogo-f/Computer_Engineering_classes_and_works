/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex03_Composite;

/**
 *
 * @author diogo
 */
class Quadrado extends BlocoComponent {

    private String nomeC = "Quadrado";
    private String nome;

    public Quadrado(String nome) {
        this.nome = nome;
    }

    @Override
    public void draw() {
        System.out.println(nomeC + " " + nome);
    }
}
