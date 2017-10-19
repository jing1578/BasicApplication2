package org.jing1578.basicapplication.test;

import android.content.Context;

/**
 * Created by jing1578 on 2017/10/11 11:34.
 */

public class Test {

    public static void main(String[] args){
        ConcereteSubject concereteSubject=new ConcereteSubject();
        ConcereteObserver concereteObserver=new ConcereteObserver();
        concereteSubject.attach(concereteObserver);
        concereteSubject.change("海阔天空");
    }
}
