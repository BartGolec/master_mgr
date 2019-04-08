package com.mgr.bg.Model;

import javax.persistence.*;

/**
 * Created by Bartosz on 11/19/2018.
 */

@Entity
@Table(name = "singleData")
public class SingleDataEntity {

    public SingleDataEntity(String date, int pmax, int CP, int CO, int BPP, int BPO, int BOO, int BOP) {
        this.date = date;
        this.Pmax = pmax;
        this.CP = CP;
        this.CO = CO;
        this.BPP = BPP;
        this.BPO = BPO;
        this.BOO = BOO;
        this.BOP = BOP;
    }

    public SingleDataEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Date")
    private String date;

    private double Pmax;

    private double CP;

    private double CO;

    private double BPP;

    private double BPO;

    private double BOO;

    private double BOP;

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPmax() {
        return Pmax;
    }

    public void setPmax(double pmax) {
        Pmax = pmax;
    }

    public double getCP() {
        return CP;
    }

    public void setCP(double CP) {
        this.CP = CP;
    }

    public double getCO() {
        return CO;
    }

    public void setCO(double CO) {
        this.CO = CO;
    }

    public double getBPP() {
        return BPP;
    }

    public void setBPP(double BPP) {
        this.BPP = BPP;
    }

    public double getBPO() {
        return BPO;
    }

    public void setBPO(double BPO) {
        this.BPO = BPO;
    }

    public double getBOO() {
        return BOO;
    }

    public void setBOO(double BOO) {
        this.BOO = BOO;
    }

    public double getBOP() {
        return BOP;
    }

    public void setBOP(double BOP) {
        this.BOP = BOP;
    }

    @Override
    public String toString() {
        return "SingleDataEntity{" +
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
