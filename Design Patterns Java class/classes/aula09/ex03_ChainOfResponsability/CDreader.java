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
public class CDreader implements MediaBlockSystemChain {

    private MediaBlockSystemChain nextInChain;

    @Override
    public void setNextHandler(MediaBlockSystemChain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void playMedia(Media request) {
        if (request.getMediaType().equals("CD")) {
            System.out.println("Playing CD");
        } else {
            nextInChain.playMedia(request);
        }
    }

}
