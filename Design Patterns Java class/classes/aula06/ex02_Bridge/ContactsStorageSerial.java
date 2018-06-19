/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex02_Bridge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogo
 */
public class ContactsStorageSerial implements ContactsStorageInterface {

    public static Scanner sc = new Scanner(System.in);
    private List<Contact> listTemp;

    public ContactsStorageSerial() {
        listTemp = new ArrayList<>();
    }

    @Override
    public List<Contact> loadContacts() {
        System.out.println("Nome do ficheiro serial a abrir: ");
        try {
            FileInputStream fileIn = new FileInputStream(sc.nextLine());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            listTemp = (List<Contact>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("erro");
        }
        return listTemp;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try {
            FileOutputStream fileOut = new FileOutputStream("contactsSerial");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
