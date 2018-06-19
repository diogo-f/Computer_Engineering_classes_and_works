/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula02_VideoClube;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author diogo
 */
public class Cliente {

    private static int count = 1;
    private int clienteID;
    private final Date dataInsc;
    private Estudante estudante;
    private Funcionario funcionario;
    private TipoCliente tipoCliente;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//    private lista de videos!! uma para historico, e uma para os ativos(emprestados)
    private final ArrayList<Integer> historicoEmprestimos;
    private final ArrayList<Integer> emprestimosAtivos;
    private int numMaxEmprestimos;

    /**
     * 
     *construtor de estudante
     * @param nome
     * @param cc
     * @param dataNasc
     * @param curso
     * @param numMec
     * @param maxEmp
     */
    public Cliente(String nome, int cc, Date dataNasc, String curso, int numMec, int maxEmp) {
        estudante = new Estudante(nome, cc, dataNasc, curso, numMec);
        tipoCliente = tipoCliente.ESTUDANTE;
        setClienteID(count++);
        this.dataInsc = new Date();
        this.numMaxEmprestimos = maxEmp;
        historicoEmprestimos = new ArrayList<>();
        emprestimosAtivos = new ArrayList<>();
    }

    /**
     * 
     *contrutor de funcionario
     * @param nome
     * @param cc
     * @param dataNasc
     * @param numFunc
     * @param nif
     * @param maxEmp
     */
    public Cliente(String nome, int cc, Date dataNasc, int numFunc, int nif, int maxEmp) {
        funcionario = new Funcionario(nome, cc, dataNasc, numFunc, nif);
        tipoCliente = tipoCliente.FUNCIONARIO;
        setClienteID(count++);
        this.dataInsc = new Date();
        this.numMaxEmprestimos = maxEmp;
        historicoEmprestimos = new ArrayList<>();
        emprestimosAtivos = new ArrayList<>();
    }

    /**
     *
     * @param clienteID
     */
    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    /**
     *
     * @return
     */
    public int getClienteID() {
        return clienteID;
    }

    /**
     *
     * @return
     */
    public String getNomeCliente() {
        if (tipoCliente.equals(tipoCliente.ESTUDANTE)) {
            return estudante.getNome();
        }
        if (tipoCliente.equals(tipoCliente.FUNCIONARIO)) {
            return funcionario.getNome();
        } else {
            return "nome ---";
        }
    }

    /**
     *
     * @param idFilme
     * @return
     */
    public boolean addEmprestimo(int idFilme) {
        if (emprestimosAtivos.size() <= numMaxEmprestimos) {
            emprestimosAtivos.add(idFilme);
            historicoEmprestimos.add(idFilme);
            return false;
        } else {
            System.out.println("Cliente ID-Nome: " + clienteID + " - " + getNomeCliente() + " chegou ao limite de emprestimos");
            return true;
        }
    }

    /**
     *
     * @param idFilme
     */
    public void remEmprestimo(int idFilme) {
        for (int i = 0; i < emprestimosAtivos.size(); i++) {
            if (emprestimosAtivos.get(i) == idFilme) {
                emprestimosAtivos.remove(i);
            }
            else{
                System.out.println("Não encontrado");
            }
        }
    }

    /**
     * historico de emprestimos deste cliente
     */
    public void getEmprestimosHistorico() {
        System.out.println("Historico de emprestimos de " + getNomeCliente());
        for (int idFilme : historicoEmprestimos) {
            System.out.println("ID filme: " + idFilme);
        }
    }

    /**
     * Emprestimos ativos deste cliente
     */
    public void getEmprestimosAtivos() {
        System.out.println("Emprestimos ativos de " + getNomeCliente() + " | " + emprestimosAtivos.size() + " de " + numMaxEmprestimos);
        for (int idFilme : emprestimosAtivos) {
            System.out.println("ID filme: " + idFilme);
        }
    }

    /**
     *
     * @return
     */
    public String toStringIDnome() {
        if (tipoCliente.equals(tipoCliente.ESTUDANTE)) {
            return "-Estudante   | " + clienteID + " | " + estudante.getNome();
        } else {
            return "-Funcionario | " + clienteID + " | " + funcionario.getNome();
        }
    }

    @Override
    public String toString() {
        if (tipoCliente.equals(tipoCliente.ESTUDANTE)) {
            return "\n---Cliente---" + "\n--Estudante--\n" + "numero socio: " + clienteID + "\nData Insc: " + dateFormat.format(dataInsc) + estudante.toString();
        } else {
            return "\n----Cliente----" + "\n--Funcionario--\n" + "numero socio: " + clienteID + "\nData Insc: " + dateFormat.format(dataInsc) + funcionario.toString();
        }
    }

    /**
     * Tipos de cliente: ESTUDANTE FUNCIONARIO
     */
    public enum TipoCliente {

        /**
         *
         */
        ESTUDANTE,
        /**
         *
         */
        FUNCIONARIO;
    }

}
