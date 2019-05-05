package com.example.realmbueno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import io.realm.Realm;

public class AnadirPersonaActivity extends AppCompatActivity {
    private String genero;
    Spinner sGenero;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_persona);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        String[] arrayGenero = new String[] {
                "Hombre", "Mujer", "Otro"};
        sGenero = (Spinner) findViewById(R.id.addpersonGenero);
        ArrayAdapter<String> spinneroptionsGenero = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayGenero);
        spinneroptionsGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sGenero.setAdapter(spinneroptionsGenero);

        Button button = findViewById(R.id.crear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona persona = new Persona();
                EditText et = findViewById(R.id.etEdatAñadir);
                persona.edat = Integer.parseInt(et.getText().toString());
                et = findViewById(R.id.etNombreAñadir);
                persona.nombre = et.getText().toString();
                et = findViewById(R.id.etApellidoAñadir);
                persona.apellido = et.getText().toString();
                et = findViewById(R.id.etDniAñadir);
                persona.dni = et.getText().toString();
                genero = String.valueOf(sGenero.getSelectedItem());
                persona.genero = genero;
                realm.insertOrUpdate(persona);
                realm.commitTransaction();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        realm.commitTransaction();
        finish();
        super.onBackPressed();
    }
}
