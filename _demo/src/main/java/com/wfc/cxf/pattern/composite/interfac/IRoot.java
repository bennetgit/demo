package com.wfc.cxf.pattern.composite.interfac;

import java.util.ArrayList;

/**
 * Composite pattern
 * 
 * @author fcw
 *
 */
public interface IRoot {

    public String getInfo();

    public void add(IBranch branch);

    public void add(ILeaf leaf);

    @SuppressWarnings("rawtypes")
    public ArrayList getSubordinateInfo();
}
