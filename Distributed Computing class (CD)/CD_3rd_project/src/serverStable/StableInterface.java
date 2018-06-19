/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverStable;

import publicInfo.Message;

/**
 *
 * @author diogo
 */
public class StableInterface {

    private final Stable stable;

    public StableInterface(Stable stable) {
        this.stable = stable;
    }

    public Object processAndReply(Object rawMessage) /*throws MessageException */ {
        Message outMessage = null;                           // mensagem de resposta

        String xml = (String) rawMessage;
        Message inMessage = new Message(xml);

        /* seu processamento */
        switch (inMessage.getMsgType()) {
            case Message.ENJOYHOLIDAYS:                                                     // inicializar ficheiro de logging
                stable.enjoyingHolidays(inMessage.getReindeerID());
                outMessage = new Message(Message.ENJOYHOLIDAYS_ANSWER, inMessage.getReindeerID());
                break;
            case Message.GOBACKSTABLE:
                stable.goBackStable(inMessage.getReindeerID());
                outMessage = new Message(Message.GOBACKSTABLE_ANSWER, inMessage.getReindeerID());
                break;
            case Message.GROUPATSLEDGE:
                stable.groupAtTheSledge(inMessage.getReindeerID());
                outMessage = new Message(Message.GROUPATSLEDGE_ANSWER, inMessage.getReindeerID());
                break;
            case Message.HARNESSREINDEERS:
                stable.harnessTheReindeers();
                outMessage = new Message(Message.HARNESSREINDEERS_ANSWER);
                break;
            case Message.SHUTDOWN:                                                        // shutdown do servidor
                ServerMainStable.waitConnection = false;
                (((StableProxy) (Thread.currentThread())).getScon()).end();
                outMessage = new Message(Message.SHUTDOWN_ANSWER);            // gerar confirmação
                break;
        }
        String xmlReply = outMessage.toXML();

        return (xmlReply);
    }
}
