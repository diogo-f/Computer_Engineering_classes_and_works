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
public interface LunchBuilder {

    public void buildDrink();

    public void buildMainCourse();

    public void buildSide();

    public Lunch getMeal();
}
