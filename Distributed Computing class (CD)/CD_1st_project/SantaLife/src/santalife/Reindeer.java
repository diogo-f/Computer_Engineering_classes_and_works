/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santalife;

/**
 *
 * @author diogo
 */
public class Reindeer implements Runnable {

    private final int id;
    private final Loggerr log;
    private final Stable stable;
    private final TripWithSanta trip;
    private final SantaHouse house;

    public Reindeer(int id, Loggerr log, Stable stable, TripWithSanta trip, SantaHouse house) {
        this.id = id;
        this.log = log;
        this.stable = stable;
        this.trip = trip;
        this.house = house;

    }

    @Override
    public void run() {
        while (!log.endOperSantaClaus()) {
            stable.enjoyingHolidays(id);
            if (stable.goBackStable(id)) {
                house.knockKnock();
            }
            stable.groupAtTheSledge(id);
            trip.followSantaDirections(id);
        }
    }
}
