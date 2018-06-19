/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santalife;

import java.io.FileNotFoundException;

/**
 *
 * @author diogo
 */
public class Santa_sHouseLife {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here

        //criar os objectos
        Loggerr log = new Loggerr();

        SantaHouse santaHouse = new SantaHouse(log);

        Stable stable = new Stable(log);

        TripWithSanta tripWithSanta = new TripWithSanta(log);

        Santa santa = new Santa(log, santaHouse, stable, tripWithSanta);

        Factory factory = new Factory(log);
//////////iniciar as threads

        Thread santaThread = new Thread(santa);
        santaThread.start();

        for (int i = 0; i < 6; i++) {
            Thread elfThread = new Thread(new Elf(i, santaHouse, factory, log));
            elfThread.start();
        }

        for (int i = 0; i < 6; i++) {
            Thread reindeerThread = new Thread(new Reindeer(i, log, stable, tripWithSanta, santaHouse));
            reindeerThread.start();
        }
    }
}
