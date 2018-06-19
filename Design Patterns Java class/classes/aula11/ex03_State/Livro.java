/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula11.ex03_State;

/**
 *
 * @author diogo
 */
public class Livro {

    private EstadoInterface estado;
    private String titulo;
    private String isbn;
    private int ano;
    private String autor;

    public Livro(String titulo, String isbn, int ano, String autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.ano = ano;
        this.autor = autor;
        this.estado = null;
    }

    public EstadoInterface getEstado() {
        return estado;
    }

    public void setEstado(EstadoInterface estado) {
        this.estado = estado;
    }
}
