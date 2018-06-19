/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverTripWithSanta;

import commonInfo.InitialData;
import commonInfo.States;
import java.util.concurrent.Semaphore;
import serverLogger.Loggerr;

/**
 *
 * @author diogo
 */
public class TripWithSanta {

    private final Semaphore[] reindeers;
    private final Loggerr log;
    private final InitialData data;

    public TripWithSanta(Loggerr log, InitialData data) {
        reindeers = new Semaphore[data.getnReindeers()];                   //um semaforo por rena
        this.data = data;
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
        for (int i = 0; i < data.getnReindeers(); i++) {
            reindeers[i].release();             //desbloqueia as renas e estas vão para o metodo enjoyingHolidays()
            log.writeReindeerState(i, States.HOLI);
        }
    }
}
