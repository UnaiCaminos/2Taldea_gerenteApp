package com.example.gerenteaapp;

import java.util.Date;

public class Erreserba {

        private String izena;
        private int mahaiZenbakia;
        private Date data;
        private int pertsonaKop;
        private boolean kantzelatuta;
        private Date updateData;
        private String updateBy;

        public Erreserba(String izena, int mahaiZenbakia, Date data, int pertsonaKop, boolean kantzelatuta, Date updateData, String updateBy) {

            this.izena = izena;
            this.mahaiZenbakia = mahaiZenbakia;
            this.data = data;
            this.pertsonaKop = pertsonaKop;
            this.kantzelatuta = kantzelatuta;
            this.updateData = updateData;this.updateBy = updateBy;
        }

       public String getIzena() { return izena; }
       public void setIzena(String izena) { this.izena = izena; }

       public int getMahaiZenbakia() { return mahaiZenbakia; }
       public void setMahaiZenbakia(int mahaiZenbakia) {this.mahaiZenbakia = mahaiZenbakia;}

       public Date getData() { return data; }
       public void setData(Date data) { this.data = data; }

       public int getPertsonaKop() { return pertsonaKop; }
       public void setPertsonaKop(int pertsonaKop) {this.pertsonaKop = pertsonaKop;}

       public boolean getKantzelatuta() { return kantzelatuta; }
       public void setKantzelatuta(boolean kantzelatuta) {this.kantzelatuta = kantzelatuta;}

       public Date getUpdateData() { return updateData; }
       public void setUpdateData(Date updateData) {this.updateData = updateData;}

       public String getUpdateBy() { return updateBy; }
       public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
}
