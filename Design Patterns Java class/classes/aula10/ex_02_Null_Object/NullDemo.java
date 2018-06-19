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
public class NullDemo {

    public static void main(String[] args) {
        Employee emp = EmployeeFactory.getCustomer("Mac");
        Employee emp2 = EmployeeFactory.getCustomer("Janela");
        Employee emp3 = EmployeeFactory.getCustomer("Linux");
        Employee emp4 = EmployeeFactory.getCustomer("Mack");

        System.out.println(emp.getName());
        System.out.println(emp2.getName());
        System.out.println(emp3.getName());
        System.out.println(emp4.getName());
    }
}
