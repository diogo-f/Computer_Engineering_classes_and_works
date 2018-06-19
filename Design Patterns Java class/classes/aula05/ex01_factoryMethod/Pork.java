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
public class Pork implements Commodity {

    public Pork() {
        System.out.println("A preparar o porco!");
    }

    @Override
    public Temperature getTemperature() {
        return Temperature.WARM;
    }

    @Override
    public State getState() {
        return State.SOLID;
    }

    @Override
    public String toString() {
        return "Pork" + " Temperature: " + getTemperature() + " State:" + getState();
    }
}
