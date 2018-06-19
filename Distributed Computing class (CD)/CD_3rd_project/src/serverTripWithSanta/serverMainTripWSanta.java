/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverTripWithSanta;

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
public class serverMainTripWSanta {

    public static boolean waitConnection;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        TripWithSanta trip;
        TripWithSantaInterface tripInterface;
        Logger_Stub logStub = new Logger_Stub(InitialData.serverHostNameServerLogger, InitialData.loggerPort);
        ServerCom scon, sconi;
        TripWithSantaProxy tripProxy;

        /* estabelecimento do servico */
        scon = new ServerCom(InitialData.tripPort);           // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        trip = new TripWithSanta(logStub);                           // activação do serviço
        tripInterface = new TripWithSantaInterface(trip);
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();
                if (waitConnection) {// entrada em processo de escuta
                    tripProxy = new TripWithSantaProxy(sconi, tripInterface);    // lançamento do agente prestador do serviço
                    tripProxy.start();
                }
            } catch (SocketException | SocketTimeoutException ex) {
            }
        }
    }
}
