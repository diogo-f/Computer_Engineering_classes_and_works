/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex02_Bridge;

/**
 *
 * @author diogo
 */
public interface ContactsInterface {

    public void openAndLoad(ContactsStorageInterface store);

    public void saveAndClose();

    public void saveAndClose(ContactsStorageInterface store);

    public boolean exist(Contact contact);

    public Contact getByName(String name);

    public boolean add(Contact contact);

    public boolean remove(Contact contact);
}
