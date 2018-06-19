/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_aula03_ex02;

import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class POO_aula03_ex02 {
    static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String frase;
        System.out.println("Qual a frase/paragrafo?");
        frase = sc.nextLine();
        
        String[] parts = frase.split("\\.|\\?|!|\\.{3}");
        
        for (String part : parts) {
            System.out.println(part);
        }
    }
    
}
