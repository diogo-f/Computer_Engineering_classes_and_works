/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_06;

import static java.lang.System.arraycopy;

/**
 *
 * @author diogo
 */
public class Academia {

    Aluno aluno[];
    Professor prof[];
    Disciplina disc[];
    private int sizeA;
    private int sizeP;
    private int sizeD;

    public Academia() {
        this.sizeD = 0;
        this.sizeP = 0;
        this.sizeA = 0;
        aluno = new Aluno[10];
        prof = new Professor[10];
        disc = new Disciplina[20];
    }
/////////////////////////aluno///////////////////////////

    public void insert(Aluno n) {
        if (aluno.length == sizeA) {
            increase();
        }
        if (!contains(n)) {
            aluno[sizeA] = n;
            sizeA++;
        }

    }

    public boolean contains(Aluno n) {
        boolean tmp = false;
        for (int i = 0; i < sizeA; i++) {
            if (aluno[i] == n) {
                tmp = true;
                break;
            }
        }
        return tmp;
    }

    public void remove(Aluno n) {
        for (int i = 0; i < aluno.length - 1; i++) {
            if (aluno[i] == n) {
                sizeA--;
                for (int j = i; j < aluno.length - 1; j++) {
                    if (j == sizeA + 1 - 1) {
                        aluno[j] = null;
                    }
                    aluno[j] = aluno[j + 1];
                }
                System.out.println("Removido o elemento " + n);
            }
        }
    }

    public void imprimirAluno() {
        for (Aluno i : aluno) {
            i.toString();
        }
    }

    public void increase() {
        Aluno[] tmp = new Aluno[aluno.length + 10];
        arraycopy(aluno, 0, tmp, 0, aluno.length);
        aluno = tmp;
    }
/////////////////////////professor///////////////////////////

    public void insertProf(Professor n) {
        if (prof.length == sizeP) {
            increase();
        }
        if (!containsProf(n)) {
            prof[sizeP] = n;
            sizeP++;
        }
    }

    public boolean containsProf(Professor n) {
        boolean tmp = false;
        for (int i = 0; i < sizeP; i++) {
            if (prof[i] == n) {
                tmp = true;
                break;
            }
        }
        return tmp;
    }

    public void removeProf(Professor n) {
        for (int i = 0; i < prof.length - 1; i++) {
            if (prof[i] == n) {
                sizeP--;
                for (int j = i; j < prof.length - 1; j++) {
                    if (j == sizeP + 1 - 1) {
                        prof[j] = null;
                        System.out.println("Professor responsavel pela disciplina: ");
                    }
                    prof[j] = prof[j + 1];
                }
                System.out.println("Removido " + n);
            }
        }
    }
/////////////////////////disciplina///////////////////////////

    public void insertDisc(String n, int c) {

        for (int i = 0; i < sizeP; i++) {
            if (prof[i].getCC() == c) {
                Disciplina temp = new Disciplina(n, prof[i]);
                if (!containsDisc(temp)) {
                    disc[sizeD] = temp;
                }
            }
        }
    }

    public boolean containsDisc(Disciplina d) {
        boolean tmp = false;
        for (int i = 0; i < sizeD; i++) {
            if (disc[i] == d) {
                tmp = true;
                break;
            }
        }
        return tmp;
    }

    public boolean discProfessor(int cc) {
        boolean tmp = false;
        for (int i = 0; i < sizeP; i++) {
            if (prof[i].getCC() == cc) {
                tmp = true;
                break;
            }
        }
        return tmp;
    }
}
