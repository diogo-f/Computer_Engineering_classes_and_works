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
public class CrastoLunchlBuilder implements LunchBuilder {

    private Lunch lunch;

    public CrastoLunchlBuilder() {
        this.lunch = new Lunch();
    }

    @Override
    public void buildDrink() {
        lunch.setDrink("Vinho tinto");
    }

    @Override
    public void buildMainCourse() {
        lunch.setMainCourse("Bacalhau à lagareiro");
    }

    @Override
    public void buildSide() {
        lunch.setSide("Broa");
    }

    @Override
    public Lunch getMeal() {
        return this.lunch;
    }

}
