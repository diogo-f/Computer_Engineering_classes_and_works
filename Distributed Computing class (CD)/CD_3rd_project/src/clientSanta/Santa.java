/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSanta;

import publicInfo.InitialData;
import publicStubs.Factory_Stub;
import publicStubs.Logger_Stub;
import publicStubs.SantaHouse_Stub;
import publicStubs.Stable_Stub;
import publicStubs.TripWithSanta_Stub;

/**
 *
 * @author diogo
 */
public class Santa implements Runnable {

    private final Logger_Stub log;
    private final SantaHouse_Stub house;
    private final Stable_Stub stable;
    private final TripWithSanta_Stub trip;
    private final Factory_Stub factory;
    private int nTrips;
    private boolean elvesEnd;

    public Santa(Logger_Stub log, SantaHouse_Stub house, Stable_Stub stable, TripWithSanta_Stub trip, Factory_Stub factory) {
        this.log = log;
        this.house = house;
        this.stable = stable;
        this.trip = trip;
        this.factory = factory;
        this.nTrips = 0;
        this.elvesEnd = false;
    }

    @Override
    public void run() {
        while (!(log.endReindeerOperation() && log.endElfsOperation())) {
            house.rest();
            if ("r".equals(house.openTheDoor())) {
                nTrips++;
                System.out.println("ano: " + nTrips);
                if (nTrips == InitialData.maxTrips) {
                    log.endReindeerOperation(true);
                }
                stable.harnessTheReindeers();
                trip.travelAround();
                trip.goHome();
                house.endTrip();
            } else {
                if (nTrips == InitialData.maxTrips) {
                    elvesEnd = true;
                }
                house.inviteIn(elvesEnd);
                house.listenToElves();
                house.sayGoodbye();
            }
        }
        trip.shutdown();
        stable.shutdown();
        factory.shutdown();
        house.shutdown();
        log.shutdown();
    }
}
