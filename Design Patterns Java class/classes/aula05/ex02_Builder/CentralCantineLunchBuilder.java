/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula05.ex02_Builder;

/**
 *
 * @author diogo
 */
public class CentralCantineLunchBuilder implements LunchBuilder {

    private Lunch lunch;

    public CentralCantineLunchBuilder() {
        this.lunch = new Lunch();
    }

    @Override
    public void buildDrink() {
        lunch.setDrink("Água");
    }

    @Override
    public void buildMainCourse() {
        lunch.setMainCourse("Grelhada mista");
    }

    @Override
    public void buildSide() {
        lunch.setSide("Queijo fresco");
    }

    @Override
    public Lunch getMeal() {
        return this.lunch;
    }

}
