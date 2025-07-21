package org.Logica;

import org.w3c.dom.ls.LSOutput;

/**
 * Implementación de {@link ObservadorTorneo} que registra eventos de un torneo
 * imprimiendo mensajes en la consola.
 * <p>
 * Este observador escucha diferentes tipos de eventos como la adición o eliminación de participantes,
 * el inicio o finalización del torneo, generación de enfrentamientos y actualización de resultados.
 * </p>
 */

public class RegistradorTorneo implements ObservadorTorneo {
    /** constructor por defecto */
    public RegistradorTorneo(){}
    /**
     * Procesa la notificación de un evento del torneo imprimiendo detalles en la consola.
     *
     * @param evento El evento del torneo que se ha producido.
     */

    @Override
    public void actualizar(EventoTorneo evento) {
        // System.out.println("Evento: " + evento.getTipo() + " en torneo " + evento.getTorneo().getClass().getSimpleName());
        switch (evento.getTipo()) {
            case PARTICIPANTE_AGREGADO:
                // System.out.println("Participante añadido: " + ((Participante) evento.getDatos()).obtenerNombre());
                break;
            case PARTICIPANTE_ELIMINADO:
                // System.out.println("Participante eliminado: " + ((Participante) evento.getDatos()).obtenerNombre());
                break;
            case TORNEO_INICIADO:
                // System.out.println("Torneo iniciado");
                break;
            case ENFRENTAMIENTOS_GENERADOS:
                // System.out.println("Enfrentamientos generados: " + evento.getDatos());
                break;
            case RESULTADOS_ACTUALIZADOS:
                // System.out.println("Resultados actualizados: " + evento.getDatos());
                break;
            case TORNEO_FINALIZADO:
                String mensaje = (evento.getDatos() != null) ?
                        "Torneo finalizado: Ganador: " + ((Participante) evento.getDatos()).obtenerNombre() :
                        "Torneo finalizado: Sin ganador";
                System.out.println(mensaje);
                break;
        }
    }
}