package Aula02;

import java.util.*;
/**
 *
 * @author diogo
 */
public class POO_aula02_ex06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int firstNum;
        int temp = 0;
        int media = 0;
        
        ArrayList<Integer> numeros = new ArrayList<>();
        
        //primeiro numero introduzido
        System.out.printf("\nnumero real: ");
            int temp2 = sc.nextInt();
            numeros.add(temp2);
            firstNum = temp2;
        
        
        while(firstNum != temp){
            System.out.printf("\nnumero real: ");
            temp = sc.nextInt();
            numeros.add(temp);
        }
        
        System.out.println("");
        System.out.println("Introduziu: " + numeros.toString());
        System.out.println(numeros.size() + " elementos.");
        
        for(int i = numeros.size()-1; i >= 0; i--) {
            for(int j = 0; j < i; j++) {
                if(numeros.get(j) > numeros.get(j + 1)) {
                    int temp3 = numeros.get(j);
                    numeros.set(j, numeros.get(j + 1));
                    numeros.set(j + 1, temp3);
                }
            }
        }
        
        System.out.println("");
        System.out.println("O valor maximo introduzido é: " + numeros.get(numeros.size()-1));
        System.out.println("O valor minimo introduzido é: " + numeros.get(0));
        
        for(int c=0;c<=numeros.size()-1;c++){
            media += numeros.get(c);
        }
        
        System.out.println("A media dos valores introduzidos é: " + media/numeros.size());
        System.out.println("");
        
    }
}
