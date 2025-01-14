package com.example.gerenteaapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class Langilea{

    private int id;
    private String dni;
    private String izena;
    private String abizena;
    private String pasahitza;
    private String korreoa;
    private String telefonoa;
    private String postua;

    public Langilea(int id, String dni, String izena, String abizena, String pasahitza, String korreoa, String telefonoa, String postua) {

        this.id = id;
        this.dni = dni;
        this.izena = izena;
        this.abizena = abizena;
        this.pasahitza = pasahitza;
        this.korreoa = korreoa;
        this.telefonoa = telefonoa;
        this.postua = postua;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getIzena() { return izena; }
    public void setIzena(String izena) { this.izena = izena; }

    public String getAbizena() { return abizena; }
    public void setAbizena(String abizena) { this.abizena = abizena; }

    public String getPasahitza() { return pasahitza; }
    public void setPasahitza(String pasahitza) {this.pasahitza = pasahitza;}

    public String getKorreoa() { return korreoa; }
    public void setKorreoa(String korreoa) {this.korreoa = korreoa;}

    public String getTelefonoa() { return telefonoa; }
    public void setTelefonoa(String telefonoa) {this.telefonoa = telefonoa;}

    public String getPostua() { return postua; }
    public void setPostua(String postua) {this.postua = postua;}


}
