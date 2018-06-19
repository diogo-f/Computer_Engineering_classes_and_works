/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_04;

/**
 *
 * @author diogo
 */
public class Aluno {

    private String nome;
    private int nmec;
    private String curso;
    private int contLivros;

    public int getContLivros() {
        return contLivros;
    }

    public void setContLivros(int contLivros) {
        this.contLivros = contLivros;
    }

    public Aluno(String nome, int nmec, String curso) {
        this.nome = nome;
        this.nmec = nmec;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public int getNmec() {
        return nmec;
    }

    public String getCurso() {
        return curso;
    }

    public String toString() {
        return "\nNome: "+getNome()+"\nNmec: "+getNmec()+"\nCurso: "+getCurso();
    }

}
