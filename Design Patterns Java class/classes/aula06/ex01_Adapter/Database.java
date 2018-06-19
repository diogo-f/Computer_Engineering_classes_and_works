/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex01_Adapter;

import java.util.Vector;

/**
 *
 * @author diogo
 */
class Database {
// Data elements

    private Vector<Employee> employees; // Stores the employees

    public Database() {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public void deleteEmployee(long emp_num) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmpNum() == emp_num) {
                System.out.println("A remover " + employees.get(i).getName());
                employees.remove(i);
            }
        }
    }

    public Employee[] getAllEmployees() {
        Employee[] empsTemp = new Employee[employees.size()];
        return employees.toArray(empsTemp);
    }

    public void addThingsToDB() {
        System.out.println("a carregar DB Database");
        for (int i = 0; i < 5; i++) {
            addEmployee(new Employee("pessoa" + i, i, i + 1000));
        }
    }
}
