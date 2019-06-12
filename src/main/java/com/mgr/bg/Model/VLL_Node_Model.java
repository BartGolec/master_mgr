package com.mgr.bg.Model;

import java.util.Objects;

public class VLL_Node_Model {
    private String bus;
    private String node;
    private String voltageUnit;
    private Double vlnValue;
    private Double angleValue;
    private Double puValue;
    private Double baseVoltageValue;

    public VLL_Node_Model(String bus, String node, String voltageUnit, Double vlnValue, Double angleValue, Double puValue, Double baseVoltageValue) {
        this.bus = bus;
        this.node = node;
        this.voltageUnit = voltageUnit;
        this.vlnValue = vlnValue;
        this.angleValue = angleValue;
        this.puValue = puValue;
        this.baseVoltageValue = baseVoltageValue;
    }

    public VLL_Node_Model() {
    }

    public Double getAngleValue() {
        return angleValue;
    }

    public void setAngleValue(Double angleValue) {
        this.angleValue = angleValue;
    }

    public Double getPuValue() {
        return puValue;
    }

    public void setPuValue(Double puValue) {
        this.puValue = puValue;
    }

    public Double getBaseVoltageValue() {
        return baseVoltageValue;
    }

    public void setBaseVoltageValue(Double baseVoltageValue) {
        this.baseVoltageValue = baseVoltageValue;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getVoltageUnit() {
        return voltageUnit;
    }

    public void setVoltageUnit(String voltageUnit) {
        this.voltageUnit = voltageUnit;
    }

    public Double getVlnValue() {
        return vlnValue;
    }

    public void setVlnValue(Double vlnValue) {
        this.vlnValue = vlnValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VLL_Node_Model that = (VLL_Node_Model) o;
        return Objects.equals(bus, that.bus) &&
                Objects.equals(node, that.node) &&
                Objects.equals(voltageUnit, that.voltageUnit) &&
                Objects.equals(vlnValue, that.vlnValue) &&
                Objects.equals(angleValue, that.angleValue) &&
                Objects.equals(puValue, that.puValue) &&
                Objects.equals(baseVoltageValue, that.baseVoltageValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bus, node, voltageUnit, vlnValue, angleValue, puValue, baseVoltageValue);
    }

    @Override
    public String toString() {
        return "VLL_Node_Model{" +
                "bus='" + bus + '\'' +
                ", node='" + node + '\'' +
                ", voltageUnit='" + voltageUnit + '\'' +
                ", vlnValue=" + vlnValue +
                ", angleValue=" + angleValue +
                ", puValue=" + puValue +
                ", baseVoltageValue=" + baseVoltageValue +
                '}';
    }
}
