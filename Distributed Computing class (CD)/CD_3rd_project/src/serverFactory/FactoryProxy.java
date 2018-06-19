/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverFactory;

import genclass.GenericIO;
import publicInfo.Message;
import publicInfo.ServerCom;

/**
 *
 * @author diogo
 */
public class FactoryProxy extends Thread {

    private static int nProxy = 0;
    private final ServerCom sconi;
    private final FactoryInterface factoryInterface;

    public FactoryProxy(ServerCom sconi, FactoryInterface factoryInterface) {
        super("Proxy_" + FactoryProxy.getProxyId());
        this.sconi = sconi;
        this.factoryInterface = factoryInterface;
    }

    @Override
    public void run() {
        Object inMessage = null, outMessage = null;

        inMessage = sconi.readObject();
        outMessage = factoryInterface.processAndReply(inMessage);
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
