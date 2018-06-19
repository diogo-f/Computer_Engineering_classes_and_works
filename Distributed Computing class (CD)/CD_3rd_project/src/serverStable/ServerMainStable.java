/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverStable;

import publicInfo.InitialData;
import publicInfo.ServerCom;
import genclass.GenericIO;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import publicStubs.Logger_Stub;
import publicStubs.SantaHouse_Stub;

/**
 *
 * @author diogo
 */
public class ServerMainStable {

    public static boolean waitConnection;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Stable stable;
        StableInterface stableInter;
        SantaHouse_Stub santaHouseStub = new SantaHouse_Stub(InitialData.serverHostNameServerSantaHouse, InitialData.housePort);
        Logger_Stub log = new Logger_Stub(InitialData.serverHostNameServerStable, InitialData.loggerPort);
        ServerCom scon, sconi;
        StableProxy stableProxy;

        /* estabelecimento do servico */
        scon = new ServerCom(InitialData.stablePort);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        stable = new Stable(log, santaHouseStub);                           // activação do serviço
        stableInter = new StableInterface(stable/*, santaHouseStub*/);
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();
                if (waitConnection) {// entrada em processo de escuta
                    stableProxy = new StableProxy(sconi, stableInter);    // lançamento do agente prestador do serviço
                    stableProxy.start();
                }
            } catch (SocketException ex) {
            } catch (SocketTimeoutException ex) {
            }
        }
    }
}
