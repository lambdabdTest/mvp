package com.test.firebaselogin.updateActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.test.firebaselogin.R;

public class DatePickerDialogBox extends Dialog implements View.OnClickListener {

    private Button okButton;
    private DatePicker datePicker;
    private DatePresenter datePresenter;
    private String date = "";

    public DatePickerDialogBox(@NonNull Context context, String date, ProfileUpdateActivity activity) {
        super(context);
        this.date = date;
        datePresenter = activity;
    }

    public DatePickerDialogBox(@NonNull Context context, ProfileUpdateActivity activity) {
        super(context);
        datePresenter = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.date_picker_dialog_box);

        okButton = findViewById(R.id.datePickerOkButtonID);
        datePicker = findViewById(R.id.datePickerID);

        if (!date.isEmpty()){
            String[] dateSplit = date.split("/");
            datePicker.updateDate(Integer.parseInt(dateSplit[2]),
                    Integer.parseInt(dateSplit[1])-1, Integer.parseInt(dateSplit[0]));
        }

        okButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.datePickerOkButtonID){
            int month = datePicker.getMonth()+1;
            String date = datePicker.getDayOfMonth() +"/"+  month +"/"+datePicker.getYear();
            Log.e("date", "onClick: "+date);
            datePresenter.date(date);
            dismiss();
        }
    }
}
