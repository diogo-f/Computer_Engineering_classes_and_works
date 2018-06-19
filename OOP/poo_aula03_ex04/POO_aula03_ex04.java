/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_aula03_ex04;

import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class POO_aula03_ex04 {
    static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double lado1 = 0,lado2 = 0,raio = 0;
        int x = 0,y = 0;
        String cor = null;
        int m;
        do{
            m=menu();
            switch(m){
                case 1:Quadrado Q;
                       lado1 = lado();
                       x = xcord();
                       y = ycord();
                       Q = new Quadrado(lado1,cor,x,y);
                case 2:Retangulo R;
                       lado1 = lado();
                       lado2 = lado();
                       x = xcord();
                       y = ycord();
                       R = new Retangulo(lado1,lado2,cor,x,y);
                case 3:Circulo C;
                       raio = raio();
                       x = xcord();
                       y = ycord();
                       C = new Circulo(lado1,cor,x,y);
            }
        }while(m!=4);
         
    }
    
    public static int menu(){
        int n = 0;
        System.out.println("Menu:");
        System.out.println("1 - Quadrado.");
        System.out.println("2 - Retangulo.");
        System.out.println("3 - Circulo.");
        System.out.println("4 - Sair");
        n = sc.nextInt();
        return n;
    }
    public static double lado(){
        System.out.println("Comprimento do lado: ");
        return sc.nextDouble();
    }
    public static double raio(){
        System.out.println("Comprimento do raio: ");
        return sc.nextDouble();
    }
    public static int xcord(){
        System.out.println("Ponto x: ");
        return sc.nextInt();
    }
    public static int ycord(){
        System.out.println("Ponto y: ");
        return sc.nextInt();
    }
}
