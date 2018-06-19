/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula08.ex02_Facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author diogo
 */
public class Company {

    public static User user;
    private List<Employee> emps = new ArrayList<>();
    private SocialSecurity social = new SocialSecurity();
    private Insurance insurance = new Insurance();
    private Parking park = new Parking();
//    AdmitEmpFacade facade = null;
    
    public void admitEmployee(Person pessoa, double salary) {
        Employee e = new Employee(pessoa, salary);
        social.regist(pessoa);
        insurance.regist(pessoa);
        if (e.getSalary() > salarioMedio()) {
            park.allow(pessoa);
        }
        emps.add(e);
    }

    private double salarioMedio() {
        double temp = 0;
        for (Employee e : emps) {
            temp += e.getSalary();
        }
        return temp / emps.size();
    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            BankAccount ba = e.getBankAccount();
            ba.deposit(e.getSalary());
        }
    }

    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}
