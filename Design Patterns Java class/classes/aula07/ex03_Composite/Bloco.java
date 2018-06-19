/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex03_Composite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
class Bloco extends BlocoComponent {

    private String nome;
    private List<BlocoComponent> window;

    Bloco(String nome) {
        this.nome = nome;
        window = new ArrayList<>();
    }

    public void add(BlocoComponent b) {
        window.add(b);
    }

    @Override
    public void draw() {
        System.out.println("Window " + nome);
        for (BlocoComponent b : window) {
            System.out.printf("     ");
            b.draw();
        }
    }
}
