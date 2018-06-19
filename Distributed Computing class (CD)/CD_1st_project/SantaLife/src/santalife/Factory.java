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

/**
 *
 * @author diogo
 */
public class Factory {

    private final Semaphore mutex;
    private final Semaphore elvesWaiting;
    private final Semaphore[] elves;
    private final Loggerr log;
    private final Queue<Integer> gnoIds;
    private int cont;

    public Factory(Loggerr log) {
        cont = 0;
        this.log = log;
        mutex = new Semaphore(1);
        elvesWaiting = new Semaphore(3);
        elves = new Semaphore[6];
        gnoIds = new LinkedList<>();

        for (int i = 0; i < elves.length; i++) {
            elves[i] = new Semaphore(0);
        }
    }

    public void work(int id) {
        try {
            mutex.acquire();
            log.writeElfState(id, States.WORK);
            mutex.release();
        } catch (InterruptedException ex) {
        }
        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt((2000 - 200) + 1) + 200));
        } catch (InterruptedException e) {
        }
    }

    public void needAdvice(int id) {
        try {
            elvesWaiting.acquire();
            mutex.acquire();
            log.writeElfState(id, States.WISH);
            cont++;
            log.nElvesInGroup(1);
            log.queueAdd(id);

            if (cont == 3) {
                mutex.release();
                //gnoIds.remove();
                for (int i = 0; i < cont-1; i++) {
                    elves[gnoIds.remove()].release();
                }
            } else {
                gnoIds.add(id);
                mutex.release();
                elves[id].acquire();
            }

        } catch (InterruptedException ex) {
        }
    }

    public void goBackToWork(int id, int theEnd) {
        try {
            mutex.acquire();
            cont--;
            if (cont == 0) {
                elvesWaiting.release(3);
            } else if (theEnd == 4) {
                log.endOperation(true);
            }
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }
}
