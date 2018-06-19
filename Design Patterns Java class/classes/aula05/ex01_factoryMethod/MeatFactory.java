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
public class MeatFactory {

    public static Commodity createMeat(Temperature t) {
        switch (t) {
            case WARM:
                return new Pork();
            case COLD:
                return new Tuna();
            default:
                System.err.println("Comida desconhecida!");
                return null;
        }
    }
}
