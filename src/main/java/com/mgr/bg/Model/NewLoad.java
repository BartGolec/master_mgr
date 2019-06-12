package com.mgr.bg.Model;

public class NewLoad {

    private String signature;
    private String bus;
    private String phases;
    private String conn;
    private String model;
    private String voltage;
    private String activePower;
    private String reactivePower;
    private String modelFilePath;

    public NewLoad(String signature, String bus, String phases, String conn, String model, String voltage, String activePower, String reactivePower, String modelFilePath) {
        this.signature = signature;
        this.bus = bus;
        this.phases = phases;
        this.conn = conn;
        this.model = model;
        this.voltage = voltage;
        this.activePower = activePower;
        this.reactivePower = reactivePower;
        this.modelFilePath = modelFilePath;
    }

    public NewLoad(){
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getPhases() {
        return phases;
    }

    public void setPhases(String phases) {
        this.phases = phases;
    }

    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getActivePower() {
        return activePower;
    }

    public void setActivePower(String activePower) {
        this.activePower = activePower;
    }

    public String getReactivePower() {
        return reactivePower;
    }

    public void setReactivePower(String reactivePower) {
        this.reactivePower = reactivePower;
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    public String getOpenDssNewLoadConvension(){
        return "New " + signature + " "+ bus + " " + phases + " " + "Conn=Wye" + " "  + "Model=1" + " "  + voltage + "  " + activePower + " " +  reactivePower;
    }
}
