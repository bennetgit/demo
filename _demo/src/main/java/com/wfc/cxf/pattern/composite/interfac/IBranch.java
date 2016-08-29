package com.wfc.cxf.pattern.composite.interfac;

import java.util.ArrayList;

public interface IBranch {

    public String getInfo();

    public void add(IBranch branch);

    public void add(ILeaf leaf);

    @SuppressWarnings("rawtypes")
    public ArrayList getSubordinateInfo();
}
