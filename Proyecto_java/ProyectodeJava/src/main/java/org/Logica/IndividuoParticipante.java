package org.Logica;

public class IndividuoParticipante extends Participante {
    private String apellido;
    private int edad;
    private int puntaje;

    public IndividuoParticipante(String nombre, String apellido, int edad, String contacto)throws TorneoException {
        super(nombre, contacto);
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new TorneoException("El apellido del participante no puede ser nulo o vacío.");
        }
        if (edad < 18 || edad >100) {
            throw new TorneoException("La edad del participante debe ser mayor de 18 y no mayor a 100");
        }
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