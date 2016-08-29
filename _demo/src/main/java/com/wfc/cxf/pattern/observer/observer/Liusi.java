package com.wfc.cxf.pattern.observer.observer;

import java.util.Observable;
import java.util.Observer;

public class Liusi implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Liusi:开始汇报");
        this.happy(arg.toString());
        System.out.println("Liusi:汇报完毕");
    }

    private void happy(String context) {
        System.out.println("Liusi:因为" + context + "我快乐啊");
    }
}
