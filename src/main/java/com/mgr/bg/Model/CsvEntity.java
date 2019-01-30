package com.mgr.bg.Model;

import javax.persistence.*;

/**
 * Created by Bartosz on 11/19/2018.
 */

@Entity
@Table(name = "CSVDatabase")
public class CsvEntity {

    public CsvEntity(String date, int pmax, int CP, int CO, int BPP, int BPO, int BOO, int BOP) {
        this.date = date;
        this.Pmax = pmax;
        this.CP = CP;
        this.CO = CO;
        this.BPP = BPP;
        this.BPO = BPO;
        this.BOO = BOO;
        this.BOP = BOP;
    }

    public CsvEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Data")
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

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "CsvEntity{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", Pmax=" + Pmax +
                ", CP=" + CP +
                ", CO=" + CO +
                ", BPP=" + BPP +
                ", BPO=" + BPO +
                ", BOO=" + BOO +
                ", BOP=" + BOP +
                '}';
    }
}
