/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverStable;

import commonInfo.Message;

/**
 *
 * @author diogo
 */
public class StableInterface {

    private Stable stable;

    public StableInterface(Stable stable) {
        this.stable = stable;
    }

    public Message processAndReply(Message inMessage) /*throws MessageException */ {
        Message outMessage = null;                           // mensagem de resposta

        //validação da mensagem recebida
        /*switch (inMessage.getMsgType()) {
         case Message.GOBACKSTABLE:
         if ((inMessage.getMsgType() != Message.) || (inMessage.getFName().equals(""))) {
         throw new MessageException("Nome do ficheiro inexistente!", inMessage);
         }
         break;
         case Message.REQCUTH:
         if ((inMessage.getCustId() < 0) || (inMessage.getCustId() >= bShop.getNCust())) {
         throw new MessageException("Id do cliente inválido!", inMessage);
         }
         break;
         case Message.ENDOP:
         case Message.GOTOSLP:
         case Message.CALLCUST:
         if ((inMessage.getBarbId() < 0) || (inMessage.getBarbId() >= bShop.getNBarb())) {
         throw new MessageException("Id do barbeiro inválido!", inMessage);
         }
         break;
         case Message.GETPAY:
         if ((inMessage.getBarbId() < 0) || (inMessage.getBarbId() >= bShop.getNBarb())) {
         throw new MessageException("Id do barbeiro inválido!", inMessage);
         }
         if ((inMessage.getCustId() < 0) || (inMessage.getCustId() >= bShop.getNCust())) {
         throw new MessageException("Id do cliente inválido!", inMessage);
         }
         break;
         default:
         throw new MessageException("Tipo inválido!", inMessage);
         }*/

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
        }
        return (outMessage);
    }
}
