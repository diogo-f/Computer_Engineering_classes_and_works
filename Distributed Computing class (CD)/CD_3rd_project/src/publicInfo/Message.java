/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicInfo;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 *
 * @author diogo
 */
public class Message implements Serializable {

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
    public static final int SETENDELFOPERATION = 61;
    public static final int SETENDELFOPERATION_ANSWER = 62;
    public static final int ENDELFOPERATIONs = 63;
    public static final int ENDELFOPERATIONs_ANSWER = 64;
    public static final int ENDELFSOPERATION = 65;
    public static final int ENDELFSOPERATION_ANSWER = 66;
    /*Print header*/
    public static final int GETHEADER = 69;
    public static final int GETHEADER_ANSWER = 70;
    /*ShutDown*/
    public static final int SHUTDOWN = 71;
    public static final int SHUTDOWN_ANSWER = 72;


    /*Message content*/
    private int msgType = -1;
    private int reindeerID = -1;
    private int elfID = -1;
    private String santaDecision;
    private boolean endReindeerOperation;
    private int intToggle = 0;
    private boolean toggle;
    private States state;
    private int stateInt;

    public Message(int msgType) {
        this.msgType = msgType;
    }

    public Message(int msgType, int i) {
        this.msgType = msgType;
        if (msgType == KNOCKNOCK
                || msgType == ENDTRIP
                || msgType == ENDTRIP_ANSWER
                || msgType == ENJOYHOLIDAYS
                || msgType == ENJOYHOLIDAYS_ANSWER
                || msgType == GOBACKSTABLE
                || msgType == GOBACKSTABLE_ANSWER
                || msgType == GROUPATSLEDGE
                || msgType == GROUPATSLEDGE_ANSWER
                || msgType == FOLLOWSANTA_ANSWER
                || msgType == FOLLOWSANTA) {
            this.reindeerID = i;
        } else if (msgType == ATWORK
                || msgType == ATWORK_ANSWER
                || msgType == NEEDADVICE
                || msgType == NEEDADVICE_ANSWER
                || msgType == ENTERTHEHOUSE
                || msgType == ENTERTHEHOUSE_ANSWER
                || msgType == TALK
                || msgType == TALK_ANSWER
                || msgType == GOBACKWORK
                || msgType == GOBACKWORK_ANSWER
                || msgType == SETENDELFOPERATION
                || msgType == ENDELFOPERATIONs
                || msgType == QUEUEADD) {
            this.elfID = i;
        } else if (msgType == REINDEERSATSTABLE
                || msgType == nELVESINGROUP) {
            intToggle = i;
        } else if (msgType == WRITESANTASTATE) {
            stateInt = i;
        }
    }

    public Message(int msgType, String decision) {
        this.msgType = msgType;
        this.santaDecision = decision;
    }

    public Message(int msgType, boolean toggle) {      //trata das renas e dos gnomos(e logger), cada mensagem é unica, por isso não pode haver conflito
        this.msgType = msgType;
        this.toggle = toggle;
    }

    //////////\/ Para o tipo de mensagem com o State \/////////
    public Message(int msgType, int id, States state) {
        this.msgType = msgType;
        if (msgType == WRITELFSTATE) {
            this.elfID = id;
        } else {
            this.reindeerID = id;
        }
        this.state = state;
    }

    //////////\/ Para o tipo de msg com o State.getValue() \/////////
    public Message(int msgType, int id, int state) {
        this.msgType = msgType;
        if (msgType == WRITELFSTATE) {
            this.elfID = id;
        } else {
            this.reindeerID = id;
        }
        this.stateInt = state;
    }

    /*public Message(int msgType, States state) {
     this.msgType = msgType;
     this.state = state;
     }*/
    public int getMsgType() {
        return msgType;
    }

    public int getReindeerID() {
        return reindeerID;
    }

    public int getElfID() {
        return elfID;
    }

    public String getSantaDecision() {
        return santaDecision;
    }

    public boolean getToggle() {
        return toggle;
    }

    public States getState() {
        return state;
    }

