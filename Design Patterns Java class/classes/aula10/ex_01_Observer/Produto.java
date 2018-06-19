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
public class Produto implements ProdutoInterface_subject {

    private static int count = 1;
    private int idProduto;
    private String descricao;
    private double precoBase;
    private double precoLicitado;
    private Cliente licitador;
    private Produto_State estado;
    //observers
    private List<ClienteInterface_observer> observers;

    public Produto(String descricao, double precoBase, Produto_State estado) {
        setClienteID(count++);
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.estado = estado;
        this.licitador = null;
        this.precoLicitado = this.precoBase;
        observers = new ArrayList<>();
    }

    public void setClienteID(int idProd) {
        this.idProduto = idProd;

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
        notifyObserver();
    }

    public Produto_State getEstado() {
        return estado;
    }

    public void setEstado(Produto_State estado) {
        this.estado = estado;
        if (estado == estado.VENDAS || estado == estado.LEILAO) {
            notifyObserver();
        }
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getLicitacao() {
        return licitador + " - " + precoLicitado;
    }

    public Cliente getLicitador() {
        return licitador;
    }

    public void setLicitacao(Cliente licitador, double precoLic) {
        if (precoLicitado < precoLic) {
            this.licitador = licitador;
            this.precoLicitado = precoLic;
            register(licitador);
            notifyObserver();
        } else {
            System.out.println("Preço licitado deve ser superior a: " + precoLicitado);
        }
    }

    public double getPrecoLicitado() {
        return precoLicitado;
    }

    @Override
    public String toString() {
        if (licitador == null) {
            return "Produto " + "id " + idProduto + "| Descricao: " + descricao + " | preço " + precoBase + "\nestado " + estado + "\n";
        } else {
            return "Produto " + "id " + idProduto + "| Descricao: " + descricao + " | preço " + precoBase + "\nestado " + estado + "\nLicitador: " + licitador.getNome() + "- novo preço:" + precoLicitado + "\n";
        }
    }

    //observer pattern
    @Override
    public void register(ClienteInterface_observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unRegister(ClienteInterface_observer deleteObserver) {
        int index = observers.indexOf(deleteObserver);
        observers.remove(index);
    }

    @Override
    public void notifyObserver() {
        for (ClienteInterface_observer c : observers) {
            c.update(this);
        }
    }
}
