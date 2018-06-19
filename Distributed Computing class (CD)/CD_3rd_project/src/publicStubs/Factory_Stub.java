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
public class Factory_Stub {

    private String serverHostName = null;
    private final int serverPortNumber;

    public Factory_Stub(String hostName, int port) {
        this.serverHostName = hostName;
        this.serverPortNumber = port;
    }

    public void work(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ATWORK, id);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.ATWORK_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void goBackToWork(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.GOBACKWORK, id);
        
        String xml = outMessage.toXML();
        con.writeObject(xml);
        
        String xmlReply = (String) con.readObject ();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);
        System.out.println(xml+"___________GOBACKTOWORK, deve ter o MESSAGE TYPE");
        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();

        if (inMessage.getMsgType() != Message.GOBACKWORK_ANSWER) {
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
        if (inMessage.getMsgType()!= Message.SHUTDOWN_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
