package org.Logica;

public abstract class Participante {
    protected String nombre;
    protected String contacto;

    public Participante(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerContacto() {
        return contacto;
    }

    public abstract int obtenerPuntaje();

    @Override
    public String toString() {
        return nombre;
    }
}