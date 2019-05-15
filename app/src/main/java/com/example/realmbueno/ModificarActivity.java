package com.example.realmbueno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import io.realm.Realm;
import io.realm.RealmResults;

public class ModificarActivity extends AppCompatActivity {
    EditText etName, etSurname, etAge, etGender;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);

        String[] opcionesSpinner = new String[] {
                "Hombre", "Mujer", "Otro" };
        Spinner spinner = findViewById(R.id.etGender);
        ArrayAdapter<String> spinneroptions = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcionesSpinner);
        spinneroptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneroptions);


        final String nom = getIntent().getStringExtra("ModificarNombre");
        final String edat = getIntent().getStringExtra("ModificarEdat");
        final String genere = getIntent().getStringExtra("ModificarGenere");

        for (int i = 0; i < opcionesSpinner.length; i++) {
            if (opcionesSpinner[i].equals(genere)){
                spinner.setSelection(i);
            }
        }

        etName.setText(nom);

        etAge.setText(edat);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = String.valueOf(parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String dni = getIntent().getStringExtra("ModificarId");

        Realm.init(this);

        final Realm realm = Realm.getDefaultInstance();


        findViewById(R.id.modificar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();

                Persona persona = new Persona(dni, etName.getText().toString(), gender, Integer.parseInt((etAge.getText().toString())));
                realm.insertOrUpdate(persona);

                realm.commitTransaction();

                finish();
            }
        });

        findViewById(R.id.eliminar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();

                RealmResults<Persona> eliminar = realm.where(Persona.class)
                        .equalTo("dni", dni)
                        .findAll();

                eliminar.deleteAllFromRealm();

                realm.commitTransaction();

                finish();
            }
        });

    }


}
