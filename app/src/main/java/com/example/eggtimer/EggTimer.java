package com.example.eggtimer;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.collection.ArraySet;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is a timer that counts down from the selected time
 *
 * @author CKN
 * @version 1.1
 * @since 1.1
 */
public class EggTimer extends Thread {
    private boolean started;
    private int timeLeft;
    private ArrayList<EggTimerListener> listeners;


    /**
     * Constructor that sets the time left
     *
     * @version 1.1
     * @since 1.1
     * @param mins the time im minutes to count from
     */
    public EggTimer(int mins){
        timeLeft = mins*60;
        listeners = new ArrayList<EggTimerListener>();
    }


    /**
     * Method that gets the time left in sec
     *
     * @version 1.1
     * @since 1.1
     */
    public int getTimeLeft(){
        return timeLeft;
    }
    public boolean isRunning(){
        return started;
    }

    /**
     * Method that begins to count down
     *
     * @version 1.1
     * @since 1.1
     */
    public void run(){
        started = true;
        while(timeLeft > 0){
            try {
                timeLeft--;

                // Tells all the listener that it have counted down one
                for (EggTimerListener l : listeners){
                    l.onCountDown(timeLeft);
                }
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        started = false;
        // Tells all the listener that the timer have stopped
        for (EggTimerListener l : listeners){
            l.onEggTimerStopped();
        }
    }

    /**
     * Method that adds a listener
     *
     * @version 1.1
     * @since 1.1
     */
    public void addListener(EggTimerListener listener) {
        listeners.add(listener);
    }

    /**
     * Method that begins to count down
     *
     * @version 1.1
     * @since 1.1
     */
    public void removeListener(EggTimerListener listener) {
        listeners.remove(listener);
    }

}
