package com.mgr.bg.Model;

public class FileDataCheckboxes {

    private boolean pmax;
    private boolean boo;
    private boolean bop;
    private boolean bpo;
    private boolean co;
    private boolean cp;
    private boolean bpp;

    public boolean isPmax() {
        return pmax;
    }

    public void setPmax(boolean pmax) {
        this.pmax = pmax;
    }

    public boolean isBoo() {
        return boo;
    }

    public void setBoo(boolean boo) {
        this.boo = boo;
    }

    public boolean isBop() {
        return bop;
    }

    public void setBop(boolean bop) {
        this.bop = bop;
    }

    public boolean isBpo() {
        return bpo;
    }

    public void setBpo(boolean bpo) {
        this.bpo = bpo;
    }

    public boolean isCo() {
        return co;
    }

    public void setCo(boolean co) {
        this.co = co;
    }

    public boolean isCp() {
        return cp;
    }

    public void setCp(boolean cp) {
        this.cp = cp;
    }

    public boolean isBpp() {
        return bpp;
    }

    public void setBpp(boolean bpp) {
        this.bpp = bpp;
    }

    public FileDataCheckboxes() {
    }

    public FileDataCheckboxes(boolean pmax, boolean boo, boolean bop, boolean bpo, boolean co, boolean cp, boolean bpp) {
        this.pmax = pmax;
        this.boo = boo;
        this.bop = bop;
        this.bpo = bpo;
        this.co = co;
        this.cp = cp;
        this.bpp = bpp;
    }
}
