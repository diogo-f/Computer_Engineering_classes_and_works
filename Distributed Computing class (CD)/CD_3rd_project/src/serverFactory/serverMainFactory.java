/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverFactory;

import genclass.GenericIO;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import publicInfo.InitialData;
import publicInfo.ServerCom;
import publicStubs.Logger_Stub;

/**
 *
 * @author diogo
 */
public class serverMainFactory {

    public static boolean waitConnection;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Factory factory;
        FactoryInterface factoryInter;
        Logger_Stub log = new Logger_Stub(InitialData.serverHostNameServerLogger, InitialData.loggerPort);
        ServerCom scon, sconi;
        FactoryProxy factoryProxy;

        /* estabelecimento do servico */
        scon = new ServerCom(InitialData.factoryPort);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        factory = new Factory(log);                           // activação do serviço
        factoryInter = new FactoryInterface(factory);
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();                            // entrada em processo de escuta
                if (waitConnection) {
                    factoryProxy = new FactoryProxy(sconi, factoryInter);    // lançamento do agente prestador do serviço
                    factoryProxy.start();
                }
            } catch (SocketException ex) {
            } catch (SocketTimeoutException ex) {
            }
        }
    }
}
