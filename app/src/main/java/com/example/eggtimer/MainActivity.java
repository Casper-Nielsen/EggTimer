package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity for loading layout resources
 *
 * This activity is used to display the egg timer layout
 *
 * @author CKN
 * @version 1.2
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity implements EggTimerPresenter.View {
    private int mins;
    private EggTimerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new EggTimerPresenter(this);
    }

    /**
     * Method that is for the button click to set the timer for the selected option
     *
     * @version 1.0
     * @since 1.0
     */
    public void onButtonEggSelectedClicked(View view) {
        Button startbtn = findViewById(R.id.startButton);
        TextView time = findViewById(R.id.timer);
        switch (view.getId()) {
            case R.id.softBoiledButton:
                startbtn.setEnabled(true);
                mins = 5;
                time.setText("5:00");
                break;
            case R.id.mediumBoiledButton:
                startbtn.setEnabled(true);
                mins = 7;
                time.setText("7:00");
                break;
            case R.id.hardBoiledButton:
                startbtn.setEnabled(true);
                mins = 10;
                time.setText("10:00");
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
    }

    /**
     * Method that is for the button click to start the timer
     *
     * @version 1.2
     * @since 1.0
     */
    public  void onButtonStartClicked(View view){
        findViewById(R.id.softBoiledButton).setEnabled(false);
        findViewById(R.id.mediumBoiledButton).setEnabled(false);
        findViewById(R.id.hardBoiledButton).setEnabled(false);
        findViewById(R.id.startButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.stopButton).setVisibility(View.VISIBLE);
        presenter.start(mins);

    }

    /**
     * Method that is for the button click to stop the timer
     *
     * @version 1.2
     * @since 1.0
     */
    public void onButtonStopClicked(View view){
        presenter.stop();
        findViewById(R.id.softBoiledButton).setEnabled(true);
        findViewById(R.id.mediumBoiledButton).setEnabled(true);
        findViewById(R.id.hardBoiledButton).setEnabled(true);
        findViewById(R.id.startButton).setVisibility(View.VISIBLE);
        findViewById(R.id.startButton).setEnabled(false);
        findViewById(R.id.stopButton).setVisibility(View.INVISIBLE);
    }

    /**
     * Method that updates the time remaining
     *
     * @version 1.2
     * @since 1.1
     * @param time the amount of time left in sec
     */
    @Override
    public void onCountDown(long time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView)findViewById(R.id.timer)).setText(((int)Math.floor(time/60))+":"+time%60);
            }
        });
    }

    /**
     * Method that shows that the timer have stopped
     *
     * @version 1.1
     * @since 1.1
     */
    @Override
    public void onEggTimerStopped() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.softBoiledButton).setEnabled(true);
                findViewById(R.id.mediumBoiledButton).setEnabled(true);
                findViewById(R.id.hardBoiledButton).setEnabled(true);
                findViewById(R.id.startButton).setVisibility(View.VISIBLE);
                findViewById(R.id.startButton).setEnabled(false);
                findViewById(R.id.stopButton).setVisibility(View.INVISIBLE); }
        });
    }
}