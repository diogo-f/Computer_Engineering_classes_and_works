/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_06;

/**
 *
 * @author diogo
 */
public class Bolseiro extends Aluno {

    private double bolsa;

    public Bolseiro(String n, int cc, Data da, Data insc) {
        super(n, cc, da, insc);
        this.bolsa = 0;
    }

    public Bolseiro(String n, int cc, Data da, double bol) {
        super(n, cc, da);
        this.bolsa = bol;
    }

    public double getBolsa() {
        return bolsa;
    }
    
    public int getNMec(){
        return super.getNmec();
    }

    public void setBolsa(double bolsa) {
        this.bolsa = bolsa;
    }

    @Override
    public String toString() {
        if (bolsa != 0) {
            return "\nBolseiro : " + bolsa;
        } else {
            return "\nNão bolseiro";
        }
    }
}
