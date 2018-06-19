/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex02_Decorator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class CoderFilter extends TextReaderDecorator {

    private List<String> paragrafo;

    public CoderFilter(TextReaderInterface txtReader) {
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
            String encryptedString = null;
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(paragrafo.remove(0).getBytes());
                encryptedString = new String(messageDigest.digest());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CoderFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            return encryptedString;
        } else {
            return null;
        }
    }
}
