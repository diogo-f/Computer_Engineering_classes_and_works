/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverStable;

import publicInfo.InitialData;
import publicInfo.States;
import java.util.Random;
import java.util.concurrent.Semaphore;
import publicStubs.Logger_Stub;
import publicStubs.SantaHouse_Stub;

/**
 *
 * @author diogo
 */
public class Stable {

    private int stableCont;
    private int sledgeCont;
    private final Logger_Stub log;
    private final SantaHouse_Stub house;
    private final Semaphore mutex;
    private final Semaphore[] reindeersWaiting;
    private final Semaphore santa;

    public Stable(Logger_Stub log, SantaHouse_Stub house) {
        this.log = log;
        this.house = house;
        this.stableCont = 0;
        this.sledgeCont = 0;
        mutex = new Semaphore(1);
        reindeersWaiting = new Semaphore[6];                    //um semaforo por rena
        santa = new Semaphore(0);
        for (int i = 0; i < reindeersWaiting.length; i++) {
            reindeersWaiting[i] = new Semaphore(0);             //inicializar os 6 semaforos das renas
        }
    }

    public void enjoyingHolidays(int id) {
        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt((500 - 300) + 1) + 300));       //Cada rena está de ferias
        } catch (InterruptedException e) {
        }
    }

    public void goBackStable(int id) {
        try {
            mutex.acquire();
            log.reindeersAtStable(1);
            log.writeReindeerState(id, States.STBL);

            stableCont++;
            if (stableCont == InitialData.nElfs) {
                house.knockKnock();                 //a ultima rena vai acordar o pai natal
                log.setReindeerReady(true);
                stableCont = 0;
            }
            mutex.release();
            reindeersWaiting[id].acquire();         //Bloqueia cada rena no seu semaforo
        } catch (InterruptedException ex) {
        }
    }

    public void groupAtTheSledge(int id) {
        try {
            mutex.acquire();
            log.reindeersAtStable(-1);
            log.writeReindeerState(id, States.SLEG);

            this.sledgeCont++;
            if (sledgeCont == InitialData.nReindeers) {
                santa.release();
                sledgeCont = 0;
            }
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    //Santa's work
    public void harnessTheReindeers() {
        try {
            mutex.acquire();
            log.writeSantaState(States.DIST);
            mutex.release();
            for (int i = 0; i < InitialData.nReindeers; i++) {               //Pai natal liberta as renas
                reindeersWaiting[i].release();
            }
            santa.acquire();                            //Pai natal fica bloqueado a espera que as renas estejam prontas
        } catch (InterruptedException ex) {
        }
    }
}
