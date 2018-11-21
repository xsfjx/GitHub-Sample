package com.githubsample.factory.real;

import android.content.Context;

import com.githubsample.factory.AbstractDataBus;

public class RealDataBus extends AbstractDataBus {

    private final static Object lockObj = new Object();
    private static RealDataBus instance;

    public RealDataBus() {
        super();
    }

    public RealDataBus getInstance() {
        if (instance == null) {
            synchronized (lockObj) {
                instance = new RealDataBus();
            }
        }
        return instance;
    }

//    public void initail (Context context) {
//        add(this);
//    }


    @Override
    protected void restart(){
        // TODO: 11/9/2018
    }
}
