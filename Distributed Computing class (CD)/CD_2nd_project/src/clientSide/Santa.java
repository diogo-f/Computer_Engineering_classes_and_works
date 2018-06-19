/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSide;

import commonInfo.InitialData;
import serverLogger.Loggerr;
import serverSantaHouse.SantaHouse;
import serverStable.Stable;
import serverTripWithSanta.TripWithSanta;

/**
 *
 * @author diogo
 */
public class Santa implements Runnable {

    private final Loggerr log;
    private final SantaHouse house;
    private final Stable stable;
    private final TripWithSanta trip;
    private int nTrips;
    private boolean elvesEnd;
    private final InitialData data;

    public Santa(Loggerr log, SantaHouse house, Stable stable, TripWithSanta trip, InitialData data) {
        this.log = log;
        this.data = data;
        this.house = house;
        this.stable = stable;
        this.trip = trip;
        this.nTrips = 0;
        this.elvesEnd = false;
    }

    @Override
    public void run() {
        while (!(log.endReindeerOperation() && log.endElfsOperation())) {
            house.rest();
            if (house.openTheDoor() == 'e') {
                if (nTrips == data.getMaxTrips()) {
                    elvesEnd = true;
                }
                house.inviteIn(elvesEnd);
                house.listenToElves();
                house.sayGoodbye();
            } else {
                nTrips++;
                System.out.println("ano: " + nTrips);
                if (nTrips == data.getMaxTrips()) {
                    log.endReindeerOperation(true);
                }
                stable.harnessTheReindeers();
                trip.travelAround();
                trip.goHome();
                house.endTrip();
            }
        }
    }
}
