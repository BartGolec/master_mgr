package com.mgr.bg.Model;

public class SimulationResultsCheckboxes {

    private boolean voltages;
    private boolean currents;
    private boolean powers;
    private boolean losses;
    private boolean taps;
    private String openDssModelFilePath;
    private String openDssExeFilePath;

    public SimulationResultsCheckboxes(boolean voltages, boolean currents, boolean powers, boolean losses, boolean taps) {
        this.voltages = voltages;
        this.currents = currents;
        this.powers = powers;
        this.losses = losses;
        this.taps = taps;
    }

    public SimulationResultsCheckboxes(){

    }

    public String getOpenDssExeFilePath() {
        return openDssExeFilePath;
    }

    public void setOpenDssExeFilePath(String openDssExeFilePath) {
        this.openDssExeFilePath = openDssExeFilePath;
    }

    public String getOpenDssModelFilePath() {
        return openDssModelFilePath;
    }

    public void setOpenDssModelFilePath(String openDssModelFilePath) {
        this.openDssModelFilePath = openDssModelFilePath;
    }

    public boolean isVoltages() {
        return voltages;
    }

    public void setVoltages(boolean voltages) {
        this.voltages = voltages;
    }

    public boolean isCurrents() {
        return currents;
    }

    public void setCurrents(boolean currents) {
        this.currents = currents;
    }

    public boolean isPowers() {
        return powers;
    }

    public void setPowers(boolean powers) {
        this.powers = powers;
    }

    public boolean isLosses() {
        return losses;
    }

    public void setLosses(boolean losses) {
        this.losses = losses;
    }

    public boolean isTaps() {
        return taps;
    }

    public void setTaps(boolean taps) {
        this.taps = taps;
    }
}
