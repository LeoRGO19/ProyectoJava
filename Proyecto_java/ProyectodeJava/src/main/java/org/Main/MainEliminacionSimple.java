package org.Main;

import org.Logica.*;

/**
 * Clase principal para demostrar el uso de un torneo de eliminación simple.
 * <p>
 * Este programa crea un torneo de eliminación simple con cuatro participantes,
 * registra un observador para el torneo, inicia el torneo, y muestra el estado y bracket final.
 * </p>
 */
public class MainEliminacionSimple {

    /**
     * Método principal que ejecuta la simulación del torneo de eliminación simple.
     * <p>
     * Crea el torneo, agrega participantes, registra observadores, inicia el torneo
     * y finalmente muestra el estado y bracket del torneo.
     * Maneja posibles excepciones relacionadas con la lógica del torneo y errores generales.
     * </p>
     *
     * @param args argumentos de línea de comando (no usados)
     */
    public static void main(String[] args) {
        try {
            // Obtener instancia del gestor de creadores de torneos
            GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();

            // Crear un torneo de eliminación simple usando el gestor y el creador adecuado
            Creador creador = gestor.obtenerCreador(FormatoTorneo.ELIMINACION_SIMPLE);
            TorneoEliminacionSimple torneo = (TorneoEliminacionSimple) creador.crearTorneo("Torneo Ejemplo", "FUTBOL", 4);

            // Registrar un observador para monitorear eventos del torneo
            torneo.registrarObservador(new RegistradorTorneo());

            // Agregar participantes al torneo
            torneo.agregarParticipante(new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com"));
            torneo.agregarParticipante(new IndividuoParticipante("María", "Gómez", 22, "maria@example.com"));
            torneo.agregarParticipante(new IndividuoParticipante("Carlos", "López", 30, "carlos@example.com"));
            torneo.agregarParticipante(new IndividuoParticipante("Ana", "Martínez", 28, "ana@example.com"));

            // Iniciar el torneo
            torneo.iniciarTorneo();

            // Mostrar estado final del torneo
            torneo.verEstado();

            // Mostrar el bracket final del torneo
            torneo.mostrarBracket();

        } catch (TorneoException e) {
            // Captura errores específicos del torneo y muestra mensaje de error
            System.err.println("Error en el torneo de eliminación simple: " + e.getMessage());
        } catch (Exception e) {
            // Captura cualquier otro error inesperado
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}