/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex03_ChainOfResponsability;

/**
 *
 * @author diogo
 */
public interface MediaBlockSystemChain {
    public void setNextHandler(MediaBlockSystemChain nextChain);
    public void playMedia(Media request);
}
