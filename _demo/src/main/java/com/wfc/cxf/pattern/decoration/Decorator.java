package com.wfc.cxf.pattern.decoration;

public abstract class Decorator extends SchoolReport {

    private SchoolReport sr;

    public Decorator(SchoolReport sr) {
        super();
        this.sr = sr;
    }

    @Override
    public void report() {
        this.sr.report();
    }

    @Override
    public void sign(String name) {
        this.sr.sign(name);
    }
}
