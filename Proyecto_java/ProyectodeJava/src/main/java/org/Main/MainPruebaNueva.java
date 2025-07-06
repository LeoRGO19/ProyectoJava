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


        System.out.println("\n=== Torneo de Liga ===");
        Creador creadorLiga = gestor.obtenerCreador(FormatoTorneo.LIGA);
        Torneo torneoLiga = creadorLiga.crearTorneo("Liga Local", "FUTBOL");
        torneoLiga.configurar("Liga Local", "FUTBOL", 8);
        torneoLiga.agregarParticipante(new Equipo("Equipo 1", "contacto1"));
        torneoLiga.agregarParticipante(new Equipo("Equipo 2", "contacto2"));
        torneoLiga.agregarParticipante(new Equipo("Equipo 3", "contacto3"));
        torneoLiga.agregarParticipante(new Equipo("Equipo 4", "contacto4"));
        ((TorneoLiga) torneoLiga).registrarObservador(new RegistradorTorneo());
        torneoLiga.iniciarTorneo();
        torneoLiga.generarTabla();
        torneoLiga.verEstado();

    }

}
