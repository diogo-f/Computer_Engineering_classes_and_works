/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex02_Command;

import java.util.List;

/**
 *
 * @author diogo
 */
public class AddStringCommand implements Command {

    StringCollection theCollection;
    String s;

    public AddStringCommand(StringCollection col, String s) {
        this.theCollection = col;
        this.s = s;
    }

    @Override
    public void execute() {
        theCollection.addString(s);
    }

    @Override
    public void undo() {
        theCollection.removeString(s);
    }

}
