package com.a3house.appmedica.appmedica;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class ActivityNuevaVisita extends AppCompatActivity implements
        View.OnClickListener {

    EditText edtFecha, edtHora;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_visita);

        edtFecha=findViewById(R.id.edtFecha);
        edtHora=findViewById(R.id.edtHora);

        edtFecha.setOnClickListener(this);
        edtHora.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == edtFecha) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            String sdayOfMonth = (dayOfMonth<10)?"0"+dayOfMonth:String.valueOf(dayOfMonth);
                            String smonthOfYear = (monthOfYear<10)?"0"+(monthOfYear+1):String.valueOf(monthOfYear+1);

                            edtFecha.setText(sdayOfMonth + "/" + (smonthOfYear) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == edtHora) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            String sminute = (minute<10)?"0"+minute:String.valueOf(minute);

                            edtHora.setText(hourOfDay + ":" + sminute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

}
