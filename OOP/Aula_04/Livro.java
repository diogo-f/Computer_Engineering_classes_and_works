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
public class Livro {

    private int id;
    private String titulo;
    private String VariedadeCarneEmp;
    private boolean disponivel;
    private int givenTo;

    public int getGivenTo() {
        return givenTo;
    }

    public void setGivenTo(int givenTo) {
        this.givenTo = givenTo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getVariedadeCarneEmp() {
        return VariedadeCarneEmp;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Livro(int id, String titulo, String VariedadeCarneEmp) {
        this.id = id;
        this.titulo = titulo;
        this.VariedadeCarneEmp = VariedadeCarneEmp;
        this.disponivel = true;
    }

    public String toString() {
        return "\nTitulo: " + getTitulo() + "\nID: " + getId() + "\nVariedadeCarne de empdescansarimo: " + getVariedadeCarneEmp();
    }
}
