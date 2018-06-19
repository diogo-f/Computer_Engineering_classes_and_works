/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_04;

import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class ex02 {
    static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int a,b;
        System.out.println("Introduza o valor de a: ");
        a = sc.nextInt();
        System.out.println("Introduza o valor de b: ");
        b = sc.nextInt();
        
        System.out.println("O MDC dos valores introduzidos é "+MyMath.mdc(a,b));
    }
    
}
