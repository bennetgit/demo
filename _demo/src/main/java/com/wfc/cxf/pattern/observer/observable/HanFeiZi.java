package com.wfc.cxf.pattern.observer.observable;

import java.util.Observable;

public class HanFeiZi extends Observable {

    public void haveBreakfast() {
        String msg = "韩非之要吃早饭了。。。";
        System.out.println(msg);
        super.setChanged();
        super.notifyObservers(msg);
    }

    public void haveFun() {
        String msg = "韩非之要吃娱乐了。。。";
        System.out.println(msg);
        super.setChanged();
        super.notifyObservers(msg);
    }

}
