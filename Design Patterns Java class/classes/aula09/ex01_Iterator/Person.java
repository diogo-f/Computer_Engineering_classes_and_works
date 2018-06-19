/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex01_Iterator;

/**
 *
 * @author diogo
 */
public class Person {

    private String name;
    private int age;

    public Person(String n, int age) {
        name = n;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Pessoa: " + name + ", idade: " + age;
    }

}
