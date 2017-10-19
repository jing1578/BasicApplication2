package org.jing1578.basicapplication.test;

/**
 * Created by jing1578 on 2017/10/11 11:32.
 */

public class ConcereteObserver implements Observer {
    private String state;

    @Override
    public void update(String state) {
        this.state=state;
        System.out.println("ConcereteObserver状态:"+state);
    }
}
