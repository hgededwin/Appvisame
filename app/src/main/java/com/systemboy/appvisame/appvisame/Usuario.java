package com.systemboy.appvisame.appvisame;

/**
 * Created by Daniel on 02/04/2015.
 */
public class Usuario {
    protected String name;
    protected String surname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    protected String email;
    protected String titulo;
}
