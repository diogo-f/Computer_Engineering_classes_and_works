/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06.ex02_Bridge;

import java.io.Serializable;

/**
 *
 * @author diogo
 */
public class Contact implements Serializable {

    private final String nome;
    private String numero1;
    private String numero2;
    private String email;
    private String morada;
    private String company;
    private String website;

    public Contact(Builder b) {
        this.nome = b.nome;
        this.numero1 = b.numero1;
        this.numero2 = b.numero2;
        this.email = b.email;
        this.morada = b.morada;
        this.company = b.company;
        this.website = b.website;
    }

    public static class Builder {

        private final String nome;
        private String numero1 = "sem numero principal";
        private String numero2 = "sem numero secundario";
        private String email = "sem email";
        private String morada = "sem morada";
        private String company = "sem empresa";
        private String website = "sem website";

        public Builder(String nome) {
            this.nome = nome;
        }

        public Builder numero1(String numero) {
            this.numero1 = numero;
            return this;
        }

        public Builder numero2(String numero) {
            this.numero2 = numero;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder morada(String morada) {
            this.morada = morada;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getNumero1() {
        return numero1;
    }

    public String getNumero2() {
        return numero2;
    }

    public String getEmail() {
        return email;
    }

    public String getMorada() {
        return morada;
    }

    public String getCompany() {
        return company;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return "\nContact\n" + "nome= " + nome + ", numero principal= " + numero1 + ", numero secund= " + numero2 + ", email= " + email + ", morada= " + morada + ", empresa= " + company + ", website= " + website;
    }

}
