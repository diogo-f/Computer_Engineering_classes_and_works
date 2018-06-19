/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula09.ex02_Command;

/**
 *
 * @author diogo
 */
public class CommandMain {

    public static void main(String[] args) {
        UndoRedo undo = new UndoRedo();

        StringCollection coll = new StringCollection();

        AddStringCommand addStrCom = new AddStringCommand(coll, "first");
        undo.addUndoCommand(addStrCom);
        OptionExecutioner exec = new OptionExecutioner(addStrCom);
        exec.executeCommand();

        addStrCom = new AddStringCommand(coll, "second");
        undo.addUndoCommand(addStrCom);
        exec = new OptionExecutioner(addStrCom);
        exec.executeCommand();

        addStrCom = new AddStringCommand(coll, "third");
        undo.addUndoCommand(addStrCom);
        exec = new OptionExecutioner(addStrCom);
        exec.executeCommand();

        coll.printAll();

        exec = new OptionExecutioner(undo.undo());
        exec.undoCommand();
        
        coll.printAll();
    }
}
