/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_09;

/**
 *
 * @author diogo
 */
public class Carne extends Alimento {

    private VariedadeCarne tipo;
    private double proteinas;
    private double calorias;
    private double peso;

    public Carne(VariedadeCarne tipo, double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.tipo = tipo;
    }

    public VariedadeCarne getVariedadeCarne() {
        return tipo;
    }

    public void setVariedadeCarne(VariedadeCarne tipo) {
        this.tipo = tipo;
    }

}
