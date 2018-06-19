/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex02_Decorator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class TextReader implements TextReaderInterface {

    private List<String> paragrafos = new ArrayList<>();

    public TextReader(String fileName) {
        readFile(fileName);
    }

    public void readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNext()) {
                paragrafos.add(scanFile.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean hasNext() {
        return !paragrafos.isEmpty();
    }

    @Override
    public String next() {
        if (hasNext()) {
            return paragrafos.remove(0);
        } else {
            return null;
        }
    }
}
