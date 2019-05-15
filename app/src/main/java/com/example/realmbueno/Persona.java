package com.example.realmbueno;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Persona extends RealmObject {

    @PrimaryKey
    String dni;

    String fullName;
    int edat;
    String genero;


    public Persona(String dni, String fullName,  String genero, int edat) {
        this.dni = dni;
        this.fullName = fullName;

        this.genero = genero;
        this.edat = edat;
    }
    public Persona(){

    }
}
