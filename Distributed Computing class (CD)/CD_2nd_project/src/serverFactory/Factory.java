/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverFactory;

import commonInfo.InitialData;
import commonInfo.States;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import serverLogger.Loggerr;

/**
 *
 * @author diogo
 */
public class Factory {

    private final Semaphore mutex;
    private final Semaphore[] elves;
    private final Loggerr log;
    private final Queue<Integer> gnoIds;
    private final InitialData data;

    public Factory(Loggerr log, InitialData data) {
        this.log = log;
        this.data = data;
        mutex = new Semaphore(1);
        elves = new Semaphore[data.getnElfs()];
        gnoIds = new LinkedList<>();

        for (int i = 0; i < elves.length; i++) {
            elves[i] = new Semaphore(0);
        }
    }

    public void work(int id) {
        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt((200 - 100) + 1) + 200));
        } catch (InterruptedException e) {
        }
    }

    public void goBackToWork(int id) {
        mutex.release();
        try {
            mutex.acquire();
            log.writeElfState(id, States.WORK);
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }
}
