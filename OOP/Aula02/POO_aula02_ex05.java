package Aula02;


import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class POO_aula02_ex05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = -1;
        
        while(n<0){
            System.out.printf("\nIntroduza um numero positivo: ");
            n = sc.nextInt(); 
        }
        
        boolean prime = isPrime(n);
        
        if (prime){
                System.out.println("The integer, " + n + ", is a prime");
            }
        else{
            System.out.println("The integer, " + n + ", is not a prime");
        }
    }
    
    public static boolean isPrime(int p) {
            for(int i=2;i<p;i++)
            {
                if(p%i==0)
                    return false;
            }
                return true;
        }
}