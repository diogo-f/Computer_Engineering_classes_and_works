/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSantaHouse;

import commonInfo.InitialData;
import commonInfo.States;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import serverLogger.Loggerr;

/**
 *
 * @author diogo
 */
public class SantaHouse {

    private final Loggerr log;
    private final Semaphore mutex;
    private final Semaphore sleep;
    private final Semaphore santaWaiting;
    private final Semaphore elvesGroupWaiting;
    private final Semaphore[] elvesWaiting;
    private final List<Integer> elvesWithSanta;
    private int cont;
    private boolean lastReindeer;
    private boolean weAreElves;
    private boolean elvesEnd;
    private final InitialData data;

    public SantaHouse(Loggerr log, InitialData data) {
        this.log = log;
        this.data = data;
        mutex = new Semaphore(1);               //
        sleep = new Semaphore(0);               //pai natal a dormir
        elvesGroupWaiting = new Semaphore(3);   //grupo, ao 4º espera
        elvesWaiting = new Semaphore[data.getnElfs()];        //
        santaWaiting = new Semaphore(0);        //
        elvesWithSanta = new ArrayList<>();     //
        lastReindeer = false;                   //
        weAreElves = false;                     //
        elvesEnd = false;
        cont = 0;

        for (int i = 0; i < elvesWaiting.length; i++) {
            elvesWaiting[i] = new Semaphore(0);
        }
    }

    //////////////////Santa's work//////////////////
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

    public void inviteIn(boolean elvesEnd) {
        try {
            mutex.acquire();
            log.writeSantaState(States.MEET);
            mutex.release();

            this.elvesEnd = elvesEnd;
            for (int i = 0; i < elvesWithSanta.size(); i++) {
                elvesWaiting[elvesWithSanta.get(i)].release();
                santaWaiting.acquire();
            }

        } catch (InterruptedException ex) {
        }
    }

    public void listenToElves() {
        try {
            Random rand = new Random();
            Thread.sleep(rand.nextInt(((100 - 50) + 1) + 50));        //Pai natal esta ocupado a ouvir os gnomos
        } catch (InterruptedException ex) {
        }
    }

    public void sayGoodbye() {
        try {
            mutex.acquire();
            log.elvesKnocked(false);
            log.nElvesInGroup(-data.getMaxElvesInGroup());
            log.queueEmpty();

            for (int i = 0; i < elvesWithSanta.size(); i++) {
                elvesWaiting[elvesWithSanta.get(i)].release();          //pai natal liberta todos os gnomos
            }
            elvesWithSanta.clear();                                     //limpa a lista de gnomos
            cont = 0;
            elvesGroupWaiting.release(data.getMaxElvesInGroup());       //só em ultimo é que da permição a outro grupo
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    ///////////////////Elf's work//////////////////
    public void needAdvice(int id) {
        try {
            elvesGroupWaiting.acquire();
            mutex.acquire();
            cont++;
            log.writeElfState(id, States.WISH);
            log.nElvesInGroup(1);
            log.queueAdd(id);
            mutex.release();
            if (cont == data.getMaxElvesInGroup()) {
                log.elvesKnocked(true);
                weAreElves = true;
                sleep.release();
            }
            elvesWithSanta.add(id);
            elvesWaiting[id].acquire();
            log.writeElfState(id, States.JOIN);
        } catch (InterruptedException ex) {
        }
    }

    public void enterTheHouse(int id) {
        try {
            mutex.acquire();
            log.writeElfState(id, States.CONS);
            mutex.release();
            if (elvesEnd) {
                log.endElfOperations(id);
            }
            santaWaiting.release();
        } catch (InterruptedException ex) {
        }
    }

    public void talk(int id) {
        try {
            elvesWaiting[id].acquire();
        } catch (InterruptedException ex) {
        }
    }

    /////////////////Last reindeer//////////////////
    public void knockKnock() {
        try {
            mutex.acquire();
            lastReindeer = true;
            sleep.release();
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    public void endTrip() {
        lastReindeer = false;
    }
}
