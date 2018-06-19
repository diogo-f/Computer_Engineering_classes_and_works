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
public enum EnumIdadeMinima {

    /**
     *
     */
    ALL(1, "Todas as idades"),

    /**
     *
     */
    M6(2, "M6"),

    /**
     *
     */
    M12(3, "M12"),

    /**
     *
     */
    M16(4, "M16"),

    /**
     *
     */
    M18(5, "M18");

    private String idade;
    private int idadeID;

    private EnumIdadeMinima(int idadeID, String idade) {
        this.idade = idade;
        this.idadeID = idadeID;
    }

    /**
     *
     * @return
     */
    public int getIdadeID() {
        return idadeID;
    }

    /**
     *
     * @return
     */
    public String getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return Integer.toString(idadeID) + " - " + idade;

    }
}
