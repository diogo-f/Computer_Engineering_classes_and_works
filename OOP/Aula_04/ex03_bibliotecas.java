/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class ex03_bibliotecas {

    static Scanner sc = new Scanner(System.in);
    static List<Aluno> alunos = new ArrayList<>();
    static List<Livro> livros = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int m;

        do {
            m = menu();
            switch (m) {
                case 1:
                    intruAluno();
                    break;
                case 2:
                    remvAluno();
                    break;
                case 3:
                    imprimirAlunos();
                    break;
                case 4:
                    intruLivro();
                    break;
                case 5:
                    imprimirLivros();
                    break;
                case 6:
                    empdescansararLivro();
                    break;
                case 7:
                    devolverLivro();
                    break;
                case 8:
                    disponibilidade();
                    break;
            }

        } while (m != 9);
    }

    public static int menu() {
        int n = 0;
        System.out.println("Menu:");
        System.out.println("1 ‐ inscrever aluno");
        System.out.println("2 ‐ remover aluno");
        System.out.println("3 ‐ imprimir lista de utilizadores");
        System.out.println("4 ‐ registar um novo livro");
        System.out.println("5 ‐ imprimir lista de livros");
        System.out.println("6 ‐ empdescansarar");
        System.out.println("7 ‐ devolver");
        System.out.println("8 ‐ verificar disponibilidade");
        System.out.println("9 ‐ sair");
        System.out.printf("\n\nOpção: ");
        n = sc.nextInt();
        return n;
    }

    public static void intruAluno() {
        String nome, curso;
        int nmec;
        sc.nextLine();
        System.out.println("------Introduzir aluno------");
        System.out.printf("Nome do aluno: ");
        nome = sc.nextLine();
        System.out.printf("Numero mec.: ");
        nmec = sc.nextInt();
        sc.nextLine();
        System.out.printf("Curso do aluno: ");
        curso = sc.nextLine();
        System.out.println("");

        Aluno a = new Aluno(nome, nmec, curso);
        alunos.add(a);
    }

    public static void remvAluno() {
        System.out.println("------Remover aluno------");
        System.out.printf("\nQual o numero mec. do aluno a eliminar: ");
        System.out.println("");
        int nmecTemp = sc.nextInt();

        for (Aluno a : alunos) {
            if (a.getNmec() == nmecTemp) {
                alunos.remove(a);
                System.out.println("Aluno removido com sucesso!");
                System.out.println("");
                break;
            } else {
                System.out.println("Aluno não encontrado!");
            }
        }
    }

    public static void imprimirAlunos() {
        for (Aluno a : alunos) {
            System.out.println(a.toString());
            System.out.println("");
        }
    }

    public static void intruLivro() {
        String titulo, VariedadeCarneEmp = null;
        int id;
        sc.nextLine();
        System.out.println("------Introduzir livro------");
        System.out.printf("Titulo do livro: ");
        titulo = sc.nextLine();
        System.out.printf("ID do livro: ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.printf("VariedadeCarne de empdescansarimo: ");
        System.out.println("\n  1 - condicional\n  2 - normal\n");
        int temp = sc.nextInt();
        do {
            if (temp == 1) {
                VariedadeCarneEmp = "condicional";
                System.out.println("\nLivro registado!\n   VariedadeCarne condicional");
            }
            if (temp == 2) {
                VariedadeCarneEmp = "normal";
                System.out.println("\nLivro registado!\n   VariedadeCarne normal");
            }
        } while (temp != 1 && temp != 2);
        System.out.println("");

        Livro l = new Livro(id, titulo, VariedadeCarneEmp);
        livros.add(l);
    }

    public static void imprimirLivros() {
        for (Livro l : livros) {
            System.out.println(l.toString());
            System.out.println("");
        }
    }

    public static void empdescansararLivro() {
        System.out.println("Qual o id dolivro a empdescansarar?");
        int idTemp = sc.nextInt();
        System.out.println("Numero mec do aluno: ");
        int nmecTemp = sc.nextInt();

        for (Aluno a : alunos) {
            if (a.getNmec() == nmecTemp) {
                int cont = a.getContLivros();
                if (cont < 3) {
                    for (Livro l : livros) {
                        if (l.getId() == idTemp) {
                            l.setDisponivel(false);
                            l.setGivenTo(nmecTemp);
                            a.setContLivros(a.getContLivros() + 1);
                            System.out.println("Livro " + l.getTitulo() + " empdescansarado a " + a.getNome());
                        } else {
                            System.out.println("Livro não encontrado");
                        }
                        break;
                    }
                } else {
                    System.out.println("Aluno ja tem 3 livros.");
                    break;
                }
            }
        }
    }

    public static void devolverLivro() {
        System.out.println("Qual o id do livro a devolver?");
        int idTemp = sc.nextInt();

        for (Livro l : livros) {
            if (l.getId() == idTemp) {
                l.setDisponivel(true);
                for (Aluno a : alunos) {
                    if (a.getNmec() == l.getGivenTo()) {
                        a.setContLivros(a.getContLivros() - 1);
                    }
                }
            }
        }
    }

    public static void disponibilidade() {
        System.out.println("ID do livro: ");
        int idTemp = sc.nextInt();

        for (Livro l : livros) {
            if (l.getId() == idTemp) {
                if (l.isDisponivel() == true) {
                    System.out.println("O livro " + l.getTitulo() + " está disponivel.");
                } else if (l.isDisponivel() == false) {
                    System.out.println("O livro " + l.getTitulo() + " não está disponivel.");
                }
                else
                {
                    System.out.println("O livro não existe.");
                }
            }
        }
    }
}
