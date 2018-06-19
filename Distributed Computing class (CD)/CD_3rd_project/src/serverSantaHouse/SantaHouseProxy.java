/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package serverSantaHouse;

import publicInfo.Message;
import publicInfo.ServerCom;
import genclass.GenericIO;

/**
 *
 * @author diogo
 */
public class SantaHouseProxy extends Thread {

    private static int nProxy = 0;
    private ServerCom sconi;
    private SantaHouseInterface houseInterface;

    public SantaHouseProxy(ServerCom sconi, SantaHouseInterface houseInterface) {
        super("Proxy_" + SantaHouseProxy.getProxyId());

        this.sconi = sconi;
        this.houseInterface = houseInterface;
    }

    @Override
    public void run() {
        Object inMessage = null, outMessage = null;

        inMessage = sconi.readObject();
        outMessage = houseInterface.processAndReply(inMessage);
        sconi.writeObject(outMessage);
        sconi.close();
    }

    private static int getProxyId() {
        Class<?> cl = null;
        int proxyId;

        try {
            cl = Class.forName("serverSantaHouse.SantaHouseProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("O tipo de dados SantaHouseProxy não foi encontrado!");
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }
        return proxyId;
    }

    public ServerCom getScon() {
        return sconi;
    }
}
