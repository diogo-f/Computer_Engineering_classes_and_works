/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_09;

import java.util.Random;

/**
 *
 * @author diogo
 */
public class Ementa {

    private String nome;
    private String local;

    public String diaSemana() {
        int dia;
        String diaSem = null;
        Random rand = new Random();
        dia = (rand.nextInt((7 - 1) + 1) + 1);

        switch (dia) {
            case 1:
                diaSem =  "Segunda";
            case 2:
                diaSem =  "Terça";
            case 3:
                diaSem =  "Quarta";
            case 4:
                diaSem =  "Quinta";
            case 5:
                diaSem =  "Sexta";
            case 6:
                diaSem =  "Sabado";
            case 7:
                diaSem =  "Domingo";
        }
        return diaSem;
    }
}
