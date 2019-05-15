package com.example.realmbueno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        final RealmConfiguration configuration = new RealmConfiguration.Builder().name("sample.realm").schemaVersion(3).migration(new RealMigrations()).build();
        Realm.setDefaultConfiguration(configuration);
        Realm.getInstance(configuration);

        Button button = findViewById(R.id.lista);
        Button button1 = findViewById(R.id.anadirpersona);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnadirPersonaActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                startActivity(intent);
            }
        });
    }
}
