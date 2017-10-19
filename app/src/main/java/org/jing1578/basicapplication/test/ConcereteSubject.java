package org.jing1578.basicapplication.test;

import java.util.ArrayList;

/**
 * Created by jing1578 on 2017/10/11 11:26.
 */

public class ConcereteSubject  extends Subject{
    private  String  state;

    public void change(String state){
        this.state = state;
        System.out.println("ConcereteSubject状态:"+state);
        notifyUpdate(state);
    }

}
