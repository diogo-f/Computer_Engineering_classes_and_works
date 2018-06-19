/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula07.ex02_Decorator;

/**
 *
 * @author diogo
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\n---txt1---");
        TextReaderInterface txt1 = new TextReader("texto");
        while (txt1.hasNext()) {
            System.out.println(txt1.next());
        }

        System.out.println("\n---txt2---");
        TextReaderInterface txt2 = new TermFilter(new TextReader("texto"));
        while (txt2.hasNext()) {
            System.out.println(txt2.next());
        }

        System.out.println("\n---txt3---");
        TextReaderInterface txt3 = new ReverseFilter(new TextReader("texto"));
        while (txt3.hasNext()) {
            System.out.println(txt3.next());
        }

        System.out.println("\n---txt4---");
        TextReaderInterface txt4 = new CoderFilter(new TextReader("texto"));
        while (txt4.hasNext()) {
            System.out.println(txt4.next());
        }

        System.out.println("\n---txt5---");
        TextReaderInterface txt5 = new ReverseFilter(new TermFilter(new TextReader("texto")));
        while (txt5.hasNext()) {
            System.out.println(txt5.next());
        }

        System.out.println("\n---txt6---");
        TextReaderInterface txt6 = new CoderFilter(new ReverseFilter(new TextReader("texto")));
        while (txt6.hasNext()) {
            System.out.println(txt6.next());
        }
        System.out.println("\n---txt7---");
        TextReaderInterface txt7 = new CoderFilter(new TermFilter(new ReverseFilter(new TextReader("texto"))));
        while (txt7.hasNext()) {
            System.out.println(txt7.next());
        }
    }
}
