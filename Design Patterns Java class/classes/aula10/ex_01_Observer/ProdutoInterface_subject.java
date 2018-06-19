/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula10.ex_01_Observer;

/**
 *
 * @author diogo
 */
public interface ProdutoInterface_subject {
    public void register(ClienteInterface_observer o);
    public void unRegister(ClienteInterface_observer o);
    public void notifyObserver();
}
