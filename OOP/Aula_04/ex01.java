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
public class ex01 {
    static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String matricula;
        
        System.out.println("Introduza a matricula: ");
        System.out.println("Pode ser do VariedadeCarne: LL-NN-NN, NN-NN-LL ou NN-LL-NN");
        matricula = sc.nextLine();
        matricula = matriculaToUpper(matricula);
        
        while(verificaMatricula(matricula) == false){
            System.out.println("Matricula errada, reintruduza a matricula seguindo o exemplo anterior.");
            matricula = sc.nextLine();
            matriculaToUpper(matricula);
            
        }
        
        System.out.println("Matricula: "+matricula+" está correcta!");
        
        
        
    }
    
    public static boolean verificaMatricula(String mat){
        
            if(mat.length() != 8 && mat.charAt(2)!= '-' && mat.charAt(5)!= '-')
                return false;
        
        
        if(Character.isLetter(mat.charAt(0)) &&
           Character.isLetter(mat.charAt(1)) &&
           Character.isDigit(mat.charAt(3))  &&
           Character.isDigit(mat.charAt(4))  &&
           Character.isDigit(mat.charAt(6))  &&
           Character.isDigit(mat.charAt(7))
                ) return true;
        
        else if(Character.isDigit(mat.charAt(0)) &&
           Character.isDigit(mat.charAt(1))      &&
           Character.isLetter(mat.charAt(3))     &&
           Character.isLetter(mat.charAt(4))     &&
           Character.isDigit(mat.charAt(6))      &&
           Character.isDigit(mat.charAt(7))
                ) return true;
        
        else if(Character.isDigit(mat.charAt(0)) &&
           Character.isDigit(mat.charAt(1))      &&
           Character.isDigit(mat.charAt(3))      &&
           Character.isDigit(mat.charAt(4))      &&
           Character.isLetter(mat.charAt(6))     &&
           Character.isLetter(mat.charAt(7))
                ) return true;
        else
            return false;
        
    }
    
    public static String matriculaToUpper(String mat){
        mat = mat.toUpperCase();
        return mat;
    }
}
