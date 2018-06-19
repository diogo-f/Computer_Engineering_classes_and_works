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
public class Stable_Stub {

    private String serverHostName = null;
    private int serverPortNumber;

    public Stable_Stub(String hostName, int port) {
        this.serverHostName = hostName;
        this.serverPortNumber = port;
    }

    public void enjoyingHolidays(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ENJOYHOLIDAYS, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.ENJOYHOLIDAYS) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void goBackStable(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.GOBACKSTABLE, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.GOBACKSTABLE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void groupAtTheSledge(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.GROUPATSLEDGE, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.GROUPATSLEDGE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void harnessReindeers() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.HARNESSREINDEERS);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.HARNESSREINDEERS) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
