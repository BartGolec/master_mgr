package com.mgr.bg.Model;

public class Load {

    private String oldSignature;
    private String signature;
    private String bus;
    private int phases;
    private String conn;
    private int model;
    private double voltage;
    private double activePower;
    private double reactivePower;
    private String modelFilePath;

    public Load(String signature, String bus, int phases, String conn, int model, double voltage, double activePower, double reactivePower) {
        this.signature = signature;
        this.bus = bus;
        this.phases = phases;
        this.conn = conn;
        this.model = model;
        this.voltage = voltage;
        this.activePower = activePower;
        this.reactivePower = reactivePower;
    }

    public Load() {
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

    public int getPhases() {
        return phases;
    }

    public void setPhases(int phases) {
        this.phases = phases;
    }

    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getActivePower() {
        return activePower;
    }

    public void setActivePower(double activePower) {
        this.activePower = activePower;
    }

    public double getReactivePower() {
        return reactivePower;
    }

    public void setReactivePower(double reactivePower) {
        this.reactivePower = reactivePower;
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    public String getOldSignature() {
        return oldSignature;
    }

    public void setOldSignature(String oldSignature) {
        this.oldSignature = oldSignature;
    }

    public String getOpenDssLoadConvension(){
        return "New Load." + signature + " " + "Bus1=" + bus + " " + "Phases=" + phases + " " + "Conn=" + conn + " " + "Model=" + model + " " + "kV=" + voltage + "  " + "kW=" + activePower + " " + "kvar=" + reactivePower;
    }
}
