/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverLogger;

import publicInfo.Message;

/**
 *
 * @author diogo
 */
public class LoggerInterface {

    Logger log;
    private boolean endR = false, endE = false, endS = false;

    public LoggerInterface(Logger log) {
        this.log = log;
    }

    public Object processAndReply(Object rawMessage) /*throws MessageException */ {
        Message outMessage = null;                           // mensagem de resposta

        String xml = (String) rawMessage;
        Message inMessage = new Message(xml);

        /* seu processamento */
        switch (inMessage.getMsgType()) {
            case Message.WRITELFSTATE:                                                     // inicializar ficheiro de logging
                log.writeElfState(inMessage.getElfID(), inMessage.getStateInt());
                outMessage = new Message(Message.WRITELFSTATE_ANSWER);
                break;
            case Message.WRITEREINDEERSTATE:
                log.writeReindeerState(inMessage.getReindeerID(), inMessage.getStateInt());
                outMessage = new Message(Message.WRITEREINDEERSTATE_ANSWER);  //posso fazer isto?
                break;
            case Message.WRITESANTASTATE:
                log.writeSantaState(inMessage.getStateInt());
                outMessage = new Message(Message.WRITESANTASTATE_ANSWER);
                break;
            case Message.REINDEERSATSTABLE:
                log.reindeersAtStable(inMessage.getGroupCounter());
                outMessage = new Message(Message.REINDEERSATSTABLE_ANSWER);
                break;
            case Message.SETREINDEERREADY:
                log.setReindeerReady(inMessage.getToggle());
                outMessage = new Message(Message.SETREINDEERREADY_ANSWER);
                break;
            case Message.nELVESINGROUP:
                System.out.println(inMessage + " _nElvesInGroup");
                log.nElvesInGroup(inMessage.getGroupCounter());
                outMessage = new Message(Message.nELVESINGROUP_ANSWER);
                System.out.println(outMessage + " _nElvesInGroup??");
                break;
            case Message.ELVESKNOCKED:
                System.out.println("ELVES KNOCKED?????????????????????????????????????           " + inMessage.getToggle());
                log.elvesKnocked(inMessage.getToggle());
                outMessage = new Message(Message.ELVESKNOCKED_ANSWER);
                break;
            case Message.QUEUEADD:
                log.queueAdd(inMessage.getElfID());
                outMessage = new Message(Message.QUEUEADD_ANSWER);
                break;
            case Message.QUEUEMPTY:
                log.queueEmpty();
                outMessage = new Message(Message.QUEUEMPTY_ANSWER);
                break;
            case Message.SETENDREINDEEROPERATION:
                log.endReindeerOperation(inMessage.getToggle());
                outMessage = new Message(Message.SETENDREINDEEROPERATION_ANSWER);
                break;
            case Message.ENDREINDEEROPERATION:
                endR = log.endReindeerOperation();
                outMessage = new Message(Message.ENDREINDEEROPERATION_ANSWER, endR);
                break;
            case Message.ENDELFOPERATIONs:
                endE = log.endElfOperation(inMessage.getElfID());
                outMessage = new Message(Message.ENDELFOPERATIONs_ANSWER, endE);
                break;
            case Message.SETENDELFOPERATION:
                log.endElfOperations(inMessage.getElfID());
                outMessage = new Message(Message.SETENDELFOPERATION_ANSWER);
                break;
            case Message.ENDELFSOPERATION:
                endS = log.endElfsOperation();
                outMessage = new Message(Message.ENDELFSOPERATION_ANSWER, endS);
                break;
            case Message.GETHEADER:
                log.getHeader();
                outMessage = new Message(Message.GETHEADER_ANSWER);
                break;
            case Message.SHUTDOWN:
                ServerMainLogger.waitConnection = false;
                (((LoggerProxy) (Thread.currentThread())).getScon()).end();
                outMessage = new Message(Message.SHUTDOWN_ANSWER);
                break;
        }
        String xmlReply = outMessage.toXML();
        System.out.println(xmlReply + "WHAT???");

        return (xmlReply);
    }
}
