/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSanta;

import publicInfo.InitialData;
import java.io.FileNotFoundException;
import publicStubs.Factory_Stub;
import publicStubs.Logger_Stub;
import publicStubs.SantaHouse_Stub;
import publicStubs.Stable_Stub;
import publicStubs.TripWithSanta_Stub;

/**
 *
 * @author diogo
 */
public class ClientMainSanta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Logger_Stub log = new Logger_Stub(InitialData.serverHostNameServerLogger, InitialData.loggerPort);

        SantaHouse_Stub house = new SantaHouse_Stub(InitialData.serverHostNameServerSantaHouse, InitialData.housePort);
        Stable_Stub stable = new Stable_Stub(InitialData.serverHostNameServerStable, InitialData.stablePort);
        TripWithSanta_Stub trip = new TripWithSanta_Stub(InitialData.serverHostNameServerTripSanta, InitialData.tripPort);
        Factory_Stub factory = new Factory_Stub(InitialData.serverHostNameServerFactory, InitialData.factoryPort);

        Santa santa = new Santa(log, house, stable, trip, factory);

        Thread santaThread = new Thread(santa);
        santaThread.setName("Santa");
        santaThread.start();
    }
}
