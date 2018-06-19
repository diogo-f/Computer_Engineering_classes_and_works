/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula08.ex01b_Proxy;

/**
 *
 * @author diogo
 */
public class Employee {

    private double salary;
    private Person pessoa;

    public Employee(Person n, double s) {
        pessoa = n;
        salary = s;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return pessoa.getName();

    }

    public void receiveSalary() {
        pessoa.getBankAccount().deposit(salary);
        System.out.println(pessoa.getName() + " - " + "+" + salary);
    }

    public BankAccount getBankAccount() {
        return pessoa.getBankAccount();
    }
}
