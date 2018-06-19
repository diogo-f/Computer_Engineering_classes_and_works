/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex01_Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
public class NewDatabaseAdapter extends Database {

    private Registos regData = new Registos();

    public NewDatabaseAdapter() {
        //adiciona informações em run-time nas bases de dados antigas!!
        super.addThingsToDB();
        regData.addThingsToDB();
    }

    @Override
    public Employee[] getAllEmployees() {
        List<Employee> empsList = new ArrayList<>();
        //Database
        for (Employee emp : super.getAllEmployees()) {
            empsList.add(emp);
        }
        //Registos
        for (Empregado emp : regData.listaDeEmpregados()) {
            empsList.add(new Employee(emp.nome(), emp.codigo(), emp.salario()));
        }

        Employee[] arrayTemp = new Employee[empsList.size()];
        return empsList.toArray(arrayTemp);
    }

    @Override
    public void deleteEmployee(long emp_num) {
        boolean check = false;
        Employee[] arrayTemp = getAllEmployees();

        for (Employee emp : arrayTemp) {
            if (emp.getEmpNum() == emp_num) {
                check = true;
            }
        }

        if (check) {
            super.deleteEmployee(emp_num);
            regData.remove((int) emp_num);
        } else {
            System.out.println("Empregado " + emp_num + " não encontrado");
        }
    }

    public boolean checkEmployee(int num) {
        Employee[] arrayTemp = getAllEmployees();
        for (Employee emp : arrayTemp) {
            if (emp.getEmpNum() == num) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return super.addEmployee(employee);
    }

}