    public int getGroupCounter() {
        return intToggle;
    }

    public int getStateInt() {
        return stateInt;
    }

    @Override
    public String toString() {
        return "Message\n{" + "\nmsgType=" + msgType + "\nreindeerID=" + reindeerID + "\nelfID=" + elfID + "\nsantaDecision=" + santaDecision + "\nendReindeerOperation=" + endReindeerOperation + "\nintToggle=" + intToggle + "\ntoggle=" + toggle + "\nstate=" + state + "\n}";
    }

    /**
     * XML
     */
    /*------------------------------------------------------------------------*/
    public Message(String xml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.exit(1);
        }

        Document doc = null;
        try {
            doc = db.parse(new InputSource(new StringReader(xml)));
        } catch (SAXException e) {
            System.err.println(Thread.currentThread().getName() + " XML parse error." + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.err.println(Thread.currentThread().getName() + " IO error." + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        Element root = doc.getDocumentElement();

        //DEBUG
        //System.err.println("ROOT: " + root.getNodeName());
        //System.err.println("TYPE: " + root.getAttribute("type"));
        switch (root.getNodeName()) {
            case "Request":
                System.out.println(root.getAttribute("type") + "_____REQUEST");
                switch (root.getAttribute("type")) {
                    case "RESTING":
                        msgType = RESTING;
                        break;
                    case "OPENTHEDOOR":
                        msgType = OPENTHEDOOR;
                        break;
                    case "INVITEIN":
                        msgType = INVITEIN;
                        toggle = Boolean.parseBoolean("Toogle");
                        break;
                    case "LISTENELVES":
                        msgType = LISTENELVES;
                        break;
                    case "SAYGOODBYE":
                        msgType = SAYGOODBYE;
                        break;
                    case "HARNESSREINDEERS":
                        msgType = HARNESSREINDEERS;
                        break;
                    case "TRAVELAROUND":
                        msgType = TRAVELAROUND;
                        break;
                    case "GOHOME":
                        msgType = GOHOME;
                        break;
                    case "ENDTRIP":
                        msgType = ENDTRIP;
                        break;
                    case "ATWORK":
                        msgType = ATWORK;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "GOBACKWORK":
                        msgType = GOBACKWORK;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "NEEDADVICE":
                        msgType = NEEDADVICE;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "ENTERTHEHOUSE":
                        msgType = ENTERTHEHOUSE;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "TALK":
                        msgType = TALK;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "ENJOYHOLIDAYS":
                        msgType = ENJOYHOLIDAYS;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        break;
                    case "GOBACKSTABLE":
                        msgType = GOBACKSTABLE;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        break;
                    case "KNOCKNOCK":
                        msgType = KNOCKNOCK;
                        break;
                    case "GROUPATSLEDGE":
                        msgType = GROUPATSLEDGE;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        break;
                    case "FOLLOWSANTA":
                        msgType = FOLLOWSANTA;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        break;
                    case "WRITELFSTATE":
                        msgType = WRITELFSTATE;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        stateInt = Integer.parseInt(root.getAttribute("State"));
                        break;
                    case "WRITEREINDEERSTATE":
                        msgType = WRITEREINDEERSTATE;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        stateInt = Integer.parseInt(root.getAttribute("State"));
                        break;
                    case "WRITESANTASTATE":
                        msgType = WRITESANTASTATE;
                        stateInt = Integer.parseInt(root.getAttribute("State"));
                        break;
                    case "REINDEERSATSTABLE":
                        msgType = REINDEERSATSTABLE;
                        intToggle = Integer.parseInt(root.getAttribute("IntToogle"));
                        break;
                    case "SETREINDEERREADY":
                        msgType = SETREINDEERREADY;
                        toggle = Boolean.parseBoolean(root.getAttribute("Toggle"));
                        break;
                    case "nELVESINGROUP":
                        msgType = nELVESINGROUP;
                        intToggle = Integer.parseInt(root.getAttribute("IntToogle"));
                        break;
                    case "ELVESKNOCKED":
                        msgType = ELVESKNOCKED;
                        System.out.println("------------------------------------------------------------MESSAGE ELVES KNOCKED" + root.getAttribute("Toggle"));
                        toggle = Boolean.valueOf(root.getAttribute("Toggle"));
                        break;
                    case "QUEUEADD":
                        msgType = QUEUEADD;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "QUEUEMPTY":
                        msgType = QUEUEMPTY;
                        break;
                    case "SETENDREINDEEROPERATION":
                        msgType = SETENDREINDEEROPERATION;
                        toggle = Boolean.parseBoolean(root.getAttribute("Toggle"));
                        break;
                    case "ENDREINDEEROPERATION":
                        msgType = ENDREINDEEROPERATION;
                        break;
                    case "SETENDELFOPERATION":
                        msgType = SETENDELFOPERATION;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "ENDELFOPERATIONs":
                        msgType = ENDELFOPERATIONs;
                        System.out.println(root.getAttribute("ElfID") + " WTF (message)");
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        System.out.println("saiu!!");
                        break;
                    case "ENDELFSOPERATION":
                        msgType = ENDELFSOPERATION;
                        break;
                    case "GETHEADER":
                        msgType = GETHEADER;
                        break;
                    case "SHUTDOWN":
                        msgType = SHUTDOWN;
                        break;
                    default: //ERRO
                        System.err.println(Thread.currentThread().getName() + " Undefined type1.");
                        System.exit(1);
                        break;
                }
                break;
            case "Reply":
                System.out.println(Thread.currentThread().getName() + "___that error!!");
                System.out.println(root.getAttribute("type") + "_____REPLY");
                switch (root.getAttribute("type")) {
                    case "RESTING_ANSWER":
                        msgType = RESTING_ANSWER;
                        break;
                    case "OPENTHEDOOR_ANSWER":
                        msgType = OPENTHEDOOR_ANSWER;
                        System.out.println(root.getAttribute("Decision"));
                        santaDecision = root.getAttribute("Decision");
                        break;
                    case "INVITEIN_ANSWER":
                        msgType = INVITEIN_ANSWER;
                        break;
                    case "LISTENELVES_ANSWER":
                        msgType = LISTENELVES_ANSWER;
                        break;
                    case "SAYGOODBYE_ANSWER":
                        msgType = SAYGOODBYE_ANSWER;
                        break;
                    case "HARNESSREINDEERS_ANSWER":
                        msgType = HARNESSREINDEERS_ANSWER;
                        break;
                    case "TRAVELAROUND_ANSWER":
                        msgType = TRAVELAROUND_ANSWER;
                        break;
                    case "GOHOME_ANSWER":
                        msgType = GOHOME_ANSWER;
                        break;
                    case "ENDTRIP_ANSWER":
                        msgType = ENDTRIP_ANSWER;
                        break;
                    case "ATWORK_ANSWER":
                        msgType = ATWORK_ANSWER;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "GOBACKWORK_ANSWER":
                        msgType = GOBACKWORK_ANSWER;
                        elfID = Integer.parseInt(root.getAttribute("ElfID"));
                        break;
                    case "NEEDADVICE_ANSWER":
                        msgType = NEEDADVICE_ANSWER;
                        break;
                    case "ENTERTHEHOUSE_ANSWER":
                        msgType = ENTERTHEHOUSE_ANSWER;
                        break;
                    case "TALK_ANSWER":
                        msgType = TALK_ANSWER;
                        break;
                    case "ENJOYHOLIDAYS_ANSWER":
                        msgType = ENJOYHOLIDAYS_ANSWER;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        break;
                    case "GOBACKSTABLE_ANSWER":
                        msgType = GOBACKSTABLE_ANSWER;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        break;
                    case "KNOCKNOCK_ANSWER":
                        msgType = KNOCKNOCK_ANSWER;
                        break;
                    case "GROUPATSLEDGE_ANSWER":
                        msgType = GROUPATSLEDGE_ANSWER;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        break;
                    case "FOLLOWSANTA_ANSWER":
                        msgType = FOLLOWSANTA_ANSWER;
                        reindeerID = Integer.parseInt(root.getAttribute("ReindeerID"));
                        break;
                    case "WRITELFSTATE_ANSWER":
                        msgType = WRITELFSTATE_ANSWER;
                        break;
                    case "WRITEREINDEERSTATE_ANSWER":
                        msgType = WRITEREINDEERSTATE_ANSWER;
                        break;
                    case "WRITESANTASTATE_ANSWER":
                        msgType = WRITESANTASTATE_ANSWER;
                        break;
                    case "REINDEERSATSTABLE_ANSWER":
                        msgType = REINDEERSATSTABLE_ANSWER;
                        break;
                    case "SETREINDEERREADY_ANSWER":
                        msgType = SETREINDEERREADY_ANSWER;
                        break;
                    case "nELVESINGROUP_ANSWER":
                        msgType = nELVESINGROUP_ANSWER;
                        break;
                    case "ELVESKNOCKED_ANSWER":
                        msgType = ELVESKNOCKED_ANSWER;
                        break;
                    case "QUEUEADD_ANSWER":
                        msgType = QUEUEADD_ANSWER;
                        break;
                    case "QUEUEMPTY_ANSWER":
                        msgType = QUEUEMPTY_ANSWER;
                        break;
                    case "SETENDREINDEEROPERATION_ANSWER":
                        msgType = SETENDREINDEEROPERATION_ANSWER;
                        break;
                    case "ENDREINDEEROPERATION_ANSWER":
                        msgType = ENDREINDEEROPERATION_ANSWER;
                        toggle = Boolean.parseBoolean(root.getAttribute("Toggle"));
                        break;
                    case "SETENDELFOPERATION_ANSWER":
                        msgType = SETENDELFOPERATION_ANSWER;
                        break;
                    case "ENDELFOPERATIONs_ANSWER":
                        msgType = ENDELFOPERATIONs_ANSWER;
                        toggle = Boolean.parseBoolean(root.getAttribute("Toggle"));
                        break;
                    case "ENDELFSOPERATION_ANSWER":
                        msgType = ENDELFSOPERATION_ANSWER;
                        toggle = Boolean.parseBoolean(root.getAttribute("Toggle"));
                        break;
                    case "GETHEADER_ANSWER":
                        msgType = GETHEADER_ANSWER;
                        break;
                    case "SHUTDOWN_ANSWER":
                        msgType = SHUTDOWN_ANSWER;
                        break;
                    default: // ERRO
                        System.err.println(Thread.currentThread().getName() + " Undefined type2.");
                        System.exit(1);
                        break;
                }
                break;
            default:  // ERRO
                System.err.println(Thread.currentThread().getName() + " error in root element.");
                System.exit(1);
                break;
        }
    }

    public String toXML() {
        String xml = "";
        String strType = "";

        switch (getMsgType()) {
            //Santa
            case RESTING:
            case OPENTHEDOOR:
            case INVITEIN:
            case LISTENELVES:
            case SAYGOODBYE:
            case HARNESSREINDEERS:
            case TRAVELAROUND:
            case GOHOME:
            case ENDTRIP:
            //Elfs
            case ATWORK:
            case GOBACKWORK:
            case NEEDADVICE:
            case ENTERTHEHOUSE:
            case TALK:
            //Reindeers
            case ENJOYHOLIDAYS:
            case GOBACKSTABLE:
            case KNOCKNOCK:
            case GROUPATSLEDGE:
            case FOLLOWSANTA:
            //Logger_states
            case WRITELFSTATE:
            case WRITEREINDEERSTATE:
            case WRITESANTASTATE:
            //Logger_Elves
            case nELVESINGROUP:
            case ELVESKNOCKED:
            case QUEUEADD:
            case QUEUEMPTY:
            //Logger_Reindeers
            case REINDEERSATSTABLE:
            case SETREINDEERREADY:
            //Logger_StopConditions
            case SETENDREINDEEROPERATION:
            case ENDREINDEEROPERATION:
            case SETENDELFOPERATION:
            case ENDELFOPERATIONs:
            case ENDELFSOPERATION:
            //Logger_Other
            case GETHEADER:
            case SHUTDOWN:
                xml = "<Request ";
                break;
            case RESTING_ANSWER:
            case OPENTHEDOOR_ANSWER:
            case INVITEIN_ANSWER:
            case LISTENELVES_ANSWER:
            case SAYGOODBYE_ANSWER:
            case HARNESSREINDEERS_ANSWER:
            case TRAVELAROUND_ANSWER:
            case GOHOME_ANSWER:
            case ENDTRIP_ANSWER:
            //Elfs
            case ATWORK_ANSWER:
            case GOBACKWORK_ANSWER:
            case NEEDADVICE_ANSWER:
            case ENTERTHEHOUSE_ANSWER:
            case TALK_ANSWER:
            //Reindeers
            case ENJOYHOLIDAYS_ANSWER:
            case GOBACKSTABLE_ANSWER:
            case KNOCKNOCK_ANSWER:
            case GROUPATSLEDGE_ANSWER:
            case FOLLOWSANTA_ANSWER:
            //Logger_states
            case WRITELFSTATE_ANSWER:
            case WRITEREINDEERSTATE_ANSWER:
            case WRITESANTASTATE_ANSWER:
            //Logger_Elves
            case nELVESINGROUP_ANSWER:
            case ELVESKNOCKED_ANSWER:
            case QUEUEADD_ANSWER:
            case QUEUEMPTY_ANSWER:
            //Logger_Reindeers
            case REINDEERSATSTABLE_ANSWER:
            case SETREINDEERREADY_ANSWER:
            //Logger_StopConditions
            case SETENDREINDEEROPERATION_ANSWER:
            case ENDREINDEEROPERATION_ANSWER:
            case SETENDELFOPERATION_ANSWER:
            case ENDELFOPERATIONs_ANSWER:
            case ENDELFSOPERATION_ANSWER:
            //Logger_Other
            case GETHEADER_ANSWER:
            case SHUTDOWN_ANSWER:
                xml = "<Reply ";
                break;
            default: //ERRO
                System.err.println("ERRO");
                System.exit(1);
                break;
        }

        switch (getMsgType()) {
            case RESTING:
                strType = "RESTING";
                break;
            case RESTING_ANSWER:
                strType = "RESTING_ANSWER";
                break;
            case OPENTHEDOOR:
                strType = "OPENTHEDOOR";
                break;
            case OPENTHEDOOR_ANSWER:
                strType = "OPENTHEDOOR_ANSWER";
                break;
            case INVITEIN:
                strType = "INVITEIN";
                break;
            case INVITEIN_ANSWER:
                strType = "INVITEIN_ANSWER";
                break;
            case LISTENELVES:
                strType = "LISTENELVES";
                break;
            case LISTENELVES_ANSWER:
                strType = "LISTENELVES_ANSWER";
                break;
            case SAYGOODBYE:
                strType = "SAYGOODBYE";
                break;
            case SAYGOODBYE_ANSWER:
                strType = "SAYGOODBYE_ANSWER";
                break;
            case HARNESSREINDEERS:
                strType = "HARNESSREINDEERS";
                break;
            case HARNESSREINDEERS_ANSWER:
                strType = "HARNESSREINDEERS_ANSWER";
                break;
            case TRAVELAROUND:
                strType = "TRAVELAROUND";
                break;
            case TRAVELAROUND_ANSWER:
                strType = "TRAVELAROUND_ANSWER";
                break;
            case GOHOME:
                strType = "GOHOME";
                break;
            case GOHOME_ANSWER:
                strType = "GOHOME_ANSWER";
                break;
            case ENDTRIP:
                strType = "ENDTRIP";
                break;
            case ENDTRIP_ANSWER:
                strType = "ENDTRIP_ANSWER";
                break;
            case ATWORK:
                strType = "ATWORK";
                break;
            case ATWORK_ANSWER:
                strType = "ATWORK_ANSWER";
                break;
            case GOBACKWORK:
                strType = "GOBACKWORK";
                break;
            case GOBACKWORK_ANSWER:
                strType = "GOBACKWORK_ANSWER";
                break;
            case NEEDADVICE:
                strType = "NEEDADVICE";
                break;
            case NEEDADVICE_ANSWER:
                strType = "NEEDADVICE_ANSWER";
                break;
            case ENTERTHEHOUSE:
                strType = "ENTERTHEHOUSE";
                break;
            case ENTERTHEHOUSE_ANSWER:
                strType = "ENTERTHEHOUSE_ANSWER";
                break;
            case TALK:
                strType = "TALK";
                break;
            case TALK_ANSWER:
                strType = "TALK_ANSWER";
                break;
            case ENJOYHOLIDAYS:
                strType = "ENJOYHOLIDAYS";
                break;
            case ENJOYHOLIDAYS_ANSWER:
                strType = "ENJOYHOLIDAYS_ANSWER";
                break;
            case GOBACKSTABLE:
                strType = "GOBACKSTABLE";
                break;
            case GOBACKSTABLE_ANSWER:
                strType = "GOBACKSTABLE_ANSWER";
                break;
            case KNOCKNOCK:
                strType = "KNOCKNOCK";
                break;
            case KNOCKNOCK_ANSWER:
                strType = "KNOCKNOCK_ANSWER";
                break;
            case GROUPATSLEDGE:
                strType = "GROUPATSLEDGE";
                break;
            case GROUPATSLEDGE_ANSWER:
                strType = "GROUPATSLEDGE_ANSWER";
                break;
            case FOLLOWSANTA:
                strType = "FOLLOWSANTA";
                break;
            case FOLLOWSANTA_ANSWER:
                strType = "FOLLOWSANTA_ANSWER";
                break;
            case WRITELFSTATE:
                strType = "WRITELFSTATE";
                break;
            case WRITELFSTATE_ANSWER:
                strType = "WRITELFSTATE_ANSWER";
                break;
            case WRITEREINDEERSTATE:
                strType = "WRITEREINDEERSTATE";
                break;
            case WRITEREINDEERSTATE_ANSWER:
                strType = "WRITEREINDEERSTATE_ANSWER";
                break;
            case WRITESANTASTATE:
                strType = "WRITESANTASTATE";
                break;
            case WRITESANTASTATE_ANSWER:
                strType = "WRITESANTASTATE_ANSWER";
                break;
            case REINDEERSATSTABLE:
                strType = "REINDEERSATSTABLE";
                break;
            case REINDEERSATSTABLE_ANSWER:
                strType = "REINDEERSATSTABLE_ANSWER";
                break;
            case SETREINDEERREADY:
                strType = "SETREINDEERREADY";
                break;
            case SETREINDEERREADY_ANSWER:
                strType = "SETREINDEERREADY_ANSWER";
                break;
            case nELVESINGROUP:
                strType = "nELVESINGROUP";
                break;
            case nELVESINGROUP_ANSWER:
                strType = "nELVESINGROUP_ANSWER";
                break;
            case ELVESKNOCKED:
                strType = "ELVESKNOCKED";
                break;
            case ELVESKNOCKED_ANSWER:
                strType = "ELVESKNOCKED_ANSWER";
                break;
            case QUEUEADD:
                strType = "QUEUEADD";
                break;
            case QUEUEADD_ANSWER:
                strType = "QUEUEADD_ANSWER";
                break;
            case QUEUEMPTY:
                strType = "QUEUEMPTY";
                break;
            case QUEUEMPTY_ANSWER:
                strType = "QUEUEMPTY_ANSWER";
                break;
            case SETENDREINDEEROPERATION:
                strType = "SETENDREINDEEROPERATION";
                break;
            case SETENDREINDEEROPERATION_ANSWER:
                strType = "SETENDREINDEEROPERATION_ANSWER";
                break;
            case ENDREINDEEROPERATION:
                strType = "ENDREINDEEROPERATION";
                break;
            case ENDREINDEEROPERATION_ANSWER:
                strType = "ENDREINDEEROPERATION_ANSWER";
                break;
            case SETENDELFOPERATION:
                strType = "SETENDELFOPERATION";
                break;
            case SETENDELFOPERATION_ANSWER:
                strType = "SETENDELFOPERATION_ANSWER";
                break;
            case ENDELFOPERATIONs:
                strType = "ENDELFOPERATIONs";
                break;
            case ENDELFOPERATIONs_ANSWER:
                strType = "ENDELFOPERATIONs_ANSWER";
                break;
            case ENDELFSOPERATION:
                strType = "ENDELFSOPERATION";
                break;
            case ENDELFSOPERATION_ANSWER:
                strType = "ENDELFSOPERATION_ANSWER";
                break;
            case GETHEADER:
                strType = "GETHEADER";
                break;
            case GETHEADER_ANSWER:
                strType = "GETHEADER_ANSWER";
                break;
            case SHUTDOWN:
                strType = "SHUTDOWN";
                break;
            case SHUTDOWN_ANSWER:
                strType = "SHUTDOWN_ANSWER";
                break;
        }

        xml = xml + "type=\"" + strType + "\" ";

        switch (getMsgType()) {    // add ElfID attribute
            case ATWORK:
            case ATWORK_ANSWER:
            case GOBACKWORK:
            case GOBACKWORK_ANSWER:
            case NEEDADVICE:
            case NEEDADVICE_ANSWER:
            case ENTERTHEHOUSE:
            case ENTERTHEHOUSE_ANSWER:
            case TALK:
            case TALK_ANSWER:
            case QUEUEADD:
            case SETENDELFOPERATION:
            case ENDELFOPERATIONs:
                xml = xml + "ElfID=\"" + getElfID() + "\" ";
                System.out.println(xml + "|!!!!!!!!!!!!!!!!");
                break;
        }

        switch (getMsgType()) {    // add toogle attribute
            case INVITEIN:
            case ELVESKNOCKED:
            case SETREINDEERREADY:
            case SETENDREINDEEROPERATION:
            case ENDREINDEEROPERATION_ANSWER:
            case ENDELFOPERATIONs_ANSWER:
            case ENDELFSOPERATION_ANSWER:
                xml = xml + "Toogle=\"" + getToggle() + "\" ";
                System.out.println("ELVES KNOCKED????___________-------------_______________------------_____________--------" + xml + " _message");
                break;
        }
        switch (getMsgType()) {    // add toogle attribute
            case REINDEERSATSTABLE:
            case nELVESINGROUP:
                xml = xml + "IntToogle=\"" + getGroupCounter() + "\" ";
                break;
        }
        switch (getMsgType()) {    // add Santa decision attribute
            case OPENTHEDOOR:
                xml = xml + "Decision=\"" + getSantaDecision() + "\" ";
                break;
        }
        switch (getMsgType()) {    // add Logger state attribute
            case WRITESANTASTATE:
                xml = xml + "State=\"" + getStateInt() + "\" ";
                break;
        }
        switch (getMsgType()) {    // add Logger state attribute
            case WRITEREINDEERSTATE:
                xml = xml + "State=\"" + getStateInt() + "\" ReindeerID=\"" + getReindeerID() + "\" ";
                break;
        }
        switch (getMsgType()) {    // add Logger state attribute
            case WRITELFSTATE:
                xml = xml + "State=\"" + getStateInt() + "\" ElfID=\"" + getElfID() + "\" ";
                break;
        }

        switch (getMsgType()) {    // add ReindeerID attribute
            case FOLLOWSANTA:
            case FOLLOWSANTA_ANSWER:
            case ENJOYHOLIDAYS:
            case ENJOYHOLIDAYS_ANSWER:
            case GOBACKSTABLE:
            case GOBACKSTABLE_ANSWER:
            case GROUPATSLEDGE:
            case GROUPATSLEDGE_ANSWER:
                xml = xml + "ReindeerID=\"" + getReindeerID() + "\" ";
                break;
        }

        xml = xml + "/>";

        return xml;
    }

    // Pois é... //
    public String convertToogle(boolean tg) {
        if (tg) {
            return "true";
        } else {
            return "false";
        }
    }

}
