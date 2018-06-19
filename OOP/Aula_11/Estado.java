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
public class Estado extends Regiao {

    private Localidade capital;

    public Estado(String nome, int pop, Localidade capital) {
        super(nome, pop);
        this.setCapital(capital);
    }

    public void setCapital(Localidade capital) {
        if (!capital.getTipoL().equals(TipoLocalidade.Cidade)) {
            this.capital = capital;
        }
    }

    public Localidade getCapital(){
        return capital;
    }
    
    @Override
    public String toString() {
        return capital + super.toString();
    }
}
