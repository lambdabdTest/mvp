package com.test.firebaselogin.updateActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.test.firebaselogin.MainActivity;
import com.test.firebaselogin.ProfileCreateActivity;
import com.test.firebaselogin.R;
import java.util.ArrayList;
import java.util.List;

public class ProfileUpdateActivity extends AppCompatActivity implements View.OnClickListener, DatePresenter {

    private List<String> districtList;
    private Spinner spinnerDistrict;
    private String districtName;

    private LinearLayout updateLayout, tagLayout;
    private ConstraintLayout confirmLayout;

    private CardView datePicker;
    private TextView dateTextView;
    private ImageView backButton, nextButton, tagBackButton, checkButton, confirmBackButton;

    private Button confirmButton, cancelButton;

    private String birthDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        districtList = new ArrayList<>();
        spinnerDistrict = findViewById(R.id.spinnerDistrictID);
        datePicker = findViewById(R.id.cardViewDatePickerID);
        dateTextView = findViewById(R.id.dateTextViewID);

        backButton = findViewById(R.id.updateBackButtonID);
        nextButton = findViewById(R.id.updateNextButtonID);
        tagBackButton = findViewById(R.id.tagLayoutBackButtonID);
        checkButton  = findViewById(R.id.updateCheckButtonID);
        confirmBackButton = findViewById(R.id.confirmBackButtonID);

        updateLayout = findViewById(R.id.updateLayoutID);
        tagLayout = findViewById(R.id.tagLayoutID);
        confirmLayout = findViewById(R.id.confirmLayoutID);

        confirmButton = findViewById(R.id.updateConfirmButtonID);
        cancelButton = findViewById(R.id.updateCancelButtonID);

        datePicker.setOnClickListener(this);
        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        tagBackButton.setOnClickListener(this);
        checkButton.setOnClickListener(this);
        confirmBackButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        setSpinner();
    }

    private void setSpinner() {
        districtList.add("বরগুনা");
        districtList.add("বরিশাল");
        districtList.add("ভোলা");
        districtList.add("ঝালকাঠি");
        districtList.add("পটুয়াখালি");
        districtList.add("পিরোজপুর");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,districtList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(spinnerAdapter);
        districtName = spinnerDistrict.getSelectedItem().toString();
        spinnerDistrict.setPrompt(districtName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardViewDatePickerID:
                DatePickerDialogBox pickerDialogBox;
                if (!birthDate.isEmpty()){
                    pickerDialogBox = new DatePickerDialogBox(ProfileUpdateActivity.this,
                            birthDate, ProfileUpdateActivity.this);
                }else {
                    pickerDialogBox = new DatePickerDialogBox(ProfileUpdateActivity.this,
                            ProfileUpdateActivity.this);
                }

                pickerDialogBox.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                pickerDialogBox.show();
                break;

            case R.id.updateNextButtonID:
                updateLayout.setVisibility(View.GONE);
                tagLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.tagLayoutBackButtonID:
                tagLayout.setVisibility(View.GONE);
                updateLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.updateBackButtonID:
                onBackPressed();
                break;

            case R.id.updateCheckButtonID:
                confirmLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.confirmBackButtonID:
                confirmLayout.setVisibility(View.GONE);
                break;

            case R.id.updateConfirmButtonID:
                startActivity(new Intent(ProfileUpdateActivity.this, MainActivity.class));
                finish();
                break;

            case R.id.updateCancelButtonID:
                startActivity(new Intent(ProfileUpdateActivity.this, ProfileCreateActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void date(String date) {
        birthDate = date;
        dateTextView.setText(date);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}