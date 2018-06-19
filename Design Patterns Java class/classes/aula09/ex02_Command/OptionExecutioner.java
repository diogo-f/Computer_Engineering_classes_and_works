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
public class OptionExecutioner {
    Command theCommand;

    public OptionExecutioner(Command theCommand) {
        this.theCommand = theCommand;
    }
    
    public void executeCommand(){
        theCommand.execute();
    }
    
    public void undoCommand(){
        theCommand.undo();
    }
}
