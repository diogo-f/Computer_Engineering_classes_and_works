/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfo;

/**
 *
 * @author diogo
 */
public class Message {

    ////////////Santa's messages////////////
    public static final int RESTING = 1;
    public static final int RESTING_ANSWER = 2;
    public static final int OPENTHEDOOR = 3;
    public static final int OPENTHEDOOR_ANSWER = 4;
    /*Santa with elves*/
    public static final int INVITEIN = 5;
    public static final int INVITEIN_ANSWER = 6;
    public static final int LISTENELVES = 7;
    public static final int LISTENELVES_ANSWER = 8;
    public static final int SAYGOODBYE = 9;
    public static final int SAYGOODBYE_ANSWER = 10;
    /*Santa with reindeers*/
    public static final int HARNESSREINDEERS = 11;
    public static final int HARNESSREINDEERS_ANSWER = 12;
    public static final int TRAVELAROUND = 13;
    public static final int TRAVELAROUND_ANSWER = 14;
    public static final int GOHOME = 15;
    public static final int GOHOME_ANSWER = 16;
    public static final int ENDTRIP = 17;
    public static final int ENDTRIP_ANSWER = 18;

    ////////////Elf's messages////////////
    public static final int ATWORK = 19;
    public static final int ATWORK_ANSWER = 20;
    public static final int GOBACKWORK = 21;
    public static final int GOBACKWORK_ANSWER = 22;
    public static final int NEEDADVICE = 23;
    public static final int NEEDADVICE_ANSWER = 24;
    public static final int ENTERTHEHOUSE = 25;
    public static final int ENTERTHEHOUSE_ANSWER = 26;
    public static final int TALK = 27;
    public static final int TALK_ANSWER = 28;

    ////////////Reindeers's messages////////////
    public static final int ENJOYHOLIDAYS = 29;
    public static final int ENJOYHOLIDAYS_ANSWER = 30;
    public static final int GOBACKSTABLE = 31;
    public static final int GOBACKSTABLE_ANSWER = 32;
    public static final int KNOCKNOCK = 33;
    public static final int KNOCKNOCK_ANSWER = 34;
    public static final int GROUPATSLEDGE = 35;
    public static final int GROUPATSLEDGE_ANSWER = 36;
    public static final int FOLLOWSANTA = 37;
    public static final int FOLLOWSANTA_ANSWER = 38;

    ////////////Logger messages//////////////
    /*Write states*/
    public static final int WRITELFSTATE = 39;
    public static final int WRITELFSTATE_ANSWER = 40;
    public static final int WRITEREINDEERSTATE = 41;
    public static final int WRITEREINDEERSTATE_ANSWER = 42;
    public static final int WRITESANTASTATE = 43;
    public static final int WRITESANTASTATE_ANSWER = 44;
    /*Reindeers*/
    public static final int REINDEERSATSTABLE = 45;
    public static final int REINDEERSATSTABLE_ANSWER = 46;
    public static final int SETREINDEERREADY = 47;
    public static final int SETREINDEERREADY_ANSWER = 48;
    /*Elves*/
    public static final int nELVESINGROUP = 49;
    public static final int nELVESINGROUP_ANSWER = 50;
    public static final int ELVESKNOCKED = 51;
    public static final int ELVESKNOCKED_ANSWER = 52;
    public static final int QUEUEADD = 53;
    public static final int QUEUEADD_ANSWER = 54;
    public static final int QUEUEMPTY = 55;
    public static final int QUEUEMPTY_ANSWER = 56;
    /*Stop conditions*/
    public static final int SETENDREINDEEROPERATION = 57;
    public static final int SETENDREINDEEROPERATION_ANSWER = 58;
    public static final int ENDREINDEEROPERATION = 59;
    public static final int ENDREINDEEROPERATION_ANSWER = 60;
    public static final int SETENDELFOPERATIONS = 61;
    public static final int SETENDELFOPERATIONS_ANSWER = 62;
    public static final int ENDELFOPERATION = 63;
    public static final int ENDELFOPERATION_ANSWER = 64;
    public static final int ENDELFSOPERATION = 65;
    public static final int ENDELFSOPERATION_ANSWER = 66;
    /*Print header*/
    public static final int GETHEADER = 67;
    public static final int GETHEADER_ANSWER = 68;

    /*Message content*/
    private int msgType = -1;
    private int reindeerID = -1;
    private int elfID = -1;
    private char santaDecision = 0;
    private boolean endReindeerOperation = false;
    private int reeindersAtStable = 0;

    public Message(int msgType) {
        this.msgType = msgType;
    }

    public Message(int msgType, int id) {
        this.msgType = msgType;
        if (msgType == KNOCKNOCK
                || msgType == ENDTRIP
                || msgType == ENJOYHOLIDAYS
                || msgType == GOBACKSTABLE
                || msgType == GROUPATSLEDGE) {
            this.reindeerID = id;
        } else {
            this.elfID = id;
        }
    }

    public Message(int msgType, char decision) {
        this.msgType = msgType;
        this.santaDecision = decision;
    }

    public int getMsgType() {
        return msgType;
    }

    public int getReindeerID() {
        return reindeerID;
    }

    public int getElfID() {
        return elfID;
    }

    public char getSantaDecision() {
        return santaDecision;
    }
}
