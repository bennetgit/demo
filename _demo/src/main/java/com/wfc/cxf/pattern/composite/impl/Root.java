package com.wfc.cxf.pattern.composite.impl;

import java.util.ArrayList;

import com.wfc.cxf.pattern.composite.interfac.IBranch;
import com.wfc.cxf.pattern.composite.interfac.ILeaf;
import com.wfc.cxf.pattern.composite.interfac.IRoot;

public class Root implements IRoot {
    private ArrayList subordinateList = new ArrayList();

    private String name;
    private String position;
    private int salary = 0;

    public Root(String name, String position, int salary) {
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
    public void add(IBranch branch) {
        this.subordinateList.add(branch);
    }

    @Override
    public void add(ILeaf leaf) {
        this.subordinateList.add(leaf);
    }

    @Override
    public ArrayList getSubordinateInfo() {
        return this.subordinateList;
    }

    @Override
    public String toString() {
        return "Root [subordinateList=" + subordinateList + ", name=" + name + ", position=" + position + ", salary="
                + salary + "]";
    }

}
