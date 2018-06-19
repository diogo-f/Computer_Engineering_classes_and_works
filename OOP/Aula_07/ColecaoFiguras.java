/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_07;

/**
 *
 * @author diogo
 */
public class ColecaoFiguras {

    private int existPos;
    private double maxArea, areaT;
    private int contF;
    private Figura[] fig;

    public ColecaoFiguras(double maxArea) {
        this.maxArea = maxArea;
        this.contF = 0;
        this.areaT = 0;
        fig = new Figura[15];
    }

    public boolean addFigura(Figura f) {
        if (contF < fig.length || areaT < maxArea || !exists(f)) {
            fig[contF] = f;
            contF++;
            areaT += f.area();
            return true;
        } else {
            System.out.println("Não adicionado \n" + f);
            return false;
        }
    }

    public boolean delFigura(Figura f) {
        if (!exists(f)) {
            System.out.println("Figura não existe");
            return false;
        } else {
            Figura newArr[] = new Figura[fig.length - 1];
            for (int i = 0; i < fig.length; i++) {
                if (fig[i] == f) {
                    for (int j = i; j < fig.length - 1; j++) {
                        fig[j] = fig[j + 1];
                    }
                }
            }
            System.arraycopy(fig, 0, newArr, 0, newArr.length);
            fig = newArr;
            areaT -= f.area();
        }
        System.out.println("figura removida!");
        return true;
    }

    public double areaTotal() {
        return areaT;
    }

    public boolean exists(Figura f) {
        for (Figura fi : fig) {
            if (fi.equals(f)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Coleção de figuras\nArea Maxima: " + maxArea + "\nArea ocupada: " + areaT + "\nExistem " + contF + " figuras";
    }

    public Figura[] getFiguras() {
        return fig;
    }

    public Ponto[] getCentros() {
        Ponto[] pto = new Ponto[contF];

        for (int i = 0; i < contF; i++) {
            pto[i] = fig[i].getP();
        }

        return pto;
    }
}
