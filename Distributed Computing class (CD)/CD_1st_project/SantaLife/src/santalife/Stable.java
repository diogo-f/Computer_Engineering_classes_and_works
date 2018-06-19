/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santalife;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class Stable {

    private int stableCont;
    private int sledgeCont;
    private final Loggerr log;
    private final Semaphore mutex;
    private final Semaphore[] reindeersWaiting;
    private final Semaphore santa;
    private final Queue<Integer> reinIds;

    public Stable(Loggerr log) {
        this.stableCont = 0;
        this.sledgeCont = 0;
        reinIds = new LinkedList<>();
        this.log = log;
        mutex = new Semaphore(1);
        reindeersWaiting = new Semaphore[6];
        santa = new Semaphore(0);

        for (int i = 0; i < reindeersWaiting.length; i++) {
            reindeersWaiting[i] = new Semaphore(0);

        }
    }

    public void enjoyingHolidays(int id) {
        try {
            mutex.acquire();
            log.writeReindeerState(id, States.HOLI);
            mutex.release();
        } catch (InterruptedException ex) {
        }

        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt((10000 - 5000) + 1) + 5000));
        } catch (InterruptedException e) {
        }
    }

    public boolean goBackStable(int id) {
        try {
            mutex.acquire();
            log.writeReindeerState(id, States.STBL);
            log.reindeersAtStable(1);
            stableCont++;

            if (stableCont == 6) {
                stableCont--;
                log.reindeersAtStable(-1);
                for (int i = 0; i < 5; i++) {
                    stableCont--;
                    log.reindeersAtStable(-1);
                    reindeersWaiting[reinIds.remove()].release();
                }
                mutex.release();
                return true;
            } else {
                reinIds.add(id);
                mutex.release();
                reindeersWaiting[id].acquire();
            }
        } catch (InterruptedException ex) {
        }
        return false;
    }

    public void groupAtTheSledge(int id) {
        try {
            mutex.acquire();
            log.writeReindeerState(id, States.SLEG);
            log.setReindeerReady(true);
            this.sledgeCont++;
            if (sledgeCont == 6) {
                santa.release();
            }
            mutex.release();
            reindeersWaiting[id].acquire();
        } catch (InterruptedException ex) {
        }
    }

    public void harnessTheReindeers() {
        try {
            mutex.acquire();
            log.writeSantaState(States.DIST);
            mutex.release();
            santa.acquire();
            for (int i = 0; i < 6; i++) {
                reindeersWaiting[i].release();
                sledgeCont--;
            }
        } catch (InterruptedException ex) {
        }
    }
}
