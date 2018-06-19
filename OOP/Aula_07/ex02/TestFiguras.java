/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_07.ex02;

/**
 *
 * @author diogo
 */
public class TestFiguras {

    public static void main(String[] args) {
        Circulo c1 = new Circulo(2);
        Circulo c2 = new Circulo(1, 3, 2);
        Quadrado q1 = new Quadrado(2);
        Quadrado q2 = new Quadrado(3, 4, 2);
        Rectangulo r1 = new Rectangulo(2, 3);
        Rectangulo r2 = new Rectangulo(3, 4, 2, 3);

        ColecaoFiguras col = new ColecaoFiguras(42.0);

        System.out.println(col.addFigura(c2));
        System.out.println(col.addFigura(r1));
        System.out.println(col.addFigura(r1));
        System.out.println(col.addFigura(r2));
        System.out.println(col.addFigura(c1));
        System.out.println(col.addFigura(q2));
        System.out.println(col.addFigura(q1));
        System.out.println(col.delFigura(r1));
        System.out.println(col.addFigura(q1));

        System.out.println("\nÁrea Total da Lista de Figuras: " + col.areaTotal());
        System.out.println("\nLista de Figuras:");
        for (Figura f : col.getFiguras()) {
            System.out.println(f);
        }
        System.out.println("\n\nCirculos na Lista de Figuras:");
        for (Figura f : col.getFiguras()) {
            if (f instanceof Circulo) {
                System.out.println(f);
            }
        }
        System.out.println("\n\nCentro das Figuras:");
        for (Ponto p : col.getCentros()) {
            System.out.println(p);
        }
    }
}