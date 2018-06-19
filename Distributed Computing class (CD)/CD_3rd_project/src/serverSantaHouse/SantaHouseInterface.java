/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSantaHouse;

import publicInfo.Message;

/**
 *
 * @author diogo
 */
public class SantaHouseInterface {

    SantaHouse house;
    private String decision;
    private boolean elvesEnd;

    public SantaHouseInterface(SantaHouse house) {
        this.house = house;
    }

    public Object processAndReply(Object rawMessage) /*throws MessageException */ {
        Message outMessage = null;                           // mensagem de resposta

        String xml = (String) rawMessage;
        Message inMessage = new Message(xml);

        /* seu processamento */
        switch (inMessage.getMsgType()) {
            case Message.RESTING:                                                     // inicializar ficheiro de logging
                house.rest();
                outMessage = new Message(Message.RESTING_ANSWER);
                break;
            case Message.OPENTHEDOOR:
                decision = house.openTheDoor();
                outMessage = new Message(Message.OPENTHEDOOR_ANSWER, decision);
                System.out.println(outMessage+"__OPENTHEDOOR");
                break;
            case Message.KNOCKNOCK:
                house.knockKnock();
                outMessage = new Message(Message.KNOCKNOCK_ANSWER);
                break;
            case Message.ENDTRIP:
                house.endTrip();
                outMessage = new Message(Message.ENDTRIP_ANSWER);
                break;
            case Message.NEEDADVICE:
                house.needAdvice(inMessage.getElfID());
                outMessage = new Message(Message.NEEDADVICE_ANSWER);
                break;
            case Message.ENTERTHEHOUSE:
                System.out.println("_________________________________________SH Interface_ ENTERTHEHOUSE");
                house.enterTheHouse(inMessage.getElfID());
                outMessage = new Message(Message.ENTERTHEHOUSE_ANSWER);
                break;
            case Message.TALK:
                System.out.println("_________________________________________SH Interface_ TALK");
                house.talk(inMessage.getElfID());
                outMessage = new Message(Message.TALK_ANSWER);
                break;
            case Message.INVITEIN:
                System.out.println("_________________________________________SH Interface_ INVITEin");
                house.inviteIn(inMessage.getToggle());
                outMessage = new Message(Message.INVITEIN_ANSWER);
                break;
            case Message.LISTENELVES:
                System.out.println("_________________________________________SH Interface_ LISTENTOELVES");
                house.listenToElves();
                outMessage = new Message(Message.LISTENELVES_ANSWER);
                break;
            case Message.SAYGOODBYE:
                System.out.println("_________________________________________SH Interface_ SAYGOODBYE");
                house.sayGoodbye();
                outMessage = new Message(Message.SAYGOODBYE_ANSWER);
                break;
            case Message.SHUTDOWN:                                                        // shutdown do servidor
                SantaHouseMainServer.waitConnection = false;
                (((SantaHouseProxy) (Thread.currentThread())).getScon()).end();
                outMessage = new Message(Message.SHUTDOWN_ANSWER);            // gerar confirmação
                break;
        }
        String xmlReply = outMessage.toXML();

        return (xmlReply);
    }
}
