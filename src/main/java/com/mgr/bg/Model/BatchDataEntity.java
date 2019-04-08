package com.mgr.bg.Model;

import javax.persistence.*;

@Entity
@Table(name = "batchData")
public class BatchDataEntity implements Comparable<BatchDataEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FileName")
    private String fileName;

    @Column(name = "Date")
    private String date;

    private double Pmax;

    private double CP;

    private double CO;

    private double BPP;

    private double BPO;

    private double BOO;

    private double BOP;

    public BatchDataEntity(String fileName, String date, double pmax, double CP, double CO, double BPP, double BPO, double BOO, double BOP) {
        this.fileName = fileName;
        this.date = date;
        Pmax = pmax;
        this.CP = CP;
        this.CO = CO;
        this.BPP = BPP;
        this.BPO = BPO;
        this.BOO = BOO;
        this.BOP = BOP;
    }

    public BatchDataEntity(){
    }

    @Override
    public String toString() {
        return "BatchDataEntity{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(BatchDataEntity o) {
        return this.getDate().compareTo(o.getDate());
    }
}
