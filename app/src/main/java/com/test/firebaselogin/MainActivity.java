package com.test.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button dailyChallengeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailyChallengeButton = findViewById(R.id.dailyChallengeButtonID);
        dailyChallengeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dailyChallengeButtonID:
                startActivity(new Intent(MainActivity.this, DailyChallengeActivity.class));
                break;
        }
    }
}