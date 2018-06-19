/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverStable;

import commonInfo.Message;
import commonInfo.MessageException;
import commonInfo.ServerCom;
import genclass.GenericIO;

/**
 *
 * @author diogo
 */
public class StableProxy extends Thread {

    private static int nProxy = 0;
    private ServerCom sconi;
    private StableInterface stableInter;

    public StableProxy(ServerCom sconi, StableInterface stableInter) {
        super("Proxy_" + StableProxy.getProxyId());

        this.sconi = sconi;
        this.stableInter = stableInter;
    }

    @Override
    public void run() {
        Message inMessage = null, outMessage = null;

        inMessage = (Message) sconi.readObject();
        outMessage = stableInter.processAndReply(inMessage);
        sconi.writeObject(outMessage);
        sconi.close();
    }

    private static int getProxyId() {
        Class<?> cl = null;
        int proxyId;

        try {
            cl = Class.forName("serverSide.ClientProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("O tipo de dados ClientProxy não foi encontrado!");
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }
        return proxyId;
    }
}
