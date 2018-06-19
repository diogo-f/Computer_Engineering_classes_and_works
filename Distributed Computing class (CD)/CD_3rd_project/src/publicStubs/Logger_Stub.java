/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicStubs;

import genclass.GenericIO;
import publicInfo.ClientCom;
import publicInfo.Message;
import publicInfo.States;

/**
 *
 * @author diogo
 */
public class Logger_Stub {

    private String serverHostName = null;
    private final int serverPortNumber;

    public Logger_Stub(String hostName, int port) {
        this.serverHostName = hostName;
        this.serverPortNumber = port;
    }

    public void writeElfState(int id, States state) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        System.out.println(state.getValue());
        outMessage = new Message(Message.WRITELFSTATE, id, state.getValue());

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.WRITELFSTATE_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void writeReindeerState(int id, States state) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.WRITEREINDEERSTATE, id, state.getValue());

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.WRITEREINDEERSTATE_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void writeSantaState(States state) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.WRITESANTASTATE, state.getValue());

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.WRITESANTASTATE_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void reindeersAtStable(int i) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.REINDEERSATSTABLE, i);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.REINDEERSATSTABLE_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void setReindeerReady(boolean ready) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.SETREINDEERREADY, ready);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.SETREINDEERREADY_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void nElvesInGroup(int i) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.nELVESINGROUP, i);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.nELVESINGROUP_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void elvesKnocked(boolean k) {
        System.out.println("ELFS__________________________|||||1|1|1|1|1|||||_KNOCK_KNOCK"+k);
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ELVESKNOCKED, k);
        System.out.println("ELFS__________________________|||||2|2|2|2|2|||||_KNOCK_KNOCK"+k);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();
        inMessage = new Message(xmlReply);
        System.out.println(xmlReply + "__________________________||||3|3|3|3|3||||||_KNOCK_KNOCK"+k);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.ELVESKNOCKED_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void queueAdd(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.QUEUEADD, id);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.QUEUEADD_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void queueEmpty() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.QUEUEMPTY);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.QUEUEMPTY_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void endReindeerOperation(boolean end) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.SETENDREINDEEROPERATION, end);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.SETENDREINDEEROPERATION_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public boolean endReindeerOperation() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ENDREINDEEROPERATION);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.ENDREINDEEROPERATION_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
        return inMessage.getToggle();
    }

    public void endElfOperations(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.SETENDELFOPERATION, id);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.SETENDELFOPERATION_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public boolean endElfOperation(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ENDELFOPERATIONs, id);
        System.out.println("ID" + id);

        String xml = outMessage.toXML();
        System.out.println(xml + " vazio?!?");
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.ENDELFOPERATIONs_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();

        return inMessage.getToggle();
    }

    public boolean endElfsOperation() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.ENDELFSOPERATION);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.ENDELFSOPERATION_ANSWER) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();

        return inMessage.getToggle();
    }

    public void getHeader() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        outMessage = new Message(Message.GETHEADER);

        String xml = outMessage.toXML();
        con.writeObject(xml);

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
        inMessage = new Message(xmlReply);

        //con.writeObject(outMessage);
        //inMessage = (Message) con.readObject();
        if (inMessage.getMsgType() != Message.GETHEADER_ANSWER) {
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

        String xmlReply = (String) con.readObject();                  // recebe res|  ---------------------------------------------------------------------------------
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
