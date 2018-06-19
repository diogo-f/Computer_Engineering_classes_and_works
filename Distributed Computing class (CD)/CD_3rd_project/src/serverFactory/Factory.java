/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverFactory;

import publicInfo.InitialData;
import publicInfo.States;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import publicStubs.Logger_Stub;

/**
 *
 * @author diogo
 */
public class Factory {

    private final Semaphore mutex;
    private final Semaphore[] elves;
    private final Logger_Stub log;
    private final Queue<Integer> gnoIds;

    public Factory(Logger_Stub log) {
        this.log = log;
        mutex = new Semaphore(1);
        elves = new Semaphore[InitialData.nElfs];
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
        try {
            mutex.acquire();
            System.out.println("CHEGOU AO BACKTO WORK");
            log.writeElfState(id, States.WORK);
            System.out.println("SAIU DO BACKTO WORK");
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }
}
