package com.wfc.cxf.pattern.observer.observer;

import java.util.Observable;
import java.util.Observer;

public class WangSi implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("wangsi:开始汇报");
        this.cry(arg.toString());
        System.out.println("wangsi:汇报完毕");
    }

    private void cry(String context) {
        System.out.println("wangsi:因为" + context + "我悲伤啊");
    }

}
