package com.example.eggtimer;


/**
 * This class is a timer that counts down from the selected time
 *
 * @author CKN
 * @version 1.2
 * @since 1.2
 */
public class EggTimerPresenter implements EggTimerListener {
    private View v;
    private EggTimer eggTimer;
    /**
     * Constructor that sets the view
     *
     * @version 1.2
     * @since 1.2
     * @param v the view that will be controlled
     */
    public EggTimerPresenter(View v){
        this.v = v;
    }

    /**
     * Method that stops the timer
     *
     * @version 1.2
     * @since 1.2
     */
    public void stop(){
        eggTimer.removeListener(this);
        eggTimer = null;
    }

    /**
     * Method that starts the timer
     *
     * @version 1.2
     * @since 1.2
     * @param timeToCook the amount of time the timer will run
     */
    public void start(int timeToCook){
        eggTimer = new EggTimer(timeToCook);
        eggTimer.addListener(this);
        eggTimer.start();
    }
    /**
     * Method that updates the time remaining
     *
     * @version 1.2
     * @since 1.2
     * @param timeLeft the amount of time left in sec
     */
    @Override
    public void onCountDown(long timeLeft) {
        v.onCountDown(timeLeft);
    }

    /**
     * Method that shows that the timer have stopped
     *
     * @version 1.2
     * @since 1.2
     */
    @Override
    public void onEggTimerStopped() {
        v.onEggTimerStopped();
    }


    /**
     * This interface is to be able to be controlled by the presenter
     *
     * @author CKN
     * @version 1.2
     * @since 1.2
     */
    public interface View{
        void onCountDown(long time);
        void onEggTimerStopped();
    }
}
