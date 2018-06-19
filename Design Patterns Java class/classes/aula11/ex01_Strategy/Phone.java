/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula11.ex01_Strategy;

/**
 *
 * @author diogo
 */
public class Phone {

    private String processador;
    private double preco;
    private int memoriaRam;
    private double camera;

    public Phone(String processador, int memoriaRam, double camera, double preco) {
        this.processador = processador;
        this.preco = preco;
        this.memoriaRam = memoriaRam;
        this.camera = camera;
    }

    @Override
    public String toString() {
        return "Phone: " + "processador=" + processador + ", preco=" + preco + ", memoriaRam=" + memoriaRam + ", camera=" + camera;
    }

}
