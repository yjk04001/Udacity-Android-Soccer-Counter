package com.example.youss.courtcounter;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 27000000;

    private TextView mTextViewCountdown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private Boolean mTimerRunning = false;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mEndTime;

    int currentHalf = 1;
    int scoreHome;
    int scoreVisitor;
    int shotsHome;
    int shotsVisitor;
    int sotHome;
    int sotVisitor;
    final int INCREMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountdown = findViewById(R.id.countdown);

        mButtonStartPause = findViewById(R.id.countdown_start_pause);
        mButtonReset = findViewById(R.id.countdown_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();

        displayScoreForHome(0);
    }

    /**
     * Starts timer.
     */
    private void startTimer(){
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateButtons();
                currentHalf = 2;
                displayCurrentHalf(currentHalf);
            }
        }.start();

        mTimerRunning = true;
        updateButtons();

    }

    /**
     * Pauses timer.
     */
    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateButtons();
    }

    /**
     * Resets timer to starting point.
     */
    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        updateButtons();
    }

    /**
     * Updates countdown textview for each tick of timer.
     */
    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mTextViewCountdown.setText(timeLeftFormatted);
    }

    /**
     * Sets the visibility and text of timer buttons.
     */
    private void updateButtons(){
        if(mTimerRunning){
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        }else{
            mButtonStartPause.setText("Start");

            if(mTimeLeftInMillis < 1000){
                mButtonStartPause.setVisibility(View.INVISIBLE);
            }else{
                mButtonStartPause.setVisibility(View.VISIBLE);
            }

            if(mTimeLeftInMillis < START_TIME_IN_MILLIS){
                mButtonReset.setVisibility(View.VISIBLE);
            }else{
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }


    /**
     * Displays the current half.
     */
    public void displayCurrentHalf(int half){
        TextView current_half = findViewById(R.id.current_half);
        current_half.setText(String.valueOf(half));
    }

    /**
     * Displays the given score for home team.
     */
    public void displayScoreForHome(int score) {
        TextView scoreView = findViewById(R.id.home_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for visitor.
     */
    public void displayScoreForVisitor(int score) {
        TextView scoreView = findViewById(R.id.visitor_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays goals for home team.
     */
    public void displayGoalsStatForHome(int goals) {
        TextView goalsView = findViewById(R.id.home_goals);
        goalsView.setText(String.valueOf(goals));
    }

    /**
     * Displays goals for visitor.
     */
    public void displayGoalsStatForVisitor(int goals) {
        TextView goalsView = findViewById(R.id.visitor_goals);
        goalsView.setText(String.valueOf(goals));
    }

    /**
     * Displays total shots for home team.
     */
    public void displayShotsStatForHome(int shots) {
        TextView goalsView = findViewById(R.id.home_shots);
        goalsView.setText(String.valueOf(shots));
    }

    /**
     * Displays total shots for visitor.
     */
    public void displayShotsStatForVisitor(int shots) {
        TextView goalsView = findViewById(R.id.visitor_shots);
        goalsView.setText(String.valueOf(shots));
    }

    /**
     * Displays on target shots for home team.
     */
    public void displayOnTargetStatForHome(int sot) {
        TextView goalsView = findViewById(R.id.home_sot);
        goalsView.setText(String.valueOf(sot));
    }

    /**
     * Displays on target shots for visitor.
     */
    public void displayOnTargetStatForVisitor(int sot) {
        TextView goalsView = findViewById(R.id.visitor_sot);
        goalsView.setText(String.valueOf(sot));
    }

    /**
     * Increases the score and goals for the home team by 1.
     */
    public void add_1_goal_home(View view){
        scoreHome += INCREMENT;
        displayScoreForHome(scoreHome);
        displayGoalsStatForHome(scoreHome);
    }

    /**
     * Increases the score and goals for the visitor by 1.
     */
    public void add_1_goal_visitor(View view){
        scoreVisitor += INCREMENT;
        displayScoreForVisitor(scoreVisitor);
        displayGoalsStatForVisitor(scoreVisitor);
    }

    /**
     * Increases total shots for the home team by 1.
     */
    public void add_1_shot_home(View view){
        shotsHome += INCREMENT;
        displayShotsStatForHome(shotsHome);
    }

    /**
     * Increases total shots for the visitor by 1.
     */
    public void add_1_shot_visitor(View view){
        shotsVisitor += INCREMENT;
        displayShotsStatForVisitor(shotsVisitor);
    }

    /**
     * Increases on target shots for the home team by 1.
     */
    public void add_1_sot_home(View view){
        sotHome += INCREMENT;
        displayOnTargetStatForHome(sotHome);
    }

    /**
     * Increases on target shots for the visitor by 1.
     */
    public void add_1_sot_visitor(View view){
        sotVisitor += INCREMENT;
        displayOnTargetStatForVisitor(sotVisitor);
    }

    /**
     * Resets the scores, stats, half, and timer.
     */
    public void reset_score(View view){
        scoreHome = 0;
        scoreVisitor = 0;
        shotsHome = 0;
        shotsVisitor = 0;
        sotHome = 0;
        sotVisitor = 0;
        currentHalf = 1;
        displayScoreForVisitor(scoreVisitor);
        displayGoalsStatForVisitor(scoreVisitor);
        displayShotsStatForVisitor(shotsVisitor);
        displayOnTargetStatForVisitor(sotVisitor);
        displayScoreForHome(scoreHome);
        displayGoalsStatForHome(scoreHome);
        displayShotsStatForHome(shotsHome);
        displayOnTargetStatForHome(sotHome);
        displayCurrentHalf(currentHalf);
        if(mTimeLeftInMillis < START_TIME_IN_MILLIS){
            pauseTimer();
            resetTimer();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimerRunning);
        outState.putLong("endTime", mEndTime);
        outState.putInt("scoreHome", scoreHome);
        outState.putInt("scoreVisitor", scoreVisitor);
        outState.putInt("shotsHome", shotsHome);
        outState.putInt("shotsVisitor", shotsVisitor);
        outState.putInt("sotHome", sotHome);
        outState.putInt("sotVisitor", sotVisitor);
        outState.putInt("currentHalf", currentHalf);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning = savedInstanceState.getBoolean("timerRunning");
        updateCountDownText();
        updateButtons();

        if(mTimerRunning){
            mEndTime = savedInstanceState.getLong("endTime");
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            startTimer();
        }

        scoreHome = savedInstanceState.getInt("scoreHome");
        scoreVisitor = savedInstanceState.getInt("scoreVisitor");
        shotsHome = savedInstanceState.getInt("shotsHome");
        shotsVisitor = savedInstanceState.getInt("shotsVisitor");
        sotHome = savedInstanceState.getInt("sotHome");
        sotVisitor = savedInstanceState.getInt("sotVisitor");
        currentHalf = savedInstanceState.getInt("currentHalf");
        displayScoreForHome(scoreHome);
        displayScoreForVisitor(scoreVisitor);
        displayGoalsStatForHome(scoreHome);
        displayGoalsStatForVisitor(scoreVisitor);
        displayShotsStatForHome(shotsHome);
        displayShotsStatForVisitor(shotsVisitor);
        displayOnTargetStatForHome(sotHome);
        displayOnTargetStatForVisitor(sotVisitor);
        displayCurrentHalf(currentHalf);

    }
}
