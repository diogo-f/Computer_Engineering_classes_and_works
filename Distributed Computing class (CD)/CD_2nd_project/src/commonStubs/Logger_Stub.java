/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonStubs;

import commonInfo.ClientCom;
import commonInfo.Message;
import genclass.GenericIO;

/**
 *
 * @author diogo
 */
public class Logger_Stub {
    private String serverHostName = null;
    private int serverPortNumber;

    public Logger_Stub(String hostName, int port) {
        this.serverHostName = hostName;
        this.serverPortNumber = port;
    }

    public void work() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ATWORK);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.ATWORK) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
