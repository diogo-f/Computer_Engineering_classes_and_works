/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSide;

import commonStubs.*;                   //faço .* ou import só dos que precisa? como é melhor
import serverLogger.Loggerr;

/**
 *
 * @author diogo
 */
public class Reindeer implements Runnable {

    private final int id;
    private final Loggerr_Stub log;
    //private final Stable stable;
    private final Stable_Stub stableStub;
    //private final TripWithSanta trip;
    private final TripWithSanta_Stub tripStub;

    public Reindeer(int id, Loggerr log, Stable_Stub stableStub, TripWithSanta_Stub tripStub) {
        this.id = id;
        this.log = log;
        //this.stable = stable;
        this.stableStub = stableStub;
        //this.trip = trip;
        this.tripStub = tripStub;
    }

    @Override
    public void run() {
        while (!log.endReindeerOperation()) {
            stableStub.enjoyingHolidays(id);
            stableStub.goBackStable(id);
            stableStub.groupAtTheSledge(id);
            tripStub.followSantaDirections(id);
        }
    }
}
