/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientReindeer;

import publicInfo.InitialData;
import java.io.FileNotFoundException;
import publicStubs.Logger_Stub;
import publicStubs.Stable_Stub;
import publicStubs.TripWithSanta_Stub;

/**
 *
 * @author diogo
 */
public class ClientMainReindeer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Logger_Stub log = new Logger_Stub(InitialData.serverHostNameServerLogger, InitialData.loggerPort);

        Reindeer[] reindeer = new Reindeer[InitialData.nReindeers];     // array de threads renas
        Stable_Stub stable = new Stable_Stub(InitialData.serverHostNameServerStable, InitialData.stablePort);
        TripWithSanta_Stub trip = new TripWithSanta_Stub(InitialData.serverHostNameServerTripSanta, InitialData.tripPort);

        for (int i = 0; i < reindeer.length; i++) {
            Thread reindeerThread = new Thread(new Reindeer(i, log, stable, trip));
            reindeerThread.setName("Reindeer_" + i);
            reindeerThread.start();
        }
    }
}
