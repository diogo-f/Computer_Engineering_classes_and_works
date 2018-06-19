/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_aula01_ex04;
import java.util.Scanner;
/**
 *
 * @author diogo
 */
public class POO_aula01_ex04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        
        double vm1=0;
        double vm2=0;
        double vmf;
        
        System.out.printf("1ª VM: ",vm1);
        vm1 = sc.nextDouble();
        System.out.printf("\n2ª VM: ",vm2);
        vm2 = sc.nextDouble();
        
        vmf = (vm1 + vm2)/2;
        
        System.out.printf("\nVelocidade media final: %2.2f\n",vmf);
        
        sc.close();
    }
    
}
