/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_aula01_ex06;

import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class POO_aula01_ex06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        double d, p1x, p1y, p2x, p2y;

        System.out.printf("Coordenadas do P1:\nx: ");
        p1x = sc.nextDouble();
        System.out.printf("y: ");
        p1y = sc.nextDouble();
        System.out.printf("Coordenadas do P2:\nx: ");
        p2x = sc.nextDouble();
        System.out.printf("y: ");
        p2y = sc.nextDouble();

        d = Math.sqrt(Math.pow(p2x - p1x, 2) + Math.pow(p2y - p1y, 2));

        System.out.println("Distancia entre os pontos: " + d);
        sc.close();
    }
}
