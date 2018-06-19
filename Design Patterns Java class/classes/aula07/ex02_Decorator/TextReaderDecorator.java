/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex02_Decorator;

/**
 *
 * @author diogo
 */
public abstract class TextReaderDecorator implements TextReaderInterface {

    protected TextReaderInterface textReader;

    public TextReaderDecorator(TextReaderInterface txtReadInterf) {
        this.textReader = txtReadInterf;
    }

    @Override
    public String toString() {
        return textReader.toString();
    }
}
