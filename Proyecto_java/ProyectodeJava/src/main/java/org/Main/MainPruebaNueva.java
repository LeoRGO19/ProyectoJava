package org.Main;

import org.Logica.*;

public class MainPruebaNueva {
    public static void main(String[] args) {
        GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();
        /*
        // Torneo de eliminación simple
        System.out.println("=== Torneo de Eliminación Simple ===");
        Creador creadorSimple = gestor.obtenerCreador(FormatoTorneo.ELIMINACION_SIMPLE);
        Torneo torneoSimple1 = creadorSimple.crearTorneo("Copa Simple", "FUTBOL");
        TorneoEliminacionSimple torneoSimple = (TorneoEliminacionSimple) torneoSimple1;
        torneoSimple.configurar("Copa Simple", "FUTBOL", 8);
        System.out.println("\n/////////\n");
        // Registrar el observador antes de agregar participantes
        torneoSimple.registrarObservador(new RegistradorTorneo());
        System.out.println("\n/////////\n");
        // Agregar participantes
        torneoSimple.agregarParticipante(new Equipo("Equipo A", "contactoA"));
        torneoSimple.agregarParticipante(new Equipo("Equipo B", "contactoB"));
        torneoSimple.agregarParticipante(new Equipo("Equipo C", "contactoC"));
        torneoSimple.agregarParticipante(new Equipo("Equipo D", "contactoD"));
        System.out.println("\n/////////\n");
        torneoSimple.iniciarTorneo();
        System.out.println("\n/////////\n");
        torneoSimple.generarBracket();
        System.out.println("\n/////////\n");
        // Simular todas las rondas hasta que el torneo termine
        while (torneoSimple.rondaActual < torneoSimple.rondas.size()) {
            torneoSimple.verEstado();
            torneoSimple.mostrarBracket();
            torneoSimple.avanzarRonda();
        }

        // Mostrar el estado final
        System.out.println("\n/////////\n");
        torneoSimple.verEstado();
        System.out.println("\n/////////\n");
        torneoSimple.mostrarBracket();
        */
        /*
        try {
            torneoSimple.generarTabla();
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        */


        // Torneo de eliminación doble
        System.out.println("\n=== Torneo de Eliminación Doble ===");
        Creador creadorDoble = gestor.obtenerCreador(FormatoTorneo.ELIMINACION_DOBLE);
        Torneo torneoDoble1 = creadorDoble.crearTorneo("Copa Doble", "FUTBOL");
        TorneoEliminacionDoble torneoDoble = (TorneoEliminacionDoble) torneoDoble1;
        torneoDoble.configurar("Copa Doble", "FUTBOL", 8);
        torneoDoble.agregarParticipante(new Equipo("Equipo X", "contactoX"));
        torneoDoble.agregarParticipante(new Equipo("Equipo Y", "contactoY"));
        torneoDoble.agregarParticipante(new Equipo("Equipo Z", "contactoZ"));
        torneoDoble.agregarParticipante(new Equipo("Equipo W", "contactoW"));
        torneoDoble.registrarObservador(new RegistradorTorneo());
        torneoDoble.iniciarTorneo();
        torneoDoble.generarBracket();
        torneoDoble.verEstado();
        torneoDoble.mostrarBracket();
        torneoDoble.generarEnfrentamientos(); // Avanza a la siguiente ronda
        torneoDoble.verEstado();
        torneoDoble.mostrarBracket();


    }

}
