/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_09;

import java.util.List;

/**
 *
 * @author diogo
 */
public class PratoDieta extends Prato {

    private double maxCal;

    public PratoDieta(String nome, List listaAlimentos, double maxCal) {
        super(nome, listaAlimentos);
        this.maxCal = maxCal;
    }

    public double getMaxCal() {
        return maxCal;
    }

    public void setMaxCal(double maxCal) {
        this.maxCal = maxCal;
    }

}
