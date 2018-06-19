/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverTripWithSanta;

import publicInfo.Message;

/**
 *
 * @author diogo
 */
public class TripWithSantaInterface {

    TripWithSanta trip;

    public TripWithSantaInterface(TripWithSanta trip) {
        this.trip = trip;
    }

    public Object processAndReply(Object rawMessage) /*throws MessageException */ {
        Message outMessage = null;                           // mensagem de resposta

        String xml = (String) rawMessage;
        Message inMessage = new Message(xml);
        
        /* seu processamento */
        switch (inMessage.getMsgType()) {
            case Message.FOLLOWSANTA:                                                     // inicializar ficheiro de logging
                System.out.println(inMessage.getReindeerID());
                trip.followSantaDirections(inMessage.getReindeerID());
                outMessage = new Message(Message.FOLLOWSANTA_ANSWER, inMessage.getReindeerID());
                break;
            case Message.TRAVELAROUND:
                trip.travelAround();
                outMessage = new Message(Message.TRAVELAROUND_ANSWER);
                break;
            case Message.GOHOME:
                trip.goHome();
                outMessage = new Message(Message.GOHOME_ANSWER);
                break;
            case Message.SHUTDOWN:                                                        // shutdown do servidor
                serverMainTripWSanta.waitConnection = false;
                (((TripWithSantaProxy) (Thread.currentThread())).getScon()).end();
                outMessage = new Message(Message.SHUTDOWN_ANSWER);            // gerar confirmação
                break;
        }
        String xmlReply = outMessage.toXML();

        return (xmlReply);
    }
}
