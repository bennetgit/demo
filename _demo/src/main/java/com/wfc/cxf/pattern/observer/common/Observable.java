package com.wfc.cxf.pattern.observer.common;


/**
 * 被观察者
 * 
 * @author fcw
 *
 */
public interface Observable {

    void addObserver(Observer observer);

    void deleteObserver(Observer observer);

    void notifyObserver(String context);
}
