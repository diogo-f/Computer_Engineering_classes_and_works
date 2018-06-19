/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santalife;

/**
 *
 * @author diogo
 */
public class Elf implements Runnable {

    private final int id;
    private final SantaHouse house;
    private final Factory factory;
    private final Loggerr log;
    private int cont;

    public Elf(int id, SantaHouse house, Factory factory, Loggerr log) {
        this.id = id;
        this.house = house;
        this.factory = factory;
        this.log = log;
        this.cont = 0;
    }

    @Override
    public void run() {
        while (!log.endOperSantaClaus()) {
            cont++;
            factory.work(id);
            factory.needAdvice(id);
            house.enterTheHouse(id);
            house.talk(id);
            factory.goBackToWork(id,cont);
        }
    }

}
