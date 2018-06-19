/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_05;

/**
 *
 * @author diogo
 */
public class Ex2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Data d = new Data(5, 10, 1988);
         Pessoa p = new Pessoa("Ana Santos", 98012244, d);
        
         System.out.println(p);*/

        Aluno al = new Aluno("Andreia Melo", 9855678,new Data(18, 7, 1990), new Data(1, 9, 2014));
        
        Bolseiro bls = new Bolseiro("Igor Santos", 8976543, new Data(11, 5, 1985));
        bls.setBolsa(745);
        System.out.println("Aluno:" + al.getName());
        System.out.println(al);
        System.out.println("Bolseiro:" + bls.getName() + ", NMec: " + bls.getNMec() + ", Bolsa:" + bls.getBolsa());
        System.out.println(bls);
    }
}
