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
public class TripWithSanta_Stub {

    private String serverHostName = null;
    private final int serverPortNumber;

    public TripWithSanta_Stub(String hostName, int port) {
        this.serverHostName = hostName;
        this.serverPortNumber = port;
    }

    public void followSantaDirections(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.FOLLOWSANTA, id);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.FOLLOWSANTA_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void travelAround() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.TRAVELAROUND);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.TRAVELAROUND_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void goHome() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.GOHOME);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.GOHOME_ANSWER) {
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
