package com.wfc.cxf.pattern.observer.observer;

import java.util.Observable;
import java.util.Observer;

public class Lisi implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("lisi:开始向老板汇报情况。。。");
        this.reportToShiHuang(arg.toString());
        System.out.println("lisi:汇报完毕，老板很高兴。。。");

    }

    private void reportToShiHuang(String context) {
        System.out.println("lisi:皇帝，韩非之有活动了-->>" + context);
    }

}
