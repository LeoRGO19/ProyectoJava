package org.Logica;

/**
 * Clase abstracta que representa un participante en un torneo.
 * <p>
 * Contiene información básica común a todos los participantes, como nombre y contacto,
 * y define un método abstracto para obtener el puntaje del participante.
 * </p>
 */
public abstract class Participante {
    /** Variable utilizada para almacenar el nombre */
    protected String nombre;

    /**  Variable que será utilizada para almacenar el contacto */
    protected String contacto;

    /**
     * Crea un participante con un nombre y un contacto.
     *
     * @param nombre  El nombre del participante.
     * @param contacto Información de contacto del participante.
     */
    public Participante(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }

    /**
     * Obtiene el nombre del participante.
     *
     * @return El nombre.
     */
    public String obtenerNombre() {
        return nombre;
    }

    /**
     * Obtiene la información de contacto del participante.
     *
     * @return El contacto.
     */
    public String obtenerContacto() {
        return contacto;
    }

    /**
     * Método abstracto que debe ser implementado para obtener el puntaje
     * del participante.
     *
     * @return El puntaje del participante.
     */
    public abstract int obtenerPuntaje();

    /**
     * Representación en texto del participante.
     *
     * @return El nombre del participante.
     */
    @Override
    public String toString() {
        return nombre;
    }
}