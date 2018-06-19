/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula_05;

import static java.lang.System.arraycopy;

/**
 *
 * @author diogo
 */
public class Conjunto {

    private int[] numeros;
    private int size;

    public Conjunto() {
        numeros = new int[10];
        size = 0;
    }

    public void insert(int n) {
        if (numeros.length == size) {
            increase();
        }
        if (!contains(n)) {
            numeros[size] = n;
            size++;
        }
    }

    public void increase() {
        int[] tmp = new int[numeros.length + 10];
        arraycopy(numeros, 0, tmp, 0, numeros.length);
        numeros = tmp;

    }

    public boolean contains(int n) {
        boolean contain = false;
        for (int i = 0; i < size; i++) {
            if (numeros[i] == n) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    public void remove(int n) {
        for (int i = 0; i < numeros.length - 1; i++) {
            if (numeros[i] == n) {
                size--;
                for (int j = i; j < numeros.length - 1; j++) {
                    if (j == size) {
                        numeros[j] = 0;
                    }
                    numeros[j] = numeros[j + 1];
                }
                System.out.println("Removido o elemento " + n);
            }
        }
    }

    @Override
    public String toString() {
        String tmp = "";
        for (int i = 0; i < size; i++) {
            tmp = tmp + " " + numeros[i];
        }
        return tmp;
    }

    public void empty() {
        numeros = new int[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public Conjunto unir(Conjunto x) {
        Conjunto unido = new Conjunto();
        unido.numeros = numeros;

        for (int i = 0; i < x.size; i++) {
            if (!unido.contains(x.numeros[i])) {
                unido.insert(x.numeros[i]);
            }
        }
        return unido;
    }

    public Conjunto subtrair(Conjunto x) {
        Conjunto sub = new Conjunto();
        sub.numeros = numeros;

        for (int i = 0; i < x.size; i++) {
            if (sub.contains(x.numeros[i])) {
                sub.remove(x.numeros[i]);
            }
        }
        return sub;
    }

    public Conjunto interset(Conjunto x) {
        Conjunto inters = new Conjunto();

        for (int i = 0; i < x.size; i++) {
            if (inters.contains(x.numeros[i])) {
                inters.insert(x.numeros[i]);
            }
        }
        return inters;
    }
}
