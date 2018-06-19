/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula08.ex02_Facade;

import aula08.ex02_Facade.BankAccount;
import aula08.ex02_Facade.BankAccountImpl;

/**
 *
 * @author diogo
 */
public class BankAccountProxy implements BankAccount {

    private String bank;
    private double balance;
    private BankAccount realBankAccount;

    public BankAccountProxy(String bank, double initialDeposit) {
        this.realBankAccount = new BankAccountImpl(bank, initialDeposit);
    }

    @Override
    public void deposit(double amount) {
        realBankAccount.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        System.out.println("Not allowed");
        return false;
    }

    @Override
    public double balance() {
        System.out.println("Not allowed");
        return 0.0;
    }


}
