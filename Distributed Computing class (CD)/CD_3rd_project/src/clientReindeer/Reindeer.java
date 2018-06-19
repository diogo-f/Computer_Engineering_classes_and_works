/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientReindeer;

import publicStubs.Logger_Stub;
import publicStubs.Stable_Stub;
import publicStubs.TripWithSanta_Stub;

/**
 *
 * @author diogo
 */
public class Reindeer implements Runnable {

    private final int id;
    private final Logger_Stub log;
    private final Stable_Stub stable;
    private final TripWithSanta_Stub trip;

    public Reindeer(int id, Logger_Stub log, Stable_Stub stable, TripWithSanta_Stub trip) {
        this.id = id;
        this.log = log;
        this.stable = stable;
        this.trip = trip;

    }

    @Override
    public void run() {
        while (!log.endReindeerOperation()) {
            stable.enjoyingHolidays(id);
            stable.goBackStable(id);
            stable.groupAtTheSledge(id);
            trip.followSantaDirections(id);
        }
    }
}
