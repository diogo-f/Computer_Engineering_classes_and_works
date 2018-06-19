/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverStable;

import publicInfo.Message;
import publicInfo.ServerCom;
import genclass.GenericIO;

/**
 *
 * @author diogo
 */
public class StableProxy extends Thread {

    private static int nProxy = 0;
    private final ServerCom sconi;
    private final StableInterface stableInter;

    public StableProxy(ServerCom sconi, StableInterface stableInter) {
        super("Proxy_" + StableProxy.getProxyId());

        this.sconi = sconi;
        this.stableInter = stableInter;
    }

    @Override
    public void run() {
        Object inMessage = null, outMessage = null;

        inMessage = sconi.readObject();
        outMessage = stableInter.processAndReply(inMessage);
        sconi.writeObject(outMessage);
        sconi.close();
    }

    private static int getProxyId() {
        Class<?> cl = null;
        int proxyId;

        try {
            cl = Class.forName("serverStable.StableProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("O tipo de dados StableProxy não foi encontrado!");
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
