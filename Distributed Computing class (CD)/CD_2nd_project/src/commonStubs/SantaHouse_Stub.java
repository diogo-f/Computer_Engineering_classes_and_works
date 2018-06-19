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
public class SantaHouse_Stub {

    private String serverHostName = null;
    private int serverPortNumber;

    public SantaHouse_Stub(String hostName, int port) {
        this.serverHostName = hostName;
        this.serverPortNumber = port;
    }

    public void rest() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.RESTING);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.RESTING) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public char openTheDoor(char decision) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.OPENTHEDOOR);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.OPENTHEDOOR) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();

        return decision;
    }

    public void inviteIn() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.INVITEIN);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.INVITEIN) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void listenToElves() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.LISTENELVES);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.LISTENELVES) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void sayGoodbye() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.SAYGOODBYE);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.SAYGOODBYE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void needAdvice() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.NEEDADVICE);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.NEEDADVICE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void enterTheHouse() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ENTERTHEHOUSE);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.ENTERTHEHOUSE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void talk() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.TALK);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.TALK) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void knockKnock() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.KNOCKNOCK);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.KNOCKNOCK) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void endTrip() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ENDTRIP);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.ENDTRIP) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
