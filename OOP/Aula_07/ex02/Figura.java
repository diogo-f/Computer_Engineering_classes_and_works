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
public abstract class Figura {

    private double area;
    private double perim;
    private Ponto p;

    public Figura(int x, int y) {
        p = new Ponto(x, y);
    }

    abstract double area() ;

    abstract double perim();

    public Ponto getP() {
        return p;
    }
}
