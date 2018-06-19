/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex01_Decorator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author diogo
 */
public class TodosFazemMain {

    public static void main(String[] args) throws InterruptedException {
        Employee emp1 = new Employee("emp1");
        Employee emp2 = new Employee("emp2");
        Employee emp3 = new Employee("emp3");
        Employee emp4 = new Employee("emp4");

        emp1.works();
        emp2.works();
        emp3.works();
        emp4.works();
        
        System.out.println("");

        TeamLeader tl1 = new TeamLeader(emp3);
        tl1.works();

        System.out.println("");

        Manager man1 = new Manager(tl1);
        man1.works();

        TeamMember tem1 = new TeamMember(emp1);
        TeamMember tem2 = new TeamMember(emp2);
        TeamMember tem3 = new TeamMember(emp4);

        System.out.println("\nwaiting 3sec for diferent dates...");
        Thread.sleep(3000);

        tem1.endDate(new Date());
        tem2.endDate(new Date());
        tem3.endDate(new Date());

        System.out.println(man1.toString());
        System.out.println(tl1.toString());
        System.out.println(tem1.toString());
        System.out.println(tem2.toString());
        System.out.println(tem3.toString());

    }
}
