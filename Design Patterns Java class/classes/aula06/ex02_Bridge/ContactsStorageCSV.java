/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex02_Bridge;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class ContactsStorageCSV implements ContactsStorageInterface {

    public static Scanner sc = new Scanner(System.in);
    private List<Contact> listaTemp;

    public ContactsStorageCSV() {
        this.listaTemp = new ArrayList<>();
    }

    @Override
    public List<Contact> loadContacts() {
        System.out.println("chegou ao load");
        listaTemp.clear();
        System.out.println("antes de retornar a lista");
        return readCSVfile();

    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        saveCSVfile(list);
        return true;
    }

    private List<Contact> readCSVfile() {
        //OpenCSV.jar está na pasta da aula
        //Se alterar a ordem de como estão os dados no CSV o programa, de alguma forma, vai falhar!!!!
        //Ler instruções.txt
        CSVReader reader;
        System.out.println("Nome do ficheiro CSV: ");
        try {
            reader = new CSVReader(new FileReader(sc.nextLine()), ',', '"', 1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    System.out.println("A criar contacto " + (listaTemp.size() + 1));
                    Contact contactTemp = new Contact.Builder(nextLine[0]).numero1(nextLine[1]).numero2(nextLine[2]).email(nextLine[3]).company(nextLine[4]).morada(nextLine[5]).website(nextLine[6]).build();
                    //com esta linha abaixo comentada, o programa passa a ler o ficheiro "csv500samples.csv" que esta no diretorio da aula06 no codeUA
//                    Contact contactTemp = new Contact.Builder(nextLine[0] + " " + nextLine[1]).morada(nextLine[3] + ", " + nextLine[7] + ", " + nextLine[4] + ", " + nextLine[5]).company(nextLine[2]).numero1(nextLine[8]).numero2(nextLine[9]).email(nextLine[10]).website(nextLine[11]).build();
                    listaTemp.add(contactTemp);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException ex) {
            System.out.println("Ficheiro incompativel");
        }
        for (Contact c : listaTemp) {
            System.out.println(c);
        }
        return listaTemp;
    }

    private void saveCSVfile(List<Contact> l) {
        try {
            String csv = "contacts.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv));

            //O primeiro contacto na lista diz a ordem como estão guardados os dados
            Contact primeiroContacto = new Contact.Builder("nome").numero1("Numero principal").numero2("Numero secundario").email("email").company("Empresa").morada("Morada").website("website").build();
            String[] primeiro = (primeiroContacto.getNome() + ",," + primeiroContacto.getNumero1() + ",," + primeiroContacto.getNumero2() + ",," + primeiroContacto.getEmail() + ",," + primeiroContacto.getCompany() + ",," + primeiroContacto.getMorada() + ",," + primeiroContacto.getWebsite()).split(",,");
            writer.writeNext(primeiro);

            for (Contact c : l) {
                String[] temp = (c.getNome() + ",," + c.getNumero1() + ",," + c.getNumero2() + ",," + c.getEmail() + ",," + c.getCompany() + ",," + c.getMorada() + ",," + c.getWebsite()).split(",,");
                writer.writeNext(temp);
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Erro a gravar");
        }
    }
}
