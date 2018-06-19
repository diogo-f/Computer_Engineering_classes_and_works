/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula05.ex01_factoryMethod;

/**
 *
 * @author diogo
 */
public class BeverageFactory {

    public static Commodity createBeverage(Temperature t) {
        switch (t) {
            case WARM:
                return new Milk();
            case COLD:
                return new FruitJuice("laranja");
            default:
                System.err.println("Bebida desconhecida!");
                return null;
        }
    }
}
