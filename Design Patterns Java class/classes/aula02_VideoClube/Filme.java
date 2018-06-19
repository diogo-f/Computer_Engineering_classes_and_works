/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula02_VideoClube;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class Filme {

    private static int count = 1;
    private int filmeID;
    private final String titulo;
    private EnumCategoria catg;
    private EnumIdadeMinima idadeFilme;
    private boolean disponivel;
    private double ratingTotal;
    private ArrayList<Emprestimo> emprestimos;

    //construtor, recebe o titulo e o id da categoria e da idade minima do filme (ENUM)
    /**
     *
     * @param titulo
     * @param catg
     * @param idadeFilme
     */
    public Filme(String titulo, int catg, int idadeFilme) {
        this.titulo = titulo;
        this.disponivel = true;
        this.ratingTotal = 0;
        setFilmeID(count++);
        emprestimos = new ArrayList<>();

        for (EnumCategoria cat : EnumCategoria.values()) {
            if (cat.getCatID() == catg) {
                this.catg = cat;
            }
        }

        for (EnumIdadeMinima idade : EnumIdadeMinima.values()) {
            if (idade.getIdadeID() == idadeFilme) {
                this.idadeFilme = idade;
            }
        }
    }

    /**
     *
     * @param filmeID
     */
    public void setFilmeID(int filmeID) {
        this.filmeID = filmeID;
    }

    /**
     *
     * @return
     */
    public int getFilmeID() {
        return filmeID;
    }

    /**
     * Introdução do rating dado pelo utilizador
     */
    public void setRating() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a avaliação dada pelo cliente ao filme(1-10): ");
        double rate = 0;
        while (rate < 1 || rate > 10) {
            try {
                rate = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Rating de 1 a 10");
                sc.next();
            }
        }
        this.ratingTotal = this.ratingTotal + rate;

    }

    /**
     *
     * @return
     */
    public double getRatingTotal() {
        return ratingTotal;
    }

    /**
     *
     * @return
     */
    public double getRatingMedio() {
        return ratingTotal / emprestimos.size();
    }

    /**
     *
     * @return
     */
    public String getDisponibilidade() {
        return disponivel ? "sim" : "não";
    }

    /**
     * Criação de um emprestimo
     *
     * @param idCliente
     */
    public void checkOutEmprestimo(int idCliente) {
        if (!disponivel) {
            System.out.println("Filme não disponivel, emprestado a:" + emprestimos.get(emprestimos.size() - 1).getCliente());
        } else {
            emprestimos.add(new Emprestimo(idCliente));
            disponivel = false;
            System.out.println("Emprestimo guardado" + emprestimos.get(emprestimos.size() - 1));
        }
    }

    /**
     * Devolução do emprestimo
     * 
     */
    public boolean checkInEmprestimo() {
        if (disponivel) {
            System.out.println("Filme disponivel, não pode fazer checkIn");
            return false;
        } else {
            emprestimos.get(emprestimos.size() - 1).setCheckIn();
            disponivel = true;

            System.out.println("\nCheckIn guardado" + emprestimos.get(emprestimos.size() - 1).toString());
            return true;
        }
    }

    /**
     * Historico de emprestimos de um filme
     */
    public void getEmprestimos() {
        System.out.println("\n" + titulo + " | Historico de emprestimos");
        for (Emprestimo emp : emprestimos) {
            System.out.println(emp.getCliente());
        }
    }

    @Override
    public String toString() {
        return "--Filme--\n" + "ID: " + filmeID + " | Titulo: " + titulo + "\nCategoria: " + catg.getNomeCat() + " | Idade minima: " + idadeFilme.getIdade() + "\nDisponivel: " + getDisponibilidade() + " | Rating Total: " + getRatingTotal() + " | Rating Medio: " + getRatingMedio();
    }

    /**
     *
     * @return
     */
    public String toStringIDtitulo() {
        return filmeID + " | r " + getRatingMedio() + " | " + titulo;
    }

}
