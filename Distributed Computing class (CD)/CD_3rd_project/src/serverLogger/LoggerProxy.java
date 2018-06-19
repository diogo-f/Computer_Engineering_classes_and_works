/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverLogger;

import genclass.GenericIO;
import publicInfo.ServerCom;

/**
 *
 * @author diogo
 */
public class LoggerProxy extends Thread {

    private static int nProxy = 0;
    private ServerCom sconi;
    private LoggerInterface loggerInterface;

    public LoggerProxy(ServerCom sconi, LoggerInterface loggerInterface) {
        super("Proxy_" + LoggerProxy.getProxyId());

        this.sconi = sconi;
        this.loggerInterface = loggerInterface;
    }

    @Override
    public void run() {
        Object inMessage = null, outMessage = null;

        inMessage = sconi.readObject();
        outMessage = loggerInterface.processAndReply(inMessage);
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
