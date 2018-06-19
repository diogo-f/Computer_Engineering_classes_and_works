/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_05;

/**
 *
 * @author diogo
 */
public class Bolseiro extends Aluno {

    private int bolsa;

    public Bolseiro(String n, int cc, Data da, Data insc) {
        super(n, cc, da, insc);
        this.bolsa = 0;
    }

    public Bolseiro(String n, int cc, Data da) {
        super(n, cc, da);
        this.bolsa = 0;
    }

    public int getBolsa() {
        return bolsa;
    }
    
    public int getNMec(){
        return super.getNmec();
    }

    public void setBolsa(int bolsa) {
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
