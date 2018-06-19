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
public class Leilao {

    List<Produto> listLeilao = new ArrayList<>();

    public void addProd(Produto p) {
        p.setEstado(Produto_State.LEILAO);
        listLeilao.add(p);
    }

    public void remProd(Produto p) {
        int index = listLeilao.indexOf(p);
        listLeilao.remove(index);
        if (p.getLicitador() == null) {
            p.setEstado(Produto_State.VENDAS);
        } else {
            p.setEstado(Produto_State.STOCK);
        }
    }
}
