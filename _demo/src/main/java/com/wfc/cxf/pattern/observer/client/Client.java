package com.wfc.cxf.pattern.observer.client;

import java.util.Observer;

import com.wfc.cxf.pattern.observer.observable.HanFeiZi;
import com.wfc.cxf.pattern.observer.observer.Lisi;
import com.wfc.cxf.pattern.observer.observer.Liusi;
import com.wfc.cxf.pattern.observer.observer.WangSi;

public class Client {
    public static void main(String[] args) {
        Observer lisi = new Lisi();
        Observer wangsi = new WangSi();
        Observer liusi = new Liusi();
        HanFeiZi hanfeizi = new HanFeiZi();
        hanfeizi.addObserver(lisi);
        hanfeizi.addObserver(wangsi);
        hanfeizi.addObserver(liusi);
        hanfeizi.haveBreakfast();
        hanfeizi.haveFun();
    }

}
