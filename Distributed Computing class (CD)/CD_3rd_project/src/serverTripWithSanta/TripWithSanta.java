/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverTripWithSanta;

import publicInfo.InitialData;
import publicInfo.States;
import java.util.concurrent.Semaphore;
import publicStubs.Logger_Stub;

/**
 *
 * @author diogo
 */
public class TripWithSanta {

    private final Semaphore[] reindeers;
    private final Logger_Stub log;

    public TripWithSanta(Logger_Stub log) {
        reindeers = new Semaphore[InitialData.nReindeers];                   //um semaforo por rena
        this.log = log;

        for (int i = 0; i < reindeers.length; i++) {
            reindeers[i] = new Semaphore(0);            //inicializar os 6 semaforos das renas
        }
    }

//////////////////Reindeers work///////////////
    public void followSantaDirections(int id) {
        try {
            reindeers[id].acquire();            //renas bloqueiam
        } catch (InterruptedException ex) {
        }
    }

///////////////////Santa's work/////////////////
    public void travelAround() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
    }

    public void goHome() {
        log.setReindeerReady(false);            //dá a conhecer ao logger que as renas já não estão prontas
        for (int i = 0; i < InitialData.nReindeers; i++) {
            reindeers[i].release();             //desbloqueia as renas e estas vão para o metodo enjoyingHolidays()
            log.writeReindeerState(i, States.HOLI);
        }
    }
}
