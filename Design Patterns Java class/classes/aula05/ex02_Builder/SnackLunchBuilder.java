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
public class SnackLunchBuilder implements LunchBuilder {

    private Lunch lunch;

    public SnackLunchBuilder() {
        this.lunch = new Lunch();
    }

    @Override
    public void buildDrink() {
        lunch.setDrink("Sumo");
    }

    @Override
    public void buildMainCourse() {
        lunch.setMainCourse("Pão com panado");
    }

    @Override
    public void buildSide() {
        lunch.setSide("Rissol");
    }

    @Override
    public Lunch getMeal() {
        return this.lunch;
    }

}
