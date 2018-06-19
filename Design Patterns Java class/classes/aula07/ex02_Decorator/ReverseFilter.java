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
public class ReverseFilter extends TextReaderDecorator {

    private List<String> paragrafo;

    public ReverseFilter(TextReaderInterface txtReader) {
        super(txtReader);
        paragrafo = new ArrayList<>();
        while (txtReader.hasNext()) {
            paragrafo.add(txtReader.next());
        }
    }

    @Override
    public boolean hasNext() {
        return !paragrafo.isEmpty();
    }

    @Override
    public String next() {
        if (hasNext()) {
            return new StringBuilder(paragrafo.remove(0)).reverse().toString();
        } else {
            return null;
        }
    }

}
