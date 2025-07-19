package org.Test;

import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DTest {

    private TorneoLiga torneo;

    @BeforeEach
    void setUp() throws TorneoException {
        GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();
        Creador creador = gestor.obtenerCreador(FormatoTorneo.LIGA);
        torneo = (TorneoLiga) creador.crearTorneo("Liga Local", "FUTBOL", 8);

        torneo.registrarObservador(new RegistradorTorneo());
        torneo.agregarParticipante(new Equipo("Equipo 1", "contacto1@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 2", "contacto2@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 3", "contacto3@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 4", "contacto4@example.com"));
    }

    @Test
    void iniciarYVerEstadoTabla() throws TorneoException {
        torneo.iniciarTorneo();
        assertDoesNotThrow(() -> torneo.verEstado());
        assertDoesNotThrow(() -> torneo.mostrarTabla());
    }

    @Test
    void llamarMostrarBracketDeberiaLanzarExcepcion() {
        assertThrows(TorneoException.class, () -> torneo.mostrarBracket());
    }
}