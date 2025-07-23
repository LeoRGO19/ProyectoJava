package org.Logica;

/**
 * Representa un participante individual en un torneo.
 * Extiende la clase {@link Participante} agregando información específica como apellido, edad y puntaje.
 */
public class IndividuoParticipante extends Participante {
    private String apellido;
    private int edad;
    private int puntaje;

    /**
     * Crea un participante individual con nombre, apellido, edad y contacto.
     * Inicializa el puntaje en 0.
     *
     * @param nombre   El nombre del participante.
     * @param apellido El apellido del participante. No puede ser nulo ni vacío.
     * @param edad     La edad del participante. Debe estar entre 18 y 100 años inclusive.
     * @param contacto Información de contacto del participante.
     * @throws TorneoException Si el apellido es nulo o vacío, o si la edad está fuera del rango permitido.
     */
    public IndividuoParticipante(String nombre, String apellido, int edad, String contacto) throws TorneoException {
        super(nombre, contacto);

        if (apellido == null || apellido.trim().isEmpty()) {
            throw new TorneoException("El apellido del participante no puede ser nulo o vacío.");
        }
        String edadStr = String.valueOf(edad);
        if (edadStr.trim().isEmpty()) {
            throw new TorneoException("La edad no puede ser nula o vacía.");
        }
        if (edad < 18 || edad > 100) {
            throw new TorneoException("La edad del participante debe ser mayor de 18 y no mayor a 100");
        }
        this.apellido = apellido;
        this.edad = edad;
        this.puntaje = 0;
    }

    /**
     * Obtiene el puntaje actual del participante.
     *
     * @return El puntaje del participante.
     */
    @Override
    public int obtenerPuntaje() {
        return puntaje;
    }

    /**
     * Establece el puntaje del participante.
     *
     * @param puntaje El nuevo puntaje a asignar.
     */
    public void establecerPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Obtiene el apellido del participante.
     *
     * @return El apellido.
     */
    public String obtenerApellido() {
        return apellido;
    }

    /**
     * Obtiene la edad del participante.
     *
     * @return La edad.
     */
    public int obtenerEdad() {
        return edad;
    }

    /**
     * Representación en texto del participante, incluyendo nombre, apellido y edad.
     *
     * @return Una cadena con el formato: "nombre apellido de edad años".
     */
    @Override
    public String toString() {
        return nombre + " " + apellido + " de " + edad + " años";
    }
}