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
public class Milk implements Commodity {

    public Milk() {
        System.out.println("Escolheu leite, a encher!");
    }

    @Override
    public Temperature getTemperature() {
        return Temperature.WARM;
    }

    @Override
    public State getState() {
        return State.LIQUID;
    }

    @Override
    public String toString() {
        return "Milk" + " Temperature: " + getTemperature() + " State:" + getState();
    }
}
