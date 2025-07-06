package org.Logica;

public class IndividuoParticipante extends Participante {
    private String apellido;
    private int edad;
    private int puntaje;

    public IndividuoParticipante(String nombre, String apellido, int edad, String contacto) {
        super(nombre, contacto);
        this.apellido = apellido;
        this.edad = edad;
        this.puntaje = 0;
    }

    @Override
    public int obtenerPuntaje() {
        return puntaje;
    }

    public void establecerPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String obtenerApellido() {
        return apellido;
    }

    public int obtenerEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " de " + edad + " años";
    }
}