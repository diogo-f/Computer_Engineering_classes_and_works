/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_06;

import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class Ex2 {

    static Scanner sc = new Scanner(System.in);
    static Academia acd = new Academia();

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
                    inscreverAluno();break;
                case 2:
                    inscreverProf();break;
                case 3:
                    inserirDisc();break;
            }

        } while (m != 6);
    }

    public static int menu() {
        int n = 0;
        System.out.println("Menu:");
        System.out.println("1 ‐ inscrever aluno/aluno graduado");
        System.out.println("2 - inscrever professor");
        System.out.println("3 - criar dsciplina");
        System.out.println("4 - inscrever aluno na disciplina");
        System.out.println("5 - imprimir informação de uma disciplina");
        System.out.println("6 - terminar programa");
        System.out.print("opção: ");
        n = sc.nextInt();
        return n;
    }

    public static void inscreverAluno() {
        System.out.println("Escolha:\n1- inscrever aluno\n2- inscrever aluno pos-graduado");
        int t = sc.nextInt();
        sc.nextLine();
        System.out.println("Nome do aluno: ");
        String nome = sc.nextLine();
        System.out.println("cc do aluno: ");
        int cc = sc.nextInt();
        System.out.println("Data de nascimento: \nex.: 1 1 2000");
        int dia = sc.nextInt();
        int mes = sc.nextInt();
        int ano = sc.nextInt();
        Data date = new Data(dia, mes, ano);
        if (t == 1) {
            System.out.println("Bolseiro? \n1-sim\n2-não");
            if (sc.nextInt() == 1) {
                System.out.println("Qual é o valor da bolsa: ");
                acd.insert(new Bolseiro(nome, cc, date, sc.nextInt()));
            } else {
                acd.insert((new Aluno(nome, cc, date)));
            }
        }
    }

    public static void inscreverProf() {
        sc.nextLine();
        System.out.println("Nome do Professor: ");
        String nome = sc.nextLine();
        System.out.println("cc do professor: ");
        int cc = sc.nextInt();
        System.out.println("Data de nascimento: \nex.: 1 1 2000");
        int dia = sc.nextInt();
        int mes = sc.nextInt();
        int ano = sc.nextInt();
        Data date = new Data(dia, mes, ano);
        sc.nextLine();
        System.out.println("Area de ensino: ");
        String area = sc.nextLine();
        acd.insertProf(new Professor(nome,cc,date,area));
    }
    
    public static void inserirDisc(){
        int cc = 0;
        sc.nextLine();
        System.out.println("Nome da disciplina: ");
        String nome = sc.nextLine();
        System.out.println("CC do professor responsavel pela disciplina: ");
        while(!acd.discProfessor(cc)){
            System.out.println("professor nao existe\n");
            System.out.println("\nCC do professor responsavel pela disciplina: ");
        }
        acd.insertDisc(nome, cc);
    }
}
