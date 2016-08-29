package com.wfc.cxf.pattern.composite.impl;

import com.wfc.cxf.pattern.composite.interfac.ILeaf;

public class Leaf implements ILeaf {

    private String name;
    private String position;
    private int salary = 0;

    public Leaf(String name, String position, int salary) {
        super();
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getInfo() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Leaf [name=" + name + ", position=" + position + ", salary=" + salary + "]";
    }
}
