/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula02_VideoClube;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class VideoclubeDATA {

    private final Scanner sc;
    private final SimpleDateFormat dateFormat;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Filme> listaFilmes;
    private ArrayList<Filme> listaFilmesRating;
    private final int empMax;
    private boolean needReorderFilmes;

    /**
     *
     * @param emp
     */
    public VideoclubeDATA(int emp) {
        this.listaClientes = new ArrayList<>();
        this.listaFilmes = new ArrayList<>();
        this.sc = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.empMax = emp;
        needReorderFilmes = false;
    }

    /**
     *
     * @return
     */
    public int getEmpMax() {
        return empMax;
    }

    //ask ID
    public int askID() {
        try {
            System.out.println("ID: ");
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ID invalido");
        }
        return 0;
    }

    //Cliente
    /**
     *
     * @param cliente
     */
    public void insertCliente(Cliente cliente) {
        listaClientes.add(cliente);
        System.out.println("Guardado\n" + cliente.toString());
    }

    //Criar cliente
    /**
     *
     * @return
     */
    public int addCliente() {
        String dataStr;

        try {
            ClienteDataTemp dataTemp = new ClienteDataTemp();
            System.out.println("Qual o tipo de cliente?\n1 - Estudante\n2 - Funcionario\n3 - menu anterior");
            dataTemp.setTipoCliente(sc.nextInt());
            if (dataTemp.getTipoCliente() == 3) {
                return 0;
            }
            sc.nextLine();
            System.out.println("--Novo Cliente--");
            System.out.println("Nome: ");
            dataTemp.setNome(sc.nextLine());
            System.out.println("\nCC: ");
            dataTemp.setNumCC(sc.nextInt());
            try {
                System.out.println("\nData nascimento(dd-mm-aaaa): ");
                sc.nextLine();
                dataStr = sc.nextLine();
                dataTemp.setData(dateFormat.parse(dataStr));
                System.out.println(dataTemp.getData() + "\npress enter");
            } catch (ParseException e) {
                System.out.println("Data incorreta: (dd-mm-aaaa)");
            }

            if (dataTemp.getTipoCliente() == 1) {
                //estudante
                System.out.println("\n--info do estudante--");
                sc.nextLine();
                System.out.println("\nCurso do aluno: ");
                dataTemp.setCurso(sc.nextLine());
                System.out.println("N. mecanografico: ");
                dataTemp.setnMec(sc.nextInt());
                Cliente cliente = new Cliente(dataTemp.getNome(), dataTemp.getNumCC(), dataTemp.getData(), dataTemp.getCurso(), dataTemp.getnMec(), getEmpMax());
                insertCliente(cliente);
                return 0;
            } else if (dataTemp.getTipoCliente() == 2) {
                //Funcionario
                System.out.println("\n--info do funcionario--");
                sc.nextLine();
                System.out.println("N. Funcionario: ");
                dataTemp.setnFunc(sc.nextInt());
                System.out.println("NIF: ");
                dataTemp.setNif(sc.nextInt());
                Cliente cliente = new Cliente(dataTemp.getNome(), dataTemp.getNumCC(), dataTemp.getData(), dataTemp.getnFunc(), dataTemp.getNif(), getEmpMax());
                insertCliente(cliente);
                return 0;
            } else {
                return 0;
            }
        } catch (InputMismatchException e) {
            System.out.println("!!!");
        }
        return 0;
    }

    /**
     *
     * @param id
     */
    public void deleteCliente(int id) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getClienteID() == id) {
                System.out.println("a eliminar " + listaClientes.get(i).getNomeCliente());
                listaClientes.remove(i);
            } else {
                System.out.println("Não encontrado");
            }
        }
    }

    /**
     *
     * @param id
     */
    public void getHistoricoCliente(int id) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getClienteID() == id) {
                cliente.getEmprestimosHistorico();
            } else {
                System.out.println("Não encontrado");
            }
        }
    }

    /**
     *
     * @param id
     */
    public void getEmprestimosAtivosCliente(int id) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getClienteID() == id) {
                cliente.getEmprestimosAtivos();
            } else {
                System.out.println("Não encontrado");
            }
        }
    }

    /**
     * Ver todos os clientes
     */
    public void showClientes() {
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente.toString());
        }
    }

    /**
     * Ver todos os IDs dos clientes
     */
    public void showClientesID() {
        System.out.println("\n---Cliente---");
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente.toStringIDnome());
        }
    }

    //Filme
    /**
     *
     * @param filme
     */
    public void insertFilme(Filme filme) {
        listaFilmes.add(filme);
        needReorderFilmes = true;
        System.out.println("Guardado\n" + filme.toString());
    }

    /**
     *
     * @return
     */
    public int addFilme() {
        String titulo;
        int cat;
        int idad;
        boolean correto = false;
        while (!correto) {
            try {
                System.out.println("--Filme--");
                sc.nextLine();
                System.out.println("Titulo: ");
                titulo = sc.nextLine();
                System.out.println("\nCategoria:\n");
                for (EnumCategoria catEnum : EnumCategoria.values()) {
                    System.out.println(catEnum.toString());
                }
                cat = sc.nextInt();
                System.out.println("\nIdade minima:\n");
                for (EnumIdadeMinima idadEnum : EnumIdadeMinima.values()) {
                    System.out.println(idadEnum.toString());
                }
                idad = sc.nextInt();
                Filme filme = new Filme(titulo, cat, idad);
                insertFilme(filme);
                correto = true;
            } catch (InputMismatchException e) {
                System.out.println("!!!");
                correto = false;
            }
        }
        return 9;
    }

    /**
     *
     * @param id
     */
    public void deleteFilme(int id) {
        for (int i = 0; i < listaFilmes.size(); i++) {
            if (listaFilmes.get(i).getFilmeID() == id) {
                System.out.println("a eliminar " + listaFilmes.get(i).toString());
                needReorderFilmes = true;
                listaFilmes.remove(i);
            } else {
                System.out.println("Não encontrado");
            }
        }
    }

    /**
     * Ver todos os filmes
     */
    public void showFilmes() {
        for (Filme filme : listaFilmes) {
            System.out.println(filme.toString());
        }
    }

    /**
     * Ver todos os IDs dos filmes
     */
    public void showFilmesID() {
        System.out.println("---Filmes---\nid");
        for (Filme filme : listaFilmes) {
            System.out.println(filme.toStringIDtitulo());
        }
    }

    /**
     *
     */
    public void showFilmesDisp() {
        for (Filme filme : listaFilmes) {
            if ("sim".equals(filme.getDisponibilidade())) {
                System.out.println(filme.toStringIDtitulo());
            }
        }
    }

    /**
     * Ver lista de filmes por rating(acabar!!)
     */
    public void showFilmesRating() {
        if (!needReorderFilmes) {
            for (Filme filme : listaFilmesRating) {
                System.out.println(filme.toStringIDtitulo());
                System.out.println("...");
            }
        } else {
            System.out.println("...");
        }
    }

    public void getRatings(int id) {
        for (Filme filme : listaFilmes) {
            if (filme.getFilmeID() == id) {
                System.out.println("R medio: " + filme.getRatingMedio() + " | R total" + filme.getRatingTotal());
            }
        }
    }

    /**
     *
     * @param id
     */
    public void getFilmeEmprestimos(int id) {
        for (Filme filme : listaFilmes) {
            if (filme.getFilmeID() == id) {
                filme.getEmprestimos();
            }
        }
    }

    /**
     *
     * @param idFilme
     * @param idCliente
     */
    public boolean checkOutEmprestimo(int idFilme, int idCliente) {
        boolean max = false;
        for (Filme filme : listaFilmes) {
            if (filme.getFilmeID() == idFilme && "sim".equals(filme.getDisponibilidade())) {
                for (Cliente cliente : listaClientes) {
                    if (cliente.getClienteID() == idCliente) {
                        max = cliente.addEmprestimo(idFilme);
                        if (!max) {
                            filme.checkOutEmprestimo(idCliente);
                        }
                    }
                }
            }
        }
        return max;
    }

    /**
     *
     * @param idFilme
     */
    public void checkInEmprestimo(int idFilme, int idCliente) {
        for (Filme filme : listaFilmes) {
            if (filme.getFilmeID() == idFilme) {
                if (filme.checkInEmprestimo()) {
                    for (Cliente cliente : listaClientes) {
                        if (cliente.getClienteID() == idCliente) {
                            cliente.remEmprestimo(idFilme);
                        } else {
                            System.out.println("socio não encontrado");
                        }
                    }
                }
                filme.setRating();
            }
        }
    }

    /**
     * Listar todos os emprestimos
     */
    public void getTodosEmprestimos() {
        for (Filme filme : listaFilmes) {
            filme.getEmprestimos();
        }
    }
}
