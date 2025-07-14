package org.Main;

import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FTest {

    private GestorDeInstanciaCreadora gestor;
    private Creador creador;
    private TorneoLiga torneo;
    @Mock
    private RegistradorTorneo observador;

    @BeforeEach
    void setUp() {
        gestor = GestorDeInstanciaCreadora.getInstance();
        creador = gestor.obtenerCreador(FormatoTorneo.LIGA);
        torneo = (TorneoLiga) creador.crearTorneo("Liga Local", "FUTBOL", 8);
    }

    @Test
    void testCrearTorneoLiga() {
        assertNotNull(torneo, "El torneo no debería ser nulo");
        assertEquals("Liga Local", torneo.getNombre(), "El nombre del torneo debería coincidir");
        assertEquals("FUTBOL", torneo.getDeporte(), "El deporte del torneo debería coincidir");
        assertEquals(8, torneo.getCapacidad(), "La capacidad del torneo debería ser 8");
    }

    @Test
    void testAgregarParticipantes() {
        torneo.registrarObservador(observador);
        torneo.agregarParticipante(new Equipo("Equipo 1", "contacto1@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 2", "contacto2@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 3", "contacto3@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 4", "contacto4@example.com"));

        assertDoesNotThrow(() -> torneo.iniciarTorneo(), "Iniciar torneo con participantes válidos no debería lanzar excepción");
        verify(observador, atLeastOnce()).actualizar(anyString());
    }

    @Test
    void testIniciarTorneoConParticipantesInsuficientes() {
        torneo.registrarObservador(observador);
        torneo.agregarParticipante(new Equipo("Equipo 1", "contacto1@example.com"));

        TorneoException exception = assertThrows(TorneoException.class, () -> torneo.iniciarTorneo(),
                "Iniciar torneo con participantes insuficientes debería lanzar TorneoException");
        assertTrue(exception.getMessage().contains("insuficientes"), "El mensaje de error debería mencionar participantes insuficientes");
    }

    @Test
    void testMostrarBracketLanzaExcepcion() {
        TorneoException exception = assertThrows(TorneoException.class, () -> torneo.mostrarBracket(),
                "Llamar a mostrarBracket en TorneoLiga debería lanzar TorneoException");
        assertTrue(exception.getMessage().contains("no disponible"), "El mensaje de error debería indicar que el bracket no está disponible");
    }

    @Test
    void testVerEstado() {
        torneo.registrarObservador(observador);
        torneo.agregarParticipante(new Equipo("Equipo 1", "contacto1@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 2", "contacto2@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 3", "contacto3@example.com"));
        torneo.agregarParticipante(new Equipo("Equipo 4", "contacto4@example.com"));

        assertDoesNotThrow(() -> torneo.iniciarTorneo(), "Iniciar torneo no debería lanzar excepción");
        assertDoesNotThrow(() -> torneo.verEstado(), "Ver estado no debería lanzar excepción");
        verify(observador, atLeastOnce()).actualizar(anyString());
    }

    private Object atLeastOnce() {
        return null;
    }

    @Test
    void testAgregarParticipanteDuplicado() {
        torneo.registrarObservador(observador);
        Equipo equipo = new Equipo("Equipo 1", "contacto1@example.com");
        torneo.agregarParticipante(equipo);

        TorneoException exception = assertThrows(TorneoException.class, () -> torneo.agregarParticipante(equipo),
                "Agregar participante duplicado debería lanzar TorneoException");
        assertTrue(exception.getMessage().contains("duplicado"), "El mensaje de error debería mencionar participante duplicado");
    }
}