/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_aula01_ex05;
import java.util.Scanner;
/**
 *
 * @author diogo
 */
public class POO_aula01_ex05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int hh,mm,ss,temp;
        
        System.out.printf("Qual o tempo em Segundos? ");
        temp = sc.nextInt();
        
        hh = temp/3600;
        mm = (temp%3600)/60;
        ss = (temp%3600)%60;
        
        System.out.printf("%d Seg = %dh:%dm:%ds\n",temp,hh,mm,ss);
        sc.close();
    }
    
}
