/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverStable;

import commonInfo.InitialData;
import commonInfo.ServerCom;
import genclass.GenericIO;

/**
 *
 * @author diogo
 */
public class ServerMainStable {

    private static final int portNumb = 22675;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Stable stable;
        StableInterface stableInter;
        InitialData data = new InitialData();
        SantaHouse_Stub santaHouseStub;
        Logger_Stub logStub;
        ServerCom scon, sconi;
        StableProxy stableProxy;
        
        
        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        stable = new Stable(null,null,data);                           // activação do serviço
        stableInter = new StableInterface(stable);
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                            // entrada em processo de escuta
            stableProxy = new StableProxy(sconi, stableInter);    // lançamento do agente prestador do serviço
            stableProxy.start();
        }

    }

}
