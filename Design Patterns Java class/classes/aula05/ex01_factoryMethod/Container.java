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
public abstract class Container {

    protected Commodity commodity;

    public boolean placeCommodity(Commodity c) {
        this.commodity = c;
        return true;
    }

    public Commodity getCommodity() {
        return commodity;
    }
    
    
}
