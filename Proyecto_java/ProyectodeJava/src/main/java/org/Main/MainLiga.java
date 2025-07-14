package org.Main;

import org.Logica.*;

/**
 * Clase principal para demostrar el uso de un torneo de liga.
 * <p>
 * Este programa crea un torneo de liga con varios equipos, registra un observador,
 * inicia el torneo, genera los enfrentamientos y muestra el estado final del torneo.
 * </p>
 */
public class MainLiga {
    /** Constructor por defecto */
    public MainLiga(){}
    /**
     * Método principal que ejecuta la simulación del torneo de liga.
     * <p>
     * Realiza las siguientes acciones:
     * <ul>
     *   <li>Obtiene el gestor de creadores de torneos.</li>
     *   <li>Crea un torneo de tipo liga con el creador adecuado.</li>
     *   <li>Registra un observador para monitorear eventos del torneo.</li>
     *   <li>Agrega varios equipos como participantes del torneo.</li>
     *   <li>Inicia el torneo y genera los enfrentamientos.</li>
     *   <li>Muestra el estado final del torneo.</li>
     * </ul>
     * <p>
     * Captura y maneja excepciones específicas de la lógica del torneo
     * y errores inesperados, mostrando mensajes apropiados en consola.
     * </p>
     *
     * @param args argumentos de línea de comando (no usados)
     */
    public static void main(String[] args) {
        try {
            // Obtener instancia del gestor de creadores de torneos
            GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();

            System.out.println("\n=== Torneo de Liga ===");

            // Crear un torneo de liga con 8 participantes
            Creador creadorLiga = gestor.obtenerCreador(FormatoTorneo.LIGA);
            TorneoLiga torneoLiga = (TorneoLiga) creadorLiga.crearTorneo("Liga Local", "FUTBOL", 8);

            // Registrar un observador para monitorear el torneo
            torneoLiga.registrarObservador(new RegistradorTorneo());

            // Agregar equipos participantes al torneo
            torneoLiga.agregarParticipante(new Equipo("Equipo 1", "contacto1@example.com"));
            torneoLiga.agregarParticipante(new Equipo("Equipo 2", "contacto2@example.com"));
            torneoLiga.agregarParticipante(new Equipo("Equipo 3", "contacto3@example.com"));
            torneoLiga.agregarParticipante(new Equipo("Equipo 4", "contacto4@example.com"));

            // Iniciar el torneo y generar los enfrentamientos automáticamente
            torneoLiga.iniciarTorneo();

            // Mostrar el estado actual y resultados del torneo
            torneoLiga.verEstado();

        } catch (TorneoException e) {
            // Mostrar errores específicos del torneo
            System.err.println("Error en el torneo de liga: " + e.getMessage());
        } catch (Exception e) {
            // Capturar y mostrar errores inesperados
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}