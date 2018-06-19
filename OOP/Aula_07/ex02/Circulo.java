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
public class Circulo extends Figura {

    private final double raio;
    

    public Circulo(int x, int y, double raio) {
        super(x, y);
        this.raio = raio;
    }

    public Circulo(double raio) {
        super(0, 0);
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    

    @Override
    public String toString() {
        return "Circulo: \nArea: " + area() + "\nPerimetro: " + perim() + "\nCoordenadas: " + super.getP().toString();
    }

    public boolean equals(Object c) {
        if(c==null){
            return false;
        }
        else if(this==c){
            return true;
        }
        else if (getClass() != c.getClass()) {
            return false;
        }
        Circulo other = (Circulo) c;
        if (getP() == null) {
            if (other.getP() != null) {
                return false;
            }
        } else if (!getP().equals(other.getP())) {
            return false;
        }
        if (this.raio != other.getRaio()) {
            return false;
        }
        return true;
    }

    @Override
    double area() {
        return raio * raio * Math.PI;
    }

    @Override
    double perim() {
        return 2 * raio * Math.PI;
    }

    
}
