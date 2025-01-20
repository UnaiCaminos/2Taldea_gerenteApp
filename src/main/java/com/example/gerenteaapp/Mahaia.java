package com.example.gerenteaapp;

import java.sql.Date;

public class Mahaia {

        private int id;
        private int mahaiZenbakia;
        private int kopurua;
        private Date updateDate;
        private String updateBy;

        public Mahaia(int id, int mahaiZenbakia, int kopurua, Date updateDate, String updateBy) {
            this.id = id;
            this.mahaiZenbakia = mahaiZenbakia;
            this.kopurua = kopurua;
            this.updateDate = updateDate;
            this.updateBy = updateBy;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getMahaiZenbakia() { return mahaiZenbakia; }
        public void setMahaiZenbakia(int mahaiZenbakia) { this.mahaiZenbakia = mahaiZenbakia; }

        public int getKopurua() { return kopurua; }
        public void setKopurua(int kopurua) { this.kopurua = kopurua;}

        public Date getUpdateDate() { return updateDate; }
        public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }

       public String getUpdateBy() { return updateBy; }
       public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }

}
