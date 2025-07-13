package org.Main;

import org.Logica.*;


public class MainLiga {
    public static void main(String[] args) {
        try {
            GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();
            System.out.println("\n=== Torneo de Liga ===");

            // Crear torneo de liga
            Creador creadorLiga = gestor.obtenerCreador(FormatoTorneo.LIGA);
            TorneoLiga torneoLiga = (TorneoLiga) creadorLiga.crearTorneo("Liga Local", "FUTBOL", 8);

            // Agregar observador
            torneoLiga.registrarObservador(new RegistradorTorneo());

            // Agregar participantes
            torneoLiga.agregarParticipante(new Equipo("Equipo 1", "contacto1@example.com"));
            torneoLiga.agregarParticipante(new Equipo("Equipo 2", "contacto2@example.com"));
            torneoLiga.agregarParticipante(new Equipo("Equipo 3", "contacto3@example.com"));
            torneoLiga.agregarParticipante(new Equipo("Equipo 4", "contacto4@example.com"));

            // Iniciar torneo y generar enfrentamientos
            torneoLiga.iniciarTorneo();

            // Mostrar estado final
            torneoLiga.verEstado();
        } catch (TorneoException e) {
            System.err.println("Error en el torneo de liga: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}
