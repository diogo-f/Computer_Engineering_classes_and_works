/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex02_Command;

import java.util.LinkedList;

/**
 *
 * @author diogo
 */
public class UndoRedo {

    private LinkedList<Command> undo = new LinkedList<>();
    private LinkedList<Command> redo = new LinkedList<>();

    public void addUndoCommand(Command s) {
        undo.addFirst(s);
    }

    public Command undo() {
        if (undo.size() == 0) {
            return null;
        } else {
            redo.addFirst(undo.peek());
            return undo.pop();
        }
    }

    public Command redo() {
        if (redo.size() == 0) {
            return null;
        } else {
            return redo.pop();
        }
    }
}
