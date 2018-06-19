/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverLogger;

import genclass.GenericIO;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import publicInfo.InitialData;
import publicInfo.ServerCom;

/**
 *
 * @author diogo
 */
public class ServerMainLogger {

    public static boolean waitConnection;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Logger log;
        LoggerInterface logInterface;
        ServerCom scon, sconi;
        LoggerProxy logProxy;

        /* estabelecimento do servico */
        scon = new ServerCom(InitialData.loggerPort);           // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        log = new Logger();                           // activação do serviço
        logInterface = new LoggerInterface(log);
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();
                logProxy = new LoggerProxy(sconi, logInterface);    // lançamento do agente prestador do serviço
                logProxy.start();
            } catch (SocketException ex) {
            } catch (SocketTimeoutException ex) {
            }
        }
    }
}
