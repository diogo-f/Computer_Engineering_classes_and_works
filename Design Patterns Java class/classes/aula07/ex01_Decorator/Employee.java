/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex01_Decorator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diogo
 */
public class Employee implements EmployeeInterface {

    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

    public Employee(String nome) {
        this.nome = nome;
        dataInicio = new Date();
        this.dataFim = null;
    }

    @Override
    public void works() {
        System.out.println("Nome: " + nome + "\nTrabalho");
    }

//    @Override
//    public void startDate(Date d) {
//        this.dataInicio = d;
//    }
    @Override
    public void endDate(Date d) {
        this.dataFim = d;
    }

    @Override
    public String toString() {
        if (dataFim == null) {
            return "Employee - " + "Nome: " + nome + ", dataInicio: " + dateFormat.format(dataInicio) + ", dataFim: sem data";
        } else {
            return "Employee - " + "Nome: " + nome + ", dataInicio: " + dateFormat.format(dataInicio) + ", dataFim: " + dateFormat.format(dataFim);
        }
    }
}
