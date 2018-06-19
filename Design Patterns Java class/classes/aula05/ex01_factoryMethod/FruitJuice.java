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
public class FruitJuice implements Commodity {

    private String fruta;

    public FruitJuice(String fruta) {
        this.fruta = fruta;
        System.out.println("Escolheu sumo de " + this.fruta);
    }

    @Override
    public Temperature getTemperature() {
        return Temperature.COLD;
    }

    @Override
    public State getState() {
        return State.LIQUID;
    }

    @Override
    public String toString() {
        return "FruitJuice de " + fruta + "Temperature: " + getTemperature() + " State: " + getState();
    }

}
