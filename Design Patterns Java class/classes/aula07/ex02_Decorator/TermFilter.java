/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex02_Decorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author diogo
 */
public class TermFilter extends TextReaderDecorator {

    private List<String> paragrafo;
    private List<String> temp;

    public TermFilter(TextReaderInterface txtReader) {
        super(txtReader);
        paragrafo = new ArrayList<>();
        while (textReader.hasNext()) {
            temp = Arrays.asList(textReader.next().split(" "));
            addAll();
        }
    }

    private void addAll() {
        for (String s : temp) {
            paragrafo.add(s);
        }
        temp = null;
    }

    @Override
    public boolean hasNext() {
        return !paragrafo.isEmpty();
    }

    @Override
    public String next() {
        if (hasNext()) {
            return paragrafo.remove(0);
        } else {
            return null;
        }
    }
}
