/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_07;

/**
 *
 * @author diogo
 */
public class Rectangulo extends Figura {

    private final double lado1, lado2;

    public Rectangulo(int x, int y, double lado1, double lado2) {
        super(x, y);
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public Rectangulo(double lado1, double lado2) {
        super(0, 0);
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public double area() {
        return lado1 * lado2;
    }

    public double perim() {
        return 2 * (lado1 * lado2);
    }

    public double getLado1() {
        return lado1;
    }

    public double getLado2() {
        return lado2;
    }

    @Override
    public String toString() {
        return "Rectangulo: \nArea: " + area() + "\nPerimetro: " + perim() + "\nCoordenadas: " + getP().toString();
    }

    public boolean equals(Object c) {
        if (c == null) {
            return false;
        }
        if (this == c) {
            return true;
        }
        if (getClass() != c.getClass()) {
            return false;
        }
        Rectangulo other = (Rectangulo) c;
        if (getP() == null) {
            if (other.getP() != null) {
                return false;
            }
        } else if (!getP().equals(other.getP())) {
            return false;
        }
        if (lado1 != other.getLado1() || lado2 != other.getLado2()) {
            return false;
        }
        return true;
    }
}
