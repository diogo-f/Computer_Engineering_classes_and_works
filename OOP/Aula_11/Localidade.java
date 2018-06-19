/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_11;

/**
 *
 * @author diogo
 */
public class Localidade extends Regiao {

    TipoLocalidade tipo;

    public Localidade(String nome, int pop, TipoLocalidade tipo) {
        super(nome, pop);
        this.tipo = tipo;
    }

    public TipoLocalidade getTipoL() {
        return tipo;
    }

    @Override
    public String toString() {
        return tipo + " " + super.toString();
    }

}
