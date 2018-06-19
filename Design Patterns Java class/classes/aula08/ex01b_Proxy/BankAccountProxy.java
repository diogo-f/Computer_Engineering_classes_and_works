/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula08.ex01b_Proxy;

import aula08.ex01b_Proxy.BankAccount;
import aula08.ex01b_Proxy.BankAccountImpl;
import static aula08.ex01b_Proxy.Company.user;

/**
 *
 * @author diogo
 */
public class BankAccountProxy implements BankAccount {

    private String bank;
    private double balance;
    private BankAccountImpl realBankAccount;

    public BankAccountProxy(String bank, double initialDeposit) {
        this.realBankAccount = new BankAccountImpl(bank, initialDeposit);
    }

    @Override
    public void deposit(double amount) {
        realBankAccount.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (user == User.OWNER) {
            return realBankAccount.withdraw(amount);
        } else {
            System.out.println("Not allowed");
            return false;
        }
    }

    @Override
    public double balance() {
        if (user == User.OWNER) {
            return realBankAccount.balance();
        } else {
            System.out.println("Not allowed");
            return 0.0;
        }
    }
}
