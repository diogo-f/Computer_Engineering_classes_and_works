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
public class SantaHouse {

    private final Loggerr log;
    private final Semaphore mutex;
    private final Semaphore sleep;
    private final Semaphore waitForNextElf;
    private final Semaphore[] elvesWaiting;
    private final Queue<Integer> gnoIds;
    private int cont;
    private boolean lastReindeer;
    private boolean weAreElves;

    public SantaHouse(Loggerr log) {
        this.log = log;
        mutex = new Semaphore(1);
        sleep = new Semaphore(0);
        waitForNextElf = new Semaphore(0);
        elvesWaiting = new Semaphore[6];
        gnoIds = new LinkedList<>();
        lastReindeer = false;
        weAreElves = false;

        cont = 0;

        for (int i = 0; i < elvesWaiting.length; i++) {
            elvesWaiting[i] = new Semaphore(0);
        }
    }

    public void rest() {
        try {
            mutex.acquire();
            log.writeSantaState(States.REST);
            mutex.release();
            sleep.acquire();
        } catch (InterruptedException ex) {
        }
    }

    public char openTheDoor() {
        char decision = 0;
        try {
            mutex.acquire();
            log.writeSantaState(States.DECD);
            if (weAreElves && !lastReindeer) {
                decision = 'e';
            } else {
                decision = 'r';
            }
        } catch (InterruptedException ex) {
        }
        mutex.release();
        return decision;
    }

    public void inviteIn() {
        try {
            mutex.acquire();
            log.writeSantaState(States.MEET);
            elvesWaiting[gnoIds.peek()].release();
            mutex.release();
            waitForNextElf.acquire();
        } catch (InterruptedException ex) {
        }
    }

    public void sayGoodbye() {
        try {
            mutex.acquire();
            elvesWaiting[gnoIds.remove()].release();
            cont--;
            mutex.release();
            if (cont == 0) {
                log.nElvesInGroup(-3);
                log.elvesKnocked(false);
                log.queueEmpty();
            }
        } catch (InterruptedException ex) {
        }
    }

    /////Elfs////
    public void enterTheHouse(int id) {
        try {
            mutex.acquire();
            log.writeElfState(id, States.JOIN);
            gnoIds.add(id);
            cont++;
            if (cont == 3) {
                log.elvesKnocked(true);
                weAreElves = true;
                sleep.release();
            }
            mutex.release();
            elvesWaiting[id].acquire();
        } catch (InterruptedException ex) {
        }
    }

    public void talk(int id) {
        Random rand = new Random();
        try {
            mutex.acquire();
            log.writeElfState(id, States.CONS);
            Thread.sleep(rand.nextInt(((500 - 50) + 1) + 50));
            mutex.release();
            waitForNextElf.release();
            //elvesWaiting[id].release();
        } catch (InterruptedException ex) {
        }
    }

    /////LastReindeer/////
    public void knockKnock() {
        lastReindeer = true;
        sleep.release();
    }
}
