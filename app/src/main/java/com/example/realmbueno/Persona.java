package com.example.realmbueno;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Persona extends RealmObject {

    @PrimaryKey
    String dni;


    String nombre;
    String apellido;
    int edat;
    String genero;


}
