package com.test.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DailyChallengeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button closeButton;
    private TextView timeCounterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenge);

        closeButton = findViewById(R.id.closeButtonID);
        timeCounterTextView = findViewById(R.id.timeCounterTextViewID);

        closeButton.setOnClickListener(this);
        timeCounter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.closeButtonID:
                onBackPressed();
                break;
        }
    }

    private void timeCounter(){
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeCounterTextView.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //timeCounterTextView.setText("done!");
                timeCounter();
            }

        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}