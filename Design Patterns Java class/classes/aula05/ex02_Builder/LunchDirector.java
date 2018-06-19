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
public class LunchDirector {

    private LunchBuilder lunchBuilder;

    public LunchDirector(LunchBuilder lunchBuilder) {
        this.lunchBuilder = lunchBuilder;
    }

    public void constructMeal() {
        lunchBuilder.buildDrink();
        lunchBuilder.buildMainCourse();
        lunchBuilder.buildSide();
    }

    public Lunch getMeal() {
        return lunchBuilder.getMeal();
    }

}
