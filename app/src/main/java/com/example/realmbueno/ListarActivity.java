package com.example.realmbueno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListarActivity extends AppCompatActivity {
    private ListView lsvData;
    private int less;
    Realm realm;
    Adapter adapter;
    Spinner sGenero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_acivity);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        String[] arraySpinner = new String[] {
                "Entre Edades", "Edat o menor", "Edat o mayor","Genero" };
        Spinner s = (Spinner) findViewById(R.id.appCompatSpinner);
        ArrayAdapter<String> spinneroptions = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinneroptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(spinneroptions);
        String[] arrayGenero = new String[] {
                "Hombre", "Mujer", "Otro"};
        sGenero = (Spinner) findViewById(R.id.Genero);
        ArrayAdapter<String> spinneroptionsGenero = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayGenero);
        spinneroptionsGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sGenero.setAdapter(spinneroptionsGenero);
        lsvData = (ListView) findViewById(R.id.lsvFilms);
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        adapter = new Adapter(personas);
        if(personas.size()>0) lsvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                findViewById(R.id.Genero).setVisibility(View.INVISIBLE);
                findViewById(R.id.edat1).setVisibility(View.INVISIBLE);
                findViewById(R.id.edat2).setVisibility(View.INVISIBLE);
                less = position;
                if (position ==0){
                    findViewById(R.id.edat1).setVisibility(View.VISIBLE);
                    findViewById(R.id.edat2).setVisibility(View.VISIBLE);
                }
                if (position == 1 || position == 2){
                    findViewById(R.id.edat1).setVisibility(View.VISIBLE);
                }
                if (position == 3){
                    findViewById(R.id.Genero).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        findViewById(R.id.buscar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar(less);
                Log.e("pepe","gola");
            }
        });

    }

    private void buscar(int less){
        int edat2 = 0;
        int edat1 = 0;
        TextView tvedat1 = findViewById(R.id.edat1);
        if (!tvedat1.getText().toString().isEmpty()) {
            edat1 = Integer.parseInt(tvedat1.getText().toString());
        }
        TextView tvedat2 = findViewById(R.id.edat2);
        if (!tvedat2.getText().toString().isEmpty()) {
             edat2 = Integer.parseInt(tvedat2.getText().toString());
        }
        RealmResults<Persona> filtrar = realm.where(Persona.class).findAll();
        switch (less){
            case 0:

                 filtrar = realm.where(Persona.class)
                    .between("edat", edat1,edat2)
                    .findAll();

                break;
            case 1:
                filtrar = realm.where(Persona.class)
                        .lessThanOrEqualTo("edat", edat1)
                        .findAll();
                break;
            case 2:
                filtrar = realm.where(Persona.class)
                        .greaterThanOrEqualTo("edat", edat1)
                        .findAll();
                break;
            case 3:
                filtrar = realm.where(Persona.class)
                        .equalTo("genero", String.valueOf(sGenero.getSelectedItem()))
                        .findAll();
                break;
        }
        adapter = new Adapter(filtrar);
        lsvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}
