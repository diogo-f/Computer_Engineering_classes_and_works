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
public class Tuna implements Commodity {

    public Tuna() {
        System.out.println("A preparar o atum!");
    }

    @Override
    public Temperature getTemperature() {
        return Temperature.COLD;
    }

    @Override
    public State getState() {
        return State.SOLID;
    }

    @Override
    public String toString() {
        return "Tuna" + " Temperature: " + getTemperature() + " State: " + getState();
    }
}
