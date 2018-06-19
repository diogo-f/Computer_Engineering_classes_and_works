/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientElf;

import publicStubs.Factory_Stub;
import publicStubs.Logger_Stub;
import publicStubs.SantaHouse_Stub;

/**
 *
 * @author diogo
 */
public class Elf implements Runnable {

    private final int id;
    private final SantaHouse_Stub house;
    private final Factory_Stub factory;
    private final Logger_Stub log;

    public Elf(int id, SantaHouse_Stub house, Factory_Stub factory, Logger_Stub log) {
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
