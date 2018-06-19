/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula10.ex_02_Null_Object;

/**
 *
 * @author diogo
 */
public class Programmer extends Employee {

    public Programmer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
