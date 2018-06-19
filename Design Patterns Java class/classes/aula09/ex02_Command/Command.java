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
public interface Command {

    public void execute();

    public void undo();
}
