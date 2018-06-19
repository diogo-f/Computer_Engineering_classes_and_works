/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula02_VideoClube;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diogo
 */
public class Emprestimo {

    private final Date checkOut;
    private Date checkIn;
    private final int idCliente;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     *
     * @param idCliente
     */
    public Emprestimo(int idCliente) {
        this.checkOut = new Date();
        this.idCliente = idCliente;
    }

    /**
     *faz o checkIn com a data e hora do momento
     */
    public void setCheckIn() {
        this.checkIn = new Date();
    }

    /**
     *
     * @return
     */
    public String getCliente() {
        if (checkIn != null) {
            return "n socio: " + idCliente + " | Data de devolução: " + dateFormat.format(checkIn);
        } else {
            return "n socio: " + idCliente + " | Data de devolução: não devolvido";
        }
    }

    @Override
    public String toString() {
        if (checkIn != null) {
            return "\n--Emprestimo--\n" + "Data de checkout: " + dateFormat.format(checkOut) + "\nData de checkin : " + dateFormat.format(checkIn) + "\nn socio: " + idCliente;
        } else {
            return "\n--Emprestimo--\n" + "Data de checkout: " + dateFormat.format(checkOut) + "\nData de checkin : não entregue" + "\nn socio: " + idCliente;
        }
    }
}
