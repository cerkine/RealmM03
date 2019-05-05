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


    public Persona(String dni, String nombre, String apellido, String genero, int edat) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edat = edat;
    }
    public Persona(){

    }
}
