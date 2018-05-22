package com.a3house.appmedica.appmedica;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import java.util.Calendar;

public class ActivityNuevaVisita extends AppCompatActivity implements
        View.OnClickListener {

    private EditText edtFecha, edtHora, edtLugar, edtNotas;
    private Button btnGuardar;
    private RadioGroup rdMedico;
    int dia, mes, anyo, hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_visita);

        edtFecha=findViewById(R.id.edtFecha);
        edtHora=findViewById(R.id.edtHora);
        edtLugar=findViewById(R.id.edtLugar);
        edtNotas=findViewById(R.id.edtNotas);
        rdMedico=findViewById(R.id.radioMedico);
        btnGuardar=findViewById(R.id.btnGuardarVisita);

        edtFecha.setOnClickListener(this);
        edtHora.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int mYear, mMonth, mDay, mHour, mMinute;

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

                            dia = dayOfMonth;
                            mes = monthOfYear+1;
                            anyo = year;

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

                            hora = hourOfDay;
                            minuto = minute;
                            String sminute = (minute<10)?"0"+minute:String.valueOf(minute);
                            edtHora.setText(hourOfDay + ":" + sminute);
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
        if (v == btnGuardar) {
            int radioButtonId = rdMedico.getCheckedRadioButtonId();
            RadioButton radiobutton = findViewById(radioButtonId);
            GestionVisita.crearVisita(dia, mes, anyo, hora, minuto, edtLugar.getText().toString(), radiobutton.getText().toString(), edtNotas.getText().toString());
            this.finish();
        }
    }

}
