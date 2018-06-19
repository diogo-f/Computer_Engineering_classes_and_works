/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula01_WordSearchSolver;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author diogo
 */
public class Puzzle {

    private ArrayList<String> world = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<String> result = new ArrayList<>();
    private int puzSize;
    private boolean wordNotFound;
    private String direcaoPalavra;
    //todas as direções possiveis
    boolean cima = true;
    boolean esq = true;
    boolean baixo = true;
    boolean dir = true;
    boolean cimaDir = true;
    boolean cimaEsq = true;
    boolean baixoDir = true;
    boolean baixoEsq = true;

    public Puzzle(ArrayList world, ArrayList words) {
        this.world = world;
        this.words = words;
    }

    public ArrayList procurarPalavras(int puzSize) {
        this.puzSize = puzSize;
        int wordTempSize;

        //percorre o array das palavras a encontrar
        for (String word : words) {
            wordNotFound = true;
            wordTempSize = word.length();
            //enquanto a palavra não for encontrada vai percorrendo as letras no puzzle até 
            //encontrar uma letra compativel(word.chatAt(0))
            while (wordNotFound) {
                //percorre na vertical todas as linhas
                for (int lin = 0; lin < puzSize; lin++) {
                    //percorre na horizontal todas as colunas
                    for (int col = 0; col < puzSize; col++) {
                        //quando encontrar uma letra que satisfaça:
                        if (Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin).charAt(col))) {
                            //-1-verifica as posições possiveis onde pode procurar o resto da palavra para a letra encontrada
                            verificarDirPossiveis(lin, col, word.length());
                            //-2-chama o metodo com a palavra a procurar e a posição de uma letra inicial igual encontrada
                            procuraDaPalavra(lin, col, word.substring(1));
                        }
                        if (!wordNotFound) {
                            //cria a string com o resultado
                            String resultEnc = StringUtils.rightPad(word, 15) + StringUtils.rightPad(Integer.toString(word.length()), 4) + StringUtils.rightPad(((lin + 1) + "," + (col + 1)), 8) + StringUtils.rightPad(direcaoPalavra, 10);
                            //adiciona o resultado ao array de resultados
                            result.add(resultEnc);
                            break;
                        }
                    }
                    if (!wordNotFound) {
                        break;
                    }
                }
                if (!wordNotFound) {
                    break;
                }
            }
        }
        return result;
    }

    private void verificarDirPossiveis(int lin, int col, int wordLen) {
        //Palavra nova, todas as direções ficam possiveis
        cima = true;
        esq = true;
        baixo = true;
        dir = true;
        cimaDir = true;
        cimaEsq = true;
        baixoDir = true;
        baixoEsq = true;
        //procura quais as direções que não são possiveis
        //cima
        if (wordLen > lin + 1) {
            cima = false;
        }
        //esquerda
        if (wordLen > col + 1) {
            esq = false;
        }
        //baixo
        if (wordLen > (puzSize - lin + 1)) {
            baixo = false;
        }
        //direita
        if (wordLen > (puzSize - col + 1)) {
            dir = false;
        }
        //diag cima direita
        if (!cima || !dir) {
            cimaDir = false;
        }
        //diag cima esquerda
        if (!cima || !esq) {
            cimaEsq = false;
        }
        //diag baixo direita
        if (!baixo || !dir) {
            baixoDir = false;
        }
        //diag baixo esquerda
        if (!baixo || !esq) {
            baixoEsq = false;
        }
//debug
//        System.out.println(cima ? "sim" : "nao");
//        System.out.println(baixo ? "sim" : "nao");
//        System.out.println(dir ? "sim" : "nao");
//        System.out.println(esq ? "sim" : "nao");
//        System.out.println(cimaDir ? "sim" : "nao");
//        System.out.println(cimaEsq ? "sim" : "nao");
//        System.out.println(baixoDir ? "sim" : "nao");
//        System.out.println(baixoEsq ? "sim" : "nao");
    }

    private void procuraDaPalavra(int lin, int col, String word) {
        //procura a palavra só nas direções validas e enquanto não for encontrada
        if (cima && wordNotFound) {
            procuraCima(lin, col, word);
        }
        if (baixo && wordNotFound) {
            procuraBaixo(lin, col, word);
        }
        if (dir && wordNotFound) {
            procuraDir(lin, col, word);
        }
        if (esq && wordNotFound) {
            procuraEsq(lin, col, word);
        }
        if (cimaDir && wordNotFound) {
            procuraCimaDir(lin, col, word);
        }
        if (cimaEsq && wordNotFound) {
            procuraCimaEsq(lin, col, word);
        }
        if (baixoDir && wordNotFound) {
            procuraBaixoDir(lin, col, word);
        }
        if (baixoEsq && wordNotFound) {
            procuraBaixoEsq(lin, col, word);
        }
    }
    
    //estas funções são as de procura, uma para cada direção, talvez podia fazer apenas uma com um switch case e no metodo procuraDaPalavra()
    //passava a direção a procurar. são recursivas
    private void procuraCima(int lin, int col, String word) {
        if (word.length() == 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin - 1).charAt(col))) {
            wordNotFound = false;
            direcaoPalavra = "up";
            return;
        }
        if (word.length() > 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin - 1).charAt(col))) {
            procuraCima(lin - 1, col, word.substring(1));
        }
    }

    private void procuraBaixo(int lin, int col, String word) {
        if (word.length() == 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin + 1).charAt(col))) {
            wordNotFound = false;
            direcaoPalavra = "down";
        }
        if (word.length() > 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin + 1).charAt(col))) {
            procuraBaixo(lin + 1, col, word.substring(1));
        }
    }

    private void procuraDir(int lin, int col, String word) {
        if (word.length() == 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin).charAt(col + 1))) {
            wordNotFound = false;
            direcaoPalavra = "right";
            return;
        }
        if (word.length() > 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin).charAt(col + 1))) {
            procuraDir(lin, col + 1, word.substring(1));
        }
    }

    private void procuraEsq(int lin, int col, String word) {
        if (word.length() == 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin).charAt(col - 1))) {
            wordNotFound = false;
            direcaoPalavra = "left";
            return;
        }
        if (word.length() > 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin).charAt(col - 1))) {
            procuraEsq(lin, col - 1, word.substring(1));
        }
    }

    private void procuraCimaDir(int lin, int col, String word) {
        if (word.length() == 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin - 1).charAt(col + 1))) {
            wordNotFound = false;
            direcaoPalavra = "upRight";
            return;
        }
        if (word.length() > 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin - 1).charAt(col + 1))) {
            procuraCimaDir(lin - 1, col + 1, word.substring(1));
        }
    }

    private void procuraCimaEsq(int lin, int col, String word) {
        if (word.length() == 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin - 1).charAt(col - 1))) {
            wordNotFound = false;
            direcaoPalavra = "upLeft";
            return;
        }
        if (word.length() > 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin - 1).charAt(col - 1))) {
            procuraCimaEsq(lin - 1, col - 1, word.substring(1));
        }
    }

    private void procuraBaixoDir(int lin, int col, String word) {
        if (word.length() == 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin + 1).charAt(col + 1))) {
            wordNotFound = false;
            direcaoPalavra = "downRight";
            return;
        }
        if (word.length() > 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin + 1).charAt(col + 1))) {
            procuraBaixoDir(lin + 1, col + 1, word.substring(1));
        }
    }

    private void procuraBaixoEsq(int lin, int col, String word) {
        if (word.length() == 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin + 1).charAt(col - 1))) {
            wordNotFound = false;
            direcaoPalavra = "downLeft";
            return;
        }
        if (word.length() > 1 && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(world.get(lin + 1).charAt(col - 1))) {
            procuraBaixoEsq(lin + 1, col - 1, word.substring(1));
        }
    }
}
