/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicStubs;

import publicInfo.ClientCom;
import publicInfo.Message;
import genclass.GenericIO;

/**
 *
 * @author diogo
 */
public class SantaHouse_Stub {

    private String serverHostName = null;
    private final int serverPortNumber;

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

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.RESTING_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public String openTheDoor() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.OPENTHEDOOR);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.OPENTHEDOOR_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();

        return inMessage.getSantaDecision();
    }

    public void inviteIn(boolean elvesEnd) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.INVITEIN, elvesEnd);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.INVITEIN_ANSWER) {
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
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.LISTENELVES_ANSWER) {
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
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.SAYGOODBYE_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void needAdvice(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.NEEDADVICE, id);
        
        String xml = outMessage.toXML();
        System.out.println(xml+" needAdvice");
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.NEEDADVICE_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void enterTheHouse(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ENTERTHEHOUSE, id);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.ENTERTHEHOUSE_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void talk(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.TALK, id);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.TALK_ANSWER) {
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
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.ENDTRIP_ANSWER) {
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

        if (inMessage.getMsgType() != Message.KNOCKNOCK_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void shutdown() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.SHUTDOWN);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.SHUTDOWN_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
