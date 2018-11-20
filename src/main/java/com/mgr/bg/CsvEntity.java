package com.mgr.bg;

import javax.persistence.*;

/**
 * Created by Bartosz on 11/19/2018.
 */

@Entity // this tells Hibernate to make table out of this class
@Table(name = "CSV_FILE")
public class CsvEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date;

    private int Pmax;

    private int CP;

    private int CO;

    private int BPP;

    private int BPO;

    private int BOO;

    private int BOP;

    public int getBPO() {
        return BPO;
    }

    public void setBPO(int BPO) {
        this.BPO = BPO;
    }

    public int getBOO() {
        return BOO;
    }

    public void setBOO(int BOO) {
        this.BOO = BOO;
    }

    public int getBOP() {
        return BOP;
    }

    public void setBOP(int BOP) {
        this.BOP = BOP;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPmax() {
        return Pmax;
    }

    public void setPmax(int pmax) {
        Pmax = pmax;
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public int getCO() {
        return CO;
    }

    public void setCO(int CO) {
        this.CO = CO;
    }

    public int getBPP() {
        return BPP;
    }

    public void setBPP(int BPP) {
        this.BPP = BPP;
    }
}
