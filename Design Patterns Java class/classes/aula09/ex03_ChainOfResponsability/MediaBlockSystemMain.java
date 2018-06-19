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
public class MediaBlockSystemMain {

    public static void main(String[] args) {
        MediaBlockSystemChain chainCD = new CDreader();
        MediaBlockSystemChain chainCassette = new CassetteReader();
        MediaBlockSystemChain chainVinilDisk = new VinylDiskReader();
        MediaBlockSystemChain chainRadio = new RadioReceiver();
        
        chainCD.setNextHandler(chainCassette);
        chainCassette.setNextHandler(chainVinilDisk);
        chainVinilDisk.setNextHandler(chainRadio);
        
        //Options:
        //-CD
        //-Cassette
        //-VinylDisk
        //-Radio
        
        Media playMedia = new Media("Radio");
        chainCD.playMedia(playMedia);
        System.out.println("");
        playMedia = new Media("VinylDisk");
        chainCD.playMedia(playMedia);
        System.out.println("");
        playMedia = new Media("DVD");
        chainCD.playMedia(playMedia);
    }
}
