package POO_aula01_ex03;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class POO_aula01_ex03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double c, f;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Temp em Cº: ");
        c = sc.nextDouble();
        
        f = (9.5 / 5.0)*c +32;
        
        System.out.println( c + " ºC " + " = " + f +"ºF");
    }
    
}
