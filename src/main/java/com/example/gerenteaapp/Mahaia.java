package com.example.gerenteaapp;

public class Mahaia {

        private int id;
        private int mahaiZenbakia;
        private int kopurua;

        public Mahaia(int id, int mahaiZenbakia, int kopurua) {
            this.id = id;
            this.mahaiZenbakia = mahaiZenbakia;
            this.kopurua = kopurua;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getMahaiZenbakia() { return mahaiZenbakia; }
        public void setMahaiZenbakia(int mahaiZenbakia) { this.mahaiZenbakia = mahaiZenbakia; }

        public int getKopurua() { return kopurua; }
        public void setKopurua(int kopurua) { this.kopurua = kopurua;}

}
