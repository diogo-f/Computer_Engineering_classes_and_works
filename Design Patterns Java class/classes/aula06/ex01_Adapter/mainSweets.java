/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex01_Adapter;

/**
 *
 * @author diogo
 */
public class mainSweets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database db = new Database();

        Employee emp1 = new Employee("alguem1", 1, 1000);
        Employee emp2 = new Employee("alguem2", 2, 2000);
        Employee emp3 = new Employee("alguem3", 3, 3000);

        System.out.println("emp1: " + (db.addEmployee(emp1) ? "sim" : "nao"));
        System.out.println("emp2: " + (db.addEmployee(emp2) ? "sim" : "nao"));
        System.out.println("emp3: " + (db.addEmployee(emp3) ? "sim" : "nao"));

        Employee[] emps = new Employee[db.getAllEmployees().length];
        emps = db.getAllEmployees();

        for (Employee empsT : emps) {
            System.out.println(empsT.getName() + " " + empsT.getSalary());
        }
    }

}
