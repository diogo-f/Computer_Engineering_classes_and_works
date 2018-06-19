/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSantaHouse;

import publicInfo.InitialData;
import publicInfo.ServerCom;
import genclass.GenericIO;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import publicStubs.Logger_Stub;

/**
 *
 * @author diogo
 */
public class SantaHouseMainServer {

    public static boolean waitConnection;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        SantaHouse house;
        SantaHouseInterface houseInterface;
        Logger_Stub log = new Logger_Stub(InitialData.serverHostNameServerLogger, InitialData.loggerPort);
        ServerCom scon, sconi;
        SantaHouseProxy houseProxy;

        /* estabelecimento do servico */
        scon = new ServerCom(InitialData.housePort);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        house = new SantaHouse(log);                           // activação do serviço
        houseInterface = new SantaHouseInterface(house);
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();
                if (waitConnection) {// entrada em processo de escuta
                    houseProxy = new SantaHouseProxy(sconi, houseInterface);    // lançamento do agente prestador do serviço
                    houseProxy.start();
                }
            } catch (SocketException ex) {
            } catch (SocketTimeoutException ex) {
            }
        }
    }
}
