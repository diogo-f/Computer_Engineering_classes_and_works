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
public class Santa implements Runnable {

    private final Loggerr log;
    private final SantaHouse house;
    private final Stable stable;
    private final TripWithSanta trip;

    public Santa(Loggerr log, SantaHouse house, Stable stable, TripWithSanta trip) {
        this.log = log;
        this.house = house;
        this.stable = stable;
        this.trip = trip;
    }

    @Override
    public void run() {
        while (!log.endOperSantaClaus()) {
            house.rest();
            if (house.openTheDoor() == 'e') {
                for (int i = 0; i < 3; i++) {
                    house.inviteIn();
                    house.sayGoodbye();
                }
            } else {
                stable.harnessTheReindeers();
                trip.travelAround();
                trip.goHome();
            }
        }
    }
}
