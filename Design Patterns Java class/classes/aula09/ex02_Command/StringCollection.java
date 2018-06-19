/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex02_Command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo
 */
public class StringCollection implements StringCollectionInterface {

    List<String> listaStrings;
//    UndoRedo undoRedo;

    public StringCollection() {
        this.listaStrings = new ArrayList<>();
//        undoRedo = new UndoRedo();
    }

    @Override
    public void addString(String s) {
        listaStrings.add(s);
//        undoRedo.addUndoCommand(s);
    }

    @Override
    public void removeString(String s) {

        for (int i = 0; i < listaStrings.size(); i++) {
            if (listaStrings.get(i).equals(s)) {
                listaStrings.remove(i);
//                undoRedo.addUndoCommand(s);
            }
        }
    }

    public void printAll() {
        System.out.println("");
        for (String s : listaStrings) {
            System.out.println(s);
        }
    }
}
