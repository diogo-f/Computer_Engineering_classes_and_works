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
public class Quadrado extends Figura {

    private final double lado;
   

    public Quadrado(int x, int y, double lado) {
        super(x, y);
        this.lado= lado;
    }

    public Quadrado(double lado) {
        super(0, 0);
        this.lado = lado;
    }
    
    @Override
    public double area() {
        return lado * lado;
    }
    @Override
    public double perim() {
        return 4 * lado;
    }
    
    @Override
    public String toString() {
        return "Quadrado: \nArea: " + area() + "\nPerimetro: " + perim() + "\nCoordenadas: " + getP().toString();
    }
    
    
    public boolean equals(Object c) {
        if (c == null) {
            return false;
        }
        else if (this == c) {
            return true;
        }
        
        
        if (getClass() != c.getClass()) {
            return false;
        }
        Quadrado other = (Quadrado) c;
        if (getP() == null) {
            if (other.getP() != null) {
                return false;
            }
        } else if (!getP().equals(other.getP())) {
            return false;
        }
        if (lado != other.getLado()) {
            return false;
        }
        return true;
    }

    public double getLado() {
        return lado;
    }
}
