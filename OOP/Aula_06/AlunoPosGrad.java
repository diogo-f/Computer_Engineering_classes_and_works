/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_06;

/**
 *
 * @author diogo
 */
public class AlunoPosGrad extends Aluno {
    private Professor prof;
    
    public AlunoPosGrad(String n, int cc, Data da, Professor prof){
        super(n,cc,da);
        this.prof = prof;
    }

    @Override
    public String toString() {
        return "Aluno Graduado " + super.toString();
    }
    
    
}
