/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santalife;

import java.util.concurrent.Semaphore;

/**
 *
 * @author diogo
 */
public class TripWithSanta {

    private final Semaphore[] reindeers;
    private final Semaphore mutex;
    private final Loggerr log;

    public TripWithSanta(Loggerr log) {
        reindeers = new Semaphore[6];
        mutex = new Semaphore(1);
        this.log = log;

        for (int i = 0; i < reindeers.length; i++) {
            reindeers[i] = new Semaphore(0);
        }
    }

//////////////////REINDEERS//////////////
    public void followSantaDirections(int id) {
        try {
            mutex.acquire();
            log.writeReindeerState(id, States.SLEG);
            mutex.release();
            reindeers[id].acquire();

        } catch (InterruptedException ex) {
        }
    }

///////////////////SANTA/////////////////
    public void travelAround() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public void goHome() {
        log.setReindeerReady(false);
        for (int i = 0; i < 6; i++) {
            reindeers[i].release();
        }
    }
}
