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
public interface BankAccount {

    void deposit(double amount);

    boolean withdraw(double amount);

    double balance();
}
