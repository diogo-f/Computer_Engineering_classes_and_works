/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula01_WordSearchSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.StopWatch;

/**
 *
 * @author diogo
 */
public class WordSearchSolver {

    private static ArrayList<String> worldOfWords = new ArrayList<>();
    private static ArrayList<String> wordsToFind = new ArrayList<>();
    private static ArrayList<String> result = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = null;
        if (args.length == 0) {
            fileName = "wordlist_3.txt";
        } else if (args.length == 1) {
            fileName = args[0];
        } else {
            System.out.println("argumentos invalidos");
            System.exit(0);
        }

        //Começa a contar o tempo de execução
        //é sempre contado, não aceita a opção "-timing"
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //passa o nome do ficheiro à função de leitura
        lerFicheiroTXT(fileName);
        //Cria o puzzle com os arrays de palavras do ficheiro
        Puzzle puzzle = new Puzzle(worldOfWords, wordsToFind);
        //chama a função do puzzle "procurarPalavras() e guarda o array de resultados
        result = puzzle.procurarPalavras(worldOfWords.get(0).length());
        //para o relogio e escreve o tempo em segundos
        stopWatch.stop();
        System.out.println("-Elapsed time (secs): " + stopWatch.getTime() / 1000.0);
        //imprime os resultados para todas as palavras
        for (String res : result) {
            System.out.println(res);
        }
    }

    private static void lerFicheiroTXT(String fileName) {
        //le o ficheiro e passa para o scanner
        File wordsFile = new File(fileName);
        Scanner scanFile = null;
        int squareLeng = 81;

        try {
            while (squareLeng > 80) {
                scanFile = new Scanner(wordsFile);
                worldOfWords.add(scanFile.nextLine());
                squareLeng = worldOfWords.get(0).length();
                if (squareLeng > 80) {
                    System.out.println("O tamanho máximo do puzzle é 80x80 e tem de ser quadrado - tem na horizontal " + squareLeng);
                }
            }
            while (scanFile.hasNext()) {
                //adiciona as letras para fazer a matriz ao array worldOfWords
                if (worldOfWords.size() < squareLeng) {
                    worldOfWords.add(scanFile.nextLine());
                    //verifica a cada linha nova se o puzzle continua dentro das regras
                    if (Character.isLowerCase(worldOfWords.get(worldOfWords.size() - 1).charAt(0))) {
                        System.out.println("Puzzle não é quadrado! linha " + worldOfWords.get(worldOfWords.size() - 1));
                        return;
                    }
                } else {
                    //depois de acabar com as letras
                    //vai guardar as palavras para encontrar
                    String[] wordsTemp = new String[]{};
                    wordsTemp = scanFile.nextLine().split("[\\s,]+");
                    if (Character.isLowerCase(wordsTemp[0].charAt(0))) {
                        for (String word : wordsTemp) {
                            if (word.length() < 3 || word.matches(".*[0-9].*")) {
                                System.out.println("Palavras têm de ter 2 caracteres no minimo e não ter numeros - erro: " + word);
                                return;
                            }
                            wordsToFind.add(word);
                        }
                    } else {
                        //Se continuar com letras(maiusculas) para o puzzle então não é quadrado
                        //só passa para as palavras quando acabar com as letras
                        System.out.println("puzzle não é quadrado!");
                        return;
                    }
                }
            }
            System.out.println("Puzzle size: " + squareLeng + "x" + worldOfWords.size());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordSearchSolver.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ficheiro não encontrado!");
        }

    }

}
