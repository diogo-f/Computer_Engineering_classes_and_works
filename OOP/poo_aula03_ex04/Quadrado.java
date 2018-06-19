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
public class Quadrado {
    private final String color;
    private final double lado1;
    private int cordenadax,cordenaday;
    private double area = 0, perim = 0;
    
    public Quadrado(double lado, String cor, int x,int y){
        lado1 = lado;
        cordenadax = x;
        cordenaday = y;
        color = cor;
        area = area(lado);
        perim = perim(lado);
        imprimir();
    }
    
    public double area(double l){
        return l*l;
    }
    
    public double perim(double l){
        return 4*l;
    }
    
    public void imprimir(){
        System.out.println("Quadrado:");
        System.out.println("Area : "+area);
        System.out.println("Perimetro: "+perim);
        System.out.println("Cor: "+color);
        System.out.println("Cordenadas:");
        System.out.println("x: "+cordenadax);
        System.out.println("y: "+cordenaday);
    }
}
