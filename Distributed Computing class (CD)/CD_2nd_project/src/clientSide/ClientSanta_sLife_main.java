/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSide;

import serverLogger.Loggerr;
import serverTripWithSanta.TripWithSanta;
import serverFactory.Factory;
import serverSantaHouse.SantaHouse;
import serverStable.Stable;
import commonInfo.InitialData;
import java.io.FileNotFoundException;

/**
 *
 * @author diogo
 */
public class ClientSanta_sLife_main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here

        //criar os objectos
        InitialData data = new InitialData();
        Loggerr log = new Loggerr();

        SantaHouse santaHouse = new SantaHouse(log, data);
        Factory factory = new Factory(log, data);
        Stable stable = new Stable(log, santaHouse, data);
        TripWithSanta tripWithSanta = new TripWithSanta(log, data);

        Santa santa = new Santa(log, santaHouse, stable, tripWithSanta, data);

        //iniciar as threads
        Thread santaThread = new Thread(santa);
        santaThread.start();

        for (int i = 0; i < data.getnElfs(); i++) {
            Thread elfThread = new Thread(new Elf(i, santaHouse, factory, log));
            elfThread.start();
        }

        for (int i = 0; i < data.getnReindeers(); i++) {
            Thread reindeerThread = new Thread(new Reindeer(i, log, stable, tripWithSanta, santaHouse));
            reindeerThread.start();
        }
    }
}
