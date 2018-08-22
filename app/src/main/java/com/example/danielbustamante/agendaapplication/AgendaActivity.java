package com.example.danielbustamante.agendaapplication;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AgendaActivity extends AppCompatActivity implements View.OnClickListener {

    Button bfecha, bguardar;
    EditText efecha, enombre, epass,erepass, email ;
    private  int dia, mes, ano;
    private TextView tout;
    private String sexo = "Masculino", ciudad="";
    private CheckBox chdormir, chultimate, chcine, chcorrer;

    Spinner combociudaddes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        enombre = findViewById(R.id.enombre);
        epass = findViewById(R.id.epass);
        erepass = findViewById(R.id.erepass);
        email = findViewById(R.id.email);
        bfecha = (Button)findViewById(R.id.bfecha);
        efecha = (EditText)findViewById(R.id.efecha);
        chdormir =findViewById(R.id.chdormir);
        chultimate = findViewById(R.id.chultimate);
        chcine = findViewById(R.id.chcine);
        chcorrer =findViewById(R.id.chcorrer);
        tout = findViewById(R.id.tout);
        bfecha.setOnClickListener(this);
        bguardar =findViewById(R.id.bguardar);
        combociudaddes = findViewById(R.id.spciudad);


        ArrayAdapter<CharSequence>  adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_ciudades,android.R.layout.simple_spinner_item);
        combociudaddes.setAdapter(adapter);
        combociudaddes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                ciudad = (adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       bguardar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String nombre, pass, repass, fn, eemail, ciudadd, hobbies="";
               nombre = enombre.getText().toString();
               pass = epass.getText().toString();
               repass = erepass.getText().toString();
               eemail = email.getText().toString();
               fn=efecha.getText().toString();
               ciudadd=ciudad;

               if (nombre.equals("") || pass.equals("") || repass.equals("") ||ciudadd.equals("")||fn.equals("")|| eemail.equals("")) {
                   Toast.makeText(getApplicationContext(),"Digite todos los campos", Toast.LENGTH_SHORT).show();
               }else {
                   if (pass.equals(repass)){
                       if (chcine.isChecked()) hobbies = hobbies + " Cine";
                       if (chdormir.isChecked()) hobbies = hobbies + " Dormir";
                       if (chcorrer.isChecked()) hobbies = hobbies + " Correr";
                       if (chultimate.isChecked()) hobbies = hobbies + " Ultimate";

                       tout.setText("Nombre: " + nombre + "\nE-mail: "+ eemail  + "\nFecha nacimiento:"+ fn +
                               "\nCiudad nacimiento: "+ ciudad +"\nSexo: " + sexo   + "\nHobbies: "+ hobbies);

                   }else {
                       Toast.makeText(getApplicationContext(),"Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                   }
               }




           }
       });

    }

    @Override
    public void onClick(View v) {
        if (v == bfecha) {
            final Calendar  c= Calendar.getInstance();
            dia =c.get(Calendar.DAY_OF_MONTH);
            mes =c.get(Calendar.MONTH);
            ano =c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                    efecha.setText(day+"/"+(month + 1)+"/"+ year );

                }
            }
            ,dia, mes,ano);

            datePickerDialog.show();

        }



    }

    public void radioButtonClicked(View view) {
        int id  = view.getId();

        if (id== R.id.rbm){
            sexo ="Masculino";
        }else {
            sexo = " Femenino";
        }
        }
}
