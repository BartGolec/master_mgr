package com.mgr.bg.Model;

public class SingleDataObject {
    public SingleDataObject(String date, String pmax, String CP, String CO, String BPP, String BPO, String BOO, String BOP) {
        this.date = date;
        Pmax = pmax;
        this.CP = CP;
        this.CO = CO;
        this.BPP = BPP;
        this.BPO = BPO;
        this.BOO = BOO;
        this.BOP = BOP;
    }

    private String date;

    private String Pmax;

    private String CP;

    private String CO;

    private String BPP;

    private String BPO;

    private String BOO;

    private String BOP;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPmax() {
        return Pmax;
    }

    public void setPmax(String pmax) {
        Pmax = pmax;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getCO() {
        return CO;
    }

    public void setCO(String CO) {
        this.CO = CO;
    }

    public String getBPP() {
        return BPP;
    }

    public void setBPP(String BPP) {
        this.BPP = BPP;
    }

    public String getBPO() {
        return BPO;
    }

    public void setBPO(String BPO) {
        this.BPO = BPO;
    }

    public String getBOO() {
        return BOO;
    }

    public void setBOO(String BOO) {
        this.BOO = BOO;
    }

    public String getBOP() {
        return BOP;
    }

    public void setBOP(String BOP) {
        this.BOP = BOP;
    }

    @Override
    public String toString() {
        return "SingleDataObject{" +
                "date='" + date + '\'' +
                ", Pmax='" + Pmax + '\'' +
                ", CP='" + CP + '\'' +
                ", CO='" + CO + '\'' +
                ", BPP='" + BPP + '\'' +
                ", BPO='" + BPO + '\'' +
                ", BOO='" + BOO + '\'' +
                ", BOP='" + BOP + '\'' +
                '}';
    }
}
