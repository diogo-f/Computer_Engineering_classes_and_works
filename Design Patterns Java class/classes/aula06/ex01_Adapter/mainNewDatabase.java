/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex01_Adapter;

import java.util.List;

/**
 *
 * @author diogo
 */
public class mainNewDatabase {

    public static void main(String args[]) {

        NewDatabaseAdapter databaseAdapter = new NewDatabaseAdapter();

        getBD(databaseAdapter);

        System.out.println("Empregado 1 existe? " + (databaseAdapter.checkEmployee(1) ? "sim" : "nao"));
        System.out.println("Empregado 11 existe? " + (databaseAdapter.checkEmployee(11) ? "sim" : "nao"));
        System.out.println("Empregado 12 existe? " + (databaseAdapter.checkEmployee(12) ? "sim" : "nao"));
        System.out.println("Empregado 100 existe? " + (databaseAdapter.checkEmployee(100) ? "sim" : "nao"));

        Employee emp1 = new Employee("empNovo1", 100, 750);
        Employee emp2 = new Employee("empNovo2", 101, 1000);
        Employee emp3 = new Employee("empNovo3", 102, 500);

        databaseAdapter.addEmployee(emp1);
        databaseAdapter.addEmployee(emp2);
        databaseAdapter.addEmployee(emp3);

        System.out.println("");
        getBD(databaseAdapter);
        System.out.println("");

        databaseAdapter.deleteEmployee(2);
        databaseAdapter.deleteEmployee(10);
        databaseAdapter.deleteEmployee(12);

        System.out.println("");
        getBD(databaseAdapter);
        System.out.println("");

        System.out.println("Empregado 1 existe? " + (databaseAdapter.checkEmployee(1) ? "sim" : "nao"));
        System.out.println("Empregado 11 existe? " + (databaseAdapter.checkEmployee(11) ? "sim" : "nao"));
        System.out.println("Empregado 12 existe? " + (databaseAdapter.checkEmployee(12) ? "sim" : "nao"));
        System.out.println("Empregado 100 existe? " + (databaseAdapter.checkEmployee(100) ? "sim" : "nao"));
    }

    public static void getBD(NewDatabaseAdapter dbAdapter) {
        Employee[] arrayTemp = new Employee[30];

        System.out.println("BD atual");
        arrayTemp = dbAdapter.getAllEmployees();
        for (Employee emp : arrayTemp) {
            System.out.println(emp.getName() + " " + emp.getEmpNum() + " " + emp.getSalary());
        }
    }
}
