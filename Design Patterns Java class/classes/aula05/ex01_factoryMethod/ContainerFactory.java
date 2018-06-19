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
public class ContainerFactory {

    public static Container createContainerFor(Commodity commodity) {
        if (commodity.getState().equals(State.LIQUID) && commodity.getTemperature().equals(Temperature.COLD)) {
            return new PlasticBottle();
        } else if (commodity.getState().equals(State.LIQUID) && commodity.getTemperature().equals(Temperature.WARM)) {
            return new TermicBottle();
        } else if (commodity.getState().equals(State.SOLID) && commodity.getTemperature().equals(Temperature.COLD)) {
            return new PlasticBag();
        } else if (commodity.getState().equals(State.SOLID) && commodity.getTemperature().equals(Temperature.WARM)) {
            return new Tupperware();
        } else {
            System.out.println("Container não criado!");
            return null;
        }
    }
    
}
