/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverTripWithSanta;

import publicInfo.Message;
import publicInfo.ServerCom;
import genclass.GenericIO;

/**
 *
 * @author diogo
 */
public class TripWithSantaProxy extends Thread {

    private static int nProxy = 0;
    private final ServerCom sconi;
    private final TripWithSantaInterface tripInter;

    public TripWithSantaProxy(ServerCom sconi, TripWithSantaInterface tripInter) {
        super("Proxy_" + TripWithSantaProxy.getProxyId());

        this.sconi = sconi;
        this.tripInter = tripInter;
    }

    @Override
    public void run() {
        Object inMessage = null, outMessage = null;

        inMessage = sconi.readObject();
        outMessage = tripInter.processAndReply(inMessage);
        sconi.writeObject(outMessage);
        sconi.close();
    }

    private static int getProxyId() {
        Class<?> cl = null;
        int proxyId;

        try {
            cl = Class.forName("serverTripWithSanta.TripWithSantaProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("O tipo de dados TripWithSantaProxy não foi encontrado!");
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
