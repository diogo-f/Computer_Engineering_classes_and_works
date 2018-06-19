/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex01_Decorator;

/**
 *
 * @author diogo
 */
public abstract class EmployeeDecorator implements EmployeeInterface {

    protected EmployeeInterface empInt;

    public EmployeeDecorator(EmployeeInterface empInt) {
        this.empInt = empInt;
    }

    @Override
    public String toString() {
        return empInt.toString();
    }

}
