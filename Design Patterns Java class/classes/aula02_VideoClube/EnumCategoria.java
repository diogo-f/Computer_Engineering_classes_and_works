/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula02_VideoClube;

/**
 *
 * @author diogo
 */
public enum EnumCategoria {

    /**
     *
     */
    ACTION("Acção", 1),

    /**
     *
     */
    ADVENTURE("Aventura", 2),

    /**
     *
     */
    COMEDY("Comedia", 3),

    /**
     *
     */
    CRIME_GANGSTER("Crime & Gangster", 4),

    /**
     *
     */
    DRAMA("Drama", 5),

    /**
     *
     */
    HISTORY("Historia", 6),

    /**
     *
     */
    HORROR("Horror", 7),

    /**
     *
     */
    MUSICALS("Musical", 8),

    /**
     *
     */
    SCIENCEFICTION("Ficção cientifica", 9),

    /**
     *
     */
    WAR("Guerra", 10),

    /**
     *
     */
    WESTERNS("Western", 11);

    private String nomeCat;
    private int catID;

    private EnumCategoria(String nomeCat, int catID) {
        this.nomeCat = nomeCat;
        this.catID = catID;
    }

    /**
     *
     * @return
     */
    public int getCatID() {
        return catID;
    }

    /**
     *
     * @return
     */
    public String getNomeCat() {
        return nomeCat;
    }

    @Override
    public String toString() {
        return Integer.toString(catID) + " - " + nomeCat;
    }
}
