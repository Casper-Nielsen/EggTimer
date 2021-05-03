package com.example.eggtimer;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.collection.ArraySet;

import java.util.ArrayList;
import java.util.List;

public class EggTimer extends Thread {
    private boolean started;
    private int timeLeft;
    private ArrayList<EggTimerListener> listeners;
    
    public EggTimer(int mins){
        timeLeft = mins*60;
        listeners = new ArrayList<EggTimerListener>();
    }
    public int getTimeLeft(){
        return timeLeft;
    }
    public boolean isRunning(){
        return started;
    }
    public void run(){
        started = true;
        while(timeLeft > 0){
            try {
                timeLeft--;
                for (EggTimerListener l : listeners){
                    l.onCountDown(timeLeft);
                }
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        started = false;
        for (EggTimerListener l : listeners){
            l.onEggTimerStopped();
        }
    }

    public void addListener(EggTimerListener listener) {
        listeners.add(listener);
    }


    public void removeListener(EggTimerListener listener) {
        listeners.remove(listener);
    }

}
