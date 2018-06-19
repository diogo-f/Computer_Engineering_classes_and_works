/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula02_VideoClube;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class VideoclubeMain {

    private static final Scanner sc = new Scanner(System.in);
    private static VideoclubeDATA vcData;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        vcDataInit();
        menuPrincipal();
    }

    public static void vcDataInit() {
        try {
            System.out.println("Numero maximo de emprestimos por cliente: ");
            vcData = new VideoclubeDATA(sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Invalido");
            sc.next();
        }
    }

    /**
     * Menu principal
     */
    public static void menuPrincipal() {

        int menuOption;
        try {
            do {
                menuOption = showMenuPrincipal();

                switch (menuOption) {
                    case 1:
                        menuCliente();
                        break;
                    case 2:
                        menuFilme();
                        break;
                    case 3:
                        menuEmprestimo();
                        break;
                    case 4:
                        System.out.println("Fechar programa");
                        break;
                    default:
                        System.out.println("opção invalida");
                }
            } while (menuOption != 4);
        } catch (InputMismatchException ex) {
            System.out.println("opção invalida");
            sc.next();
        }
    }

    /**
     *
     * @return
     */
    public static int showMenuPrincipal() {
        int option;
        System.out.println("\n|------------------|");
        System.out.println("|--Menu principal--|");
        System.out.println("|------------------|");
        System.out.println("1 - Menu de clientes");
        System.out.println("2 - Menu de filmes");
        System.out.println("3 - Menu de emprestimos");
        System.out.println("4 - Fechar programa");
        System.out.println("Opção: ");
        option = sc.nextInt();
        return option;
    }

    /**
     * Menu de cliente
     */
    public static void menuCliente() {
        int menuOption;
        try {
            do {
                menuOption = showMenuCliente();

                switch (menuOption) {
                    case 1:
                        vcData.showClientes();
                        break;
                    case 2:
                        vcData.showClientesID();
                        break;
                    case 3:
                        menuOption = vcData.addCliente();
                        break;
                    case 4:
                        vcData.getHistoricoCliente(vcData.askID());
                        break;

                    case 5:
                        vcData.getEmprestimosAtivosCliente(vcData.askID());
                        break;
                    case 6:
                            vcData.deleteCliente(vcData.askID());
                        break;
                    default:
                        System.out.println("opção invalida");
                }
            } while (menuOption != 7);
        } catch (InputMismatchException ex) {
            System.out.println("opção invalida");
            sc.next();
        }
    }

    /**
     *
     * @return
     */
    public static int showMenuCliente() {
        int option;
        System.out.println("\n|--Menu Clientes--|\n");
        System.out.println("1 - Ver lista de clientes");
        System.out.println("2 - Ver IDs de clientes");
        System.out.println("3 - Adicionar cliente");
        System.out.println("4 - Historico de filmes de um cliente (ID)");
        System.out.println("5 - Emprestimos ativos com o cliente (ID)");
        System.out.println("6 - Remover cliente (ID)");
        System.out.println("7 - Menu principal");

        System.out.println("Opção: ");
        option = sc.nextInt();
        return option;
    }

    /**
     * Menu de filmes
     */
    public static void menuFilme() {
        int menuOption;
        try {
            do {
                menuOption = showMenuFilme();

                switch (menuOption) {
                    case 1:
                        vcData.showFilmes();
                        break;
                    case 2:
                        vcData.showFilmesID();
                        break;
                    case 3:
                        vcData.showFilmesRating();
                        break;
                    case 4:
                        menuOption = vcData.addFilme();
                        break;
                    case 5:
                        vcData.showFilmesDisp();
                        break;
                    case 6:
                            vcData.getRatings(vcData.askID());
                        break;
                    case 7:
                            vcData.getFilmeEmprestimos(vcData.askID());
                        break;
                    case 8:
                        menuEmprestimo();
                        break;
                    default:
                        System.out.println("opção invalida");
                }
            } while (menuOption != 9);
        } catch (InputMismatchException ex) {
            System.out.println("opção invalida");
            sc.next();
        }
    }

    /**
     *
     * @return
     */
    public static int showMenuFilme() {
        int option;
        System.out.println("\n|--Menu Filmes--|\n");
        System.out.println("1 - Ver lista de filmes");
        System.out.println("2 - Ver IDs dos filmes ");
        System.out.println("3 - Ver lista de filmes por rating...");
        System.out.println("4 - Adicionar filme");
        System.out.println("5 - Filmes disponiveis");
        System.out.println("6 - Rating de um filme (ID)");
        System.out.println("7 - Historico de emprestimos de um filme (ID)");
        System.out.println("8 - Menu emprestimo");
        System.out.println("9 - Menu principal");
        System.out.println("Opção: ");
        option = sc.nextInt();
        return option;
    }

    /**
     * Menu de emprestimos
     */
    public static void menuEmprestimo() {
        int idFilTemp;
        int idCliTemp;
        int menuOption;
        try {
            do {
                menuOption = showMenuEmprestimo();

                switch (menuOption) {
                    case 1:
                        try {
                            System.out.println("ID filme: ");
                            idFilTemp = sc.nextInt();
                            System.out.println("numero de socio do cliente: ");
                            idCliTemp = sc.nextInt();
                            vcData.checkOutEmprestimo(idFilTemp, idCliTemp);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalido");
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("ID filme: ");
                            idFilTemp = sc.nextInt();
                            System.out.println("numero de socio do cliente: ");
                            idCliTemp = sc.nextInt();
                            vcData.checkInEmprestimo(idFilTemp, idCliTemp);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalido");
                        }
                        break;
                    case 3:
                        vcData.getTodosEmprestimos();
                        break;
                    default:
                        System.out.println("opção invalida");
                }
            } while (menuOption != 4);
        } catch (InputMismatchException ex) {
            System.out.println("opção invalida");
            sc.next();
        }
    }

    /**
     *
     * @return
     */
    public static int showMenuEmprestimo() {
        int option;
        System.out.println("\n|--Menu Emprestimos--|\n");
        System.out.println("1 - checkOut -> Criar emprestimo");
        System.out.println("2 - CheckIn  -> Devolução de emprestimo");
        System.out.println("3 - Ver todos os emprestimos");
        System.out.println("4 - MENU anterior");

        System.out.println("Opção: ");
        option = sc.nextInt();
        return option;
    }
}
