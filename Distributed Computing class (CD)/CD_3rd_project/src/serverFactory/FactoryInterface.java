/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverFactory;

import publicInfo.Message;

/**
 *
 * @author diogo
 */
public class FactoryInterface {

    private final Factory factory;

    public FactoryInterface(Factory factory) {
        this.factory = factory;
    }

    public Object processAndReply(Object rawMessage) {
        Message outMessage = null;
        
        String xml = (String) rawMessage;
        Message inMessage = new Message(xml);

        switch (inMessage.getMsgType()) {
            case Message.ATWORK:
                System.out.println("_________________________________________FAC Interface_ ATWORK");
                factory.work(inMessage.getElfID());
                outMessage = new Message(Message.ATWORK_ANSWER, inMessage.getElfID());
                System.out.println(outMessage+" !!");
                break;
            case Message.GOBACKWORK:
                System.out.println("_________________________________________FAC Interface_ GOBACKWORK");
                factory.goBackToWork(inMessage.getElfID());
                outMessage = new Message(Message.GOBACKWORK_ANSWER, inMessage.getReindeerID());
                break;
            case Message.SHUTDOWN:                                                        // shutdown do servidor
                serverMainFactory.waitConnection = false;
                (((FactoryProxy) (Thread.currentThread())).getScon()).end();
                outMessage = new Message(Message.SHUTDOWN_ANSWER);            // gerar confirmação
                break;
        }
        
        String xmlReply = outMessage.toXML();
        
        return (xmlReply);
    }
}
