/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSide;

import serverFactory.Factory;
import serverLogger.Loggerr;
import serverSantaHouse.SantaHouse;

/**
 *
 * @author diogo
 */
public class Elf implements Runnable {

    private final int id;
    private final SantaHouse house;
    private final Factory factory;
    private final Loggerr log;

    public Elf(int id, SantaHouse house, Factory factory, Loggerr log) {
        this.id = id;
        this.house = house;
        this.factory = factory;
        this.log = log;
    }

    @Override
    public void run() {
        while (!log.endElfOperation(id)) {
            factory.work(id);
            house.needAdvice(id);
            house.enterTheHouse(id);
            house.talk(id);
            factory.goBackToWork(id);
        }
    }

}
