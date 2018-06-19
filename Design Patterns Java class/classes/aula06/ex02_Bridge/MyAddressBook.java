/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex02_Bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class MyAddressBook implements ContactsInterface {

    public static Scanner sc = new Scanner(System.in);
    private List<Contact> contactsBook;
    private ContactsStorageInterface contactStorage;

    public MyAddressBook() {
        contactsBook = new ArrayList<>();
    }

    @Override
    public void openAndLoad(ContactsStorageInterface store) {
        System.out.println("1");
        contactStorage = store;
        System.out.println("2");
        contactsBook = contactStorage.loadContacts();
    }

    @Override
    public void saveAndClose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAndClose(ContactsStorageInterface store) {
        contactStorage = store;
        contactStorage.saveContacts(contactsBook);
    }

    @Override
    public boolean exist(Contact contact) {
        String numeroTemp = contact.getNumero1();
        boolean exists = false;

        for (Contact c : contactsBook) {
            if (c.getNumero1().equals(numeroTemp) || c.getNumero2().equals(numeroTemp)) {
                exists = true;
            }
        }
        return exists;
    }

    @Override
    public Contact getByName(String name) {
        Contact temp = null;
        for (Contact c : contactsBook) {
            if (c.getNome().contains(name)) {
                temp = c;
            }
        }
        return temp;
    }

    @Override
    public boolean add(Contact contact) {
        if (contactsBook.add(contact)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Contact contact) {
        boolean removido = false;
        for (int i = 0; i < contactsBook.size(); i++) {
            if (contactsBook.get(i).getNome().contains(contact.getNome()) && (contact.getNumero1().equals(contactsBook.get(i).getNumero1()) || contact.getNumero1().equals(contactsBook.get(i).getNumero2()))) {
                contactsBook.remove(i);
                removido = true;
            }
        }
        return removido;
    }

    public List<Contact> getAllContacts() {
        return contactsBook;
    }

}
