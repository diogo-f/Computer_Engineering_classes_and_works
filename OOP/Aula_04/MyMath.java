/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_04;

/**
 *
 * @author diogo
 */
public class MyMath {
    
    public static int mdc(int a, int b){
        if(b==0)
            return a;
        else
            return mdc(b,a%b);
    }
}
