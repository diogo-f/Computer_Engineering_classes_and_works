/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Km2Miles;

/**
 *
 * @author diogo
 */
import java.util.Scanner;

public class Km2Miles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        double km, miles;
        System.out.print("Insira distância em km: ");
        km = sc.nextDouble();
        miles = km / 1.609;
        System.out.println("A distância em milhas é " + miles);
        sc.close();
    }
    
}
