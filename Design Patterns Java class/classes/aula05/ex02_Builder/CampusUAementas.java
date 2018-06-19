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
public class CampusUAementas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LunchBuilder lunch = new CrastoLunchlBuilder();
        LunchDirector mealDirector = new LunchDirector(lunch);
        mealDirector.constructMeal();
        Lunch meal = mealDirector.getMeal();
        System.out.println("Ana's meal is: " + meal);
        mealDirector = new LunchDirector(new SnackLunchBuilder());
        mealDirector.constructMeal();
        meal = mealDirector.getMeal();
        System.out.println("Rui's meal is: " + meal);
        mealDirector = new LunchDirector(new CentralCantineLunchBuilder());
        mealDirector.constructMeal();
        meal = mealDirector.getMeal();
        System.out.println("My meal is: " + meal);
    }
}
