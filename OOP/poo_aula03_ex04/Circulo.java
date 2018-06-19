/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_aula03_ex04;

/**
 *
 * @author diogo
 */
public class Circulo {
    private final String color;
    private final double raio;
    private int cordenadax,cordenaday;
    private double area = 0, perim = 0;
    
    public Circulo(double raio, String cor, int x,int y){
        this.raio = raio;
        cordenadax = x;
        cordenaday = y;
        color = cor;
        area = area(raio);
        perim = perim(raio);
        imprimir();
    }
    
    public double area(double r){
        return r*r*Math.PI;
    }
    
    public double perim(double r){
        return 2*r*Math.PI;
    }
    
    public void imprimir(){
        System.out.println("Circulo:");
        System.out.println("Area : "+area);
        System.out.println("Perimetro: "+perim);
        System.out.println("Cor: "+color);
        System.out.println("Cordenadas:");
        System.out.println("x: "+cordenadax);
        System.out.println("y: "+cordenaday);
    }
}
