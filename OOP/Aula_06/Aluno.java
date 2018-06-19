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
public class Aluno extends Pessoa {

    private int nmec = 100;
    private Data dataInsc;
    private int count;

    public Aluno(String n, int cc, Data da, Data insc) {
        super(n, cc, da);
        this.dataInsc = insc;
        this.count = count;
        count = nmec++;
    }

    public Aluno(String n, int cc, Data da) {
        super(n, cc, da);
        this.dataInsc = Data.hoje();
        this.count = count;
        count = nmec++;
    }

    public int getNmec() {
        return count;
    }
    
    public String getName(){
        return super.getNome();
    }

    public Data getDataInsc() {
        return dataInsc;
    }

    @Override
    public String toString() {
        return "\nAluno: " + "\nNumero mec.: " + count + "\nData inscrição: " + dataInsc;
    }

}
