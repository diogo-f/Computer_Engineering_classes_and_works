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
class Rectangulo extends BlocoComponent {

    private String nomeC = "Rectangulo";
    private String nome;

    public Rectangulo(String nome) {
        this.nome = nome;
    }

    @Override
    public void draw() {
        System.out.println(nomeC + " " + nome);
    }
}
