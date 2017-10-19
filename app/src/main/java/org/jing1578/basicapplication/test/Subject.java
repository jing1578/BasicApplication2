package org.jing1578.basicapplication.test;

import java.util.ArrayList;

/**
 * Created by jing1578 on 2017/10/11 11:25.
 */

public class Subject {
    private ArrayList<Observer> list=new ArrayList<>();

    public void attach(Observer observer){
        list.add(observer);
    }

    public  void delete(Observer observer){
        list.remove(observer);
    }

    public  void notifyUpdate(String state){
        for (Observer observer:list){
            observer.update(state);
        }
    }

}
