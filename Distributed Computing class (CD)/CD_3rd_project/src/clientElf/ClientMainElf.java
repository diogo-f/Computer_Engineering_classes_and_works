/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientElf;

import java.io.FileNotFoundException;
import publicInfo.InitialData;
import publicStubs.Factory_Stub;
import publicStubs.Logger_Stub;
import publicStubs.SantaHouse_Stub;

/**
 *
 * @author diogo
 */
public class ClientMainElf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Logger_Stub log = new Logger_Stub(InitialData.serverHostNameServerLogger, InitialData.loggerPort);

        Elf[] elf = new Elf[InitialData.nElfs];     // array de threads renas
        Factory_Stub factory = new Factory_Stub(InitialData.serverHostNameServerFactory, InitialData.factoryPort);
        SantaHouse_Stub house = new SantaHouse_Stub(InitialData.serverHostNameServerSantaHouse, InitialData.housePort);

        for (int i = 0; i < InitialData.nElfs; i++) {
            elf[i] = new Elf(i, house, factory, log);
        }

        for (int i = 0; i < elf.length; i++) {
            Thread elfThread = new Thread(new Elf(i, house, factory, log));
            elfThread.setName("Elf_" + i);
            elfThread.start();
        }
    }
}
