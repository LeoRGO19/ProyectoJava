package org.Main;

import org.Logica.*;
public class MainEliminacionSimple {
    public static void main(String[] args) {
        try {
            GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();

            // Crear torneo de eliminación simple
            Creador creador = gestor.obtenerCreador(FormatoTorneo.ELIMINACION_SIMPLE);
            TorneoEliminacionSimple torneo = (TorneoEliminacionSimple) creador.crearTorneo("Torneo Ejemplo", "FUTBOL", 4);

            // Agregar observador
            torneo.registrarObservador(new RegistradorTorneo());

            // Agregar participantes
            torneo.agregarParticipante(new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com"));
            torneo.agregarParticipante(new IndividuoParticipante("María", "Gómez", 22, "maria@example.com"));
            torneo.agregarParticipante(new IndividuoParticipante("Carlos", "López", 30, "carlos@example.com"));
            torneo.agregarParticipante(new IndividuoParticipante("Ana", "Martínez", 28, "ana@example.com"));

            // Iniciar torneo
            torneo.iniciarTorneo();
            // Mostrar estado final
            torneo.verEstado();
            torneo.mostrarBracket();
        } catch (TorneoException e) {
            System.err.println("Error en el torneo de eliminación simple: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}
