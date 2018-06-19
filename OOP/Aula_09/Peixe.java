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
public class Peixe extends Alimento {

    private VariedadePeixe tipo;
    private double proteinas;
    private double calorias;
    private double peso;

    public Peixe(VariedadePeixe tipo, double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.tipo = tipo;
    }

    public VariedadePeixe getVariedadePeixe() {
        return tipo;
    }

    public void setVariedadePeixe(VariedadePeixe tipo) {
        this.tipo = tipo;
    }

}
