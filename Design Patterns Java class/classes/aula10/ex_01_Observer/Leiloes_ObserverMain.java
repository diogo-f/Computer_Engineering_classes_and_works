/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula10.ex_01_Observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
public class Leiloes_ObserverMain {

    public static void main(String[] args) {
        List<Produto> listVendas = new ArrayList<>();
        Leilao leilao = new Leilao();

        Gestor gestor = new Gestor("gestor_1");
        Cliente cliente1 = new Cliente("cliente_11");
        Cliente cliente2 = new Cliente("cliente_22");
        Cliente cliente3 = new Cliente("cliente_33");
        Produto prod1 = new Produto("prod_1", 5, Produto_State.STOCK);
        Produto prod2 = new Produto("prod_2", 10, Produto_State.STOCK);
        Produto prod3 = new Produto("prod_3", 100, Produto_State.STOCK);
        Produto prod4 = new Produto("prod_4", 40, Produto_State.STOCK);
        Produto prod5 = new Produto("prod_5", 1, Produto_State.STOCK);

        prod1.register(gestor);
        prod2.register(gestor);
        prod3.register(gestor);
        prod4.register(gestor);
        prod5.register(gestor);

        leilao.addProd(prod1);
        leilao.addProd(prod4);
        

        prod1.setLicitacao(cliente1, 20);
        prod1.setLicitacao(cliente3, 25);
        leilao.remProd(prod1);
        leilao.remProd(prod4);

    }

}
