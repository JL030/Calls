package com.example.javier.phonecalllogger;

import java.util.Date;

public class PhoneCall {

    private String tipo, numero;
    private Date inicio, fin;

    public PhoneCall(String type, String number, Date start, Date end) {
        this.tipo = type;
        this.numero = number;
        this.inicio = start;
        this.fin = end;
    }
}
