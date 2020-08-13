package com.test.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.test.firebaselogin.updateActivity.ProfileUpdateActivity;

public class ProfileCreateActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_create);

        saveButton = findViewById(R.id.profileCreateSaveButtonID);

        saveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profileCreateSaveButtonID:
                startActivity(new Intent(ProfileCreateActivity.this, ProfileUpdateActivity.class));
                finish();
                break;
        }
    }
}