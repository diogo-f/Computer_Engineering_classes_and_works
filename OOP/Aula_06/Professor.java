/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_06;

/**
 *
 * @author diogo
 */
public class Professor extends Pessoa {

    private String area;

    public Professor(String n, int cc, Data da, String a) {
        super(n, cc, da);
        this.area = a;
    }

    @Override
    public String toString() {
        return "Professor " + super.getNome() + "\nArea de investigação: " + area;
    }

    public int getCC(){
        return super.getCc();
    }
}
