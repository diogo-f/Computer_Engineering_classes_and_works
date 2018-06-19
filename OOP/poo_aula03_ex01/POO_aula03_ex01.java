/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_aula03_ex01;

import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class POO_aula03_ex01 {
    static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String frase1, frase2;
        System.out.println("Qual a primeira frase: ");
        frase1 = sc.nextLine();
        System.out.println("Qual a segunda frase: ");
        frase2 = sc.nextLine();
        
        //numero de caracteres
        totalCaracteres(frase1,frase2);
        //ultimo caracter da 1ª frase
        System.out.println(frase1.charAt(frase1.length()-1));
        //as frases são iguais??
        if(compararFrases(frase1,frase2))
            System.out.println("As frases são iguais.");
        else
            System.out.println("As frases são diferentes.");
        //verificar o "." no final da segunda frase
        if(frase2.charAt(frase2.length()-1) == '.')
            System.out.println("Tem . no fim.");
        else
            System.out.println("Não tem . no fim.");
        //meter tudo em minusculas
        String frase3 = frase2.toLowerCase();
        System.out.println("Em minusculas: "+frase3);
        //verificar as maiusculas
        if(soMinusculas(frase2))
            System.out.println("Só tem minusculas");
        else
            System.out.println("Tem maiusculas na frase");
        //retirar os espaços a mais
        String semespacos = frase2.trim().replaceAll(" +", " ");
        System.out.println("Frase sem espaços a mais: "+ semespacos);
    }
    
    public static void totalCaracteres(String first, String second){
        
        System.out.println("1ª frase tem "+ first.length() + " caracteres.");
        System.out.println("2ª frase tem "+ second.length() + " caracteres.");
    }
    
    public static boolean compararFrases(String first, String second){
        return first.equals(second);
    }
    
    public static boolean soMinusculas(String second){
        String temp = second.toLowerCase();
        if(temp.equals(second))
            return true;
        else
            return false;
    }
    
}
