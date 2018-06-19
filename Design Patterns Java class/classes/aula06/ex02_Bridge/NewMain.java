/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex02_Bridge;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class NewMain {

    public static Scanner sc = new Scanner(System.in);
    private static MyAddressBook myBook = new MyAddressBook();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        menu();

        //List<Contact> aa = testCSV();
        //testSerial(aa);
    }

    public static void menu() {

        int menuOption;
        try {
            do {
                System.out.println("Escolha uma opção");
                System.out.println("1- openAndLoad");
                System.out.println("2- saveAndClose");
                System.out.println("3- ContactExist");
                System.out.println("4- searchByName");
                System.out.println("5- addContact");
                System.out.println("6- removeContact");
                System.out.println("7- ver todos os contactos");
                System.out.println("8- sair");
                menuOption = sc.nextInt();

                switch (menuOption) {
                    case 1:
                        openLoad();
                        break;
                    case 2:
                        saveAndClose();
                        break;
                    case 3:
                        contactExists();
                        break;
                    case 4:
                        searchByName();
                        break;
                    case 5:
                        addContact();
                        break;
                    case 6:
                        removeContact();
                        break;
                    case 7:
                        allContacts();
                        break;
                    case 8:
                        System.out.println("A sair...");
                        break;
                    default:
                        System.out.println("opção invalida");
                }
            } while (menuOption != 8);
        } catch (InputMismatchException ex) {
            System.out.println("opção invalida");
            sc.next();
        }
    }

    public static void openLoad() {
        int opt = 0;
        System.out.println("---Abrir ficheiro de contactos---\nescolha a opção pretendida: ");
        System.out.println("1- CSV file");
        System.out.println("2- Serial file");
        opt = sc.nextInt();
        do {
            switch (opt) {
                case 1:
                    myBook.openAndLoad(new ContactsStorageCSV());
                    return;
                case 2:
                    myBook.openAndLoad(new ContactsStorageSerial());
                    return;
                default:
                    System.err.println("Formato desconhecido");
            }
        } while (opt < 1 && opt > 2);
    }

    public static void saveAndClose() {
        int opt = 0;
        System.out.println("---Guardar ficheiro de contactos---\nescolha a opção pretendida: ");
        System.out.println("1- Guardar para formato CSV");
        System.out.println("2- Guardar para formato Serial");
        opt = sc.nextInt();
        do {
            switch (opt) {
                case 1:
                    myBook.saveAndClose(new ContactsStorageCSV());
                    break;
                case 2:
                    myBook.saveAndClose(new ContactsStorageSerial());
                    break;
                default:
                    System.err.println("Formato desconhecido");
            }
        } while (opt < 1 && opt > 2);
    }

    public static void contactExists() {
        sc.nextLine();
        Contact temp;
        System.out.println("Qual o numero do contacto a procurar?");
        temp = new Contact.Builder("temp").numero1(sc.nextLine()).build();
        if (myBook.exist(temp)) {
            System.out.println("Numero de contacto existe na lista");
        } else {
            System.out.println("Numero de contacto não existe na lista");
        }
    }

    public static void searchByName() {
        sc.nextLine();
        System.out.println("Qual o nome a procurar? ");
        Contact temp = myBook.getByName(sc.nextLine());
        if (temp == null) {
            System.out.println("Contacto nao encontrado");
        } else {
            System.out.println(temp.toString());
        }
    }

    public static void addContact() {
        String nome;
        String numero1;
        String numero2;
        String email;
        String morada;
        String company;
        String website;
        sc.nextLine();
        System.out.println("Insira o seguintes dados:");
        System.out.println("Nome: ");
        nome = sc.nextLine();
        System.out.println("Numero principal: ");
        numero1 = sc.nextLine();
        System.out.println("Numero secundario: ");
        numero2 = sc.nextLine();
        System.out.println("email: ");
        email = sc.nextLine();
        System.out.println("Morada: ");
        morada = sc.nextLine();
        System.out.println("Empresa: ");
        company = sc.nextLine();
        System.out.println("Website: ");
        website = sc.nextLine();

        Contact newContact = new Contact.Builder(nome).numero1(numero1).numero2(numero2).email(email).morada(morada).company(company).website(website).build();

        if (myBook.add(newContact)) {
            System.out.println("Adicionado " + newContact.toString());
        } else {
            System.out.println("Não adicionado");
        }
    }

    public static void removeContact() {
        sc.nextLine();
        Contact temp;
        System.out.println("Qual o nome do contacto a eliminar?");
        String nomeTemp = sc.nextLine();
        System.out.println("Qual o numero do contacto a eliminar?");
        String numTemp = sc.nextLine();
        temp = new Contact.Builder(nomeTemp).numero1(numTemp).build();
        if (myBook.remove(temp)) {
            System.out.println("Contacto eliminado");
        } else {
            System.out.println("Erro ao eliminar, verifique se preencheu bem");
        }
    }

    public static void allContacts() {
        List<Contact> listaTemp = myBook.getAllContacts();
        for (Contact c : listaTemp) {
            System.out.println(c);
        }
    }

    public static void testSerial(List<Contact> lis) {
        ContactsStorageInterface ser = new ContactsStorageSerial();
        ser.saveContacts(lis);
        ser.loadContacts();
    }

    public static List<Contact> testCSV() {
        ContactsStorageInterface csv = new ContactsStorageCSV();
        List<Contact> aa = new ArrayList<>();
        aa = csv.loadContacts();
        for (Contact c : aa) {
            System.out.println(c.toString());
        }
        csv.saveContacts(aa);
        return aa;
    }
}
