package com.example.eggtimer;

/**
 * This interface is to be able to listen to the egg timer
 *
 * @author CKN
 * @version 1.1
 * @since 1.1
 */
public interface EggTimerListener {
    public void onCountDown(long timeLeft);
    public void onEggTimerStopped();
}
