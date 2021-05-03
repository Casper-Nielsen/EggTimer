package com.example.eggtimer;

public class EggTimerPresenter implements EggTimerListener {
    private View v;
    private EggTimer eggTimer;

    public EggTimerPresenter(View v){
        this.v = v;
    }

    public void stop(){
        eggTimer.removeListener(this);
    }

    public void start(int timeToCook){
        eggTimer = new EggTimer(timeToCook);
        eggTimer.addListener(this);
        eggTimer.start();
    }

    @Override
    public void onCountDown(long timeLeft) {
        v.onCountDown(timeLeft);
    }

    @Override
    public void onEggTimerStopped() {
        v.onEggTimerStopped();
    }

    public interface View{
        void onCountDown(long time);
        void onEggTimerStopped();
    }
}
