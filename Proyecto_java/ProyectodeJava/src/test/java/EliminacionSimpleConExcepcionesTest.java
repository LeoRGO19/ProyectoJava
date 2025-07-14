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
public class NTest {

    private GestorDeInstanciaCreadora gestor;
    private Creador creador;
    private TorneoEliminacionSimple torneo;
    @Mock
    private RegistradorTorneo observador;

    @BeforeEach
    void setUp() {
        gestor = GestorDeInstanciaCreadora.getInstance();
        creador = gestor.obtenerCreador(FormatoTorneo.ELIMINACION_SIMPLE);
        torneo = (TorneoEliminacionSimple) creador.crearTorneo("Torneo Ejemplo", "FUTBOL", 4);
    }

    @Test
    void testCrearTorneoEliminacionSimple() {
        assertNotNull(torneo, "El torneo no debería ser nulo");
        assertEquals("Torneo Ejemplo", torneo.getNombre(), "El nombre del torneo debería coincidir");
        assertEquals("FUTBOL", torneo.getDeporte(), "El deporte del torneo debería coincidir");
        assertEquals(4, torneo.getCapacidad(), "La capacidad del torneo debería ser 4");
    }

    @Test
    void testAgregarParticipantes() {
        torneo.registrarObservador(observador);
        torneo.agregarParticipante(new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("María", "Gómez", 22, "maria@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("Carlos", "López", 30, "carlos@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("Ana", "Martínez", 28, "ana@example.com"));

        assertDoesNotThrow(() -> torneo.iniciarTorneo(), "Iniciar torneo con participantes válidos no debería lanzar excepción");
        verify(observador, atLeastOnce()).actualizar(anyString());
    }

    @Test
    void testIniciarTorneoConParticipantesInsuficientes() {
        torneo.registrarObservador(observador);
        torneo.agregarParticipante(new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com"));

        TorneoException exception = assertThrows(TorneoException.class, () -> torneo.iniciarTorneo(),
                "Iniciar torneo con participantes insuficientes debería lanzar TorneoException");
        assertTrue(exception.getMessage().contains("insuficientes"), "El mensaje de error debería mencionar participantes insuficientes");
    }

    @Test
    void testVerTablaLanzaExcepcion() {
        TorneoException exception = assertThrows(TorneoException.class, () -> torneo.verTabla(),
                "Llamar a verTabla en TorneoEliminacionSimple debería lanzar TorneoException");
        assertTrue(exception.getMessage().contains("no disponible"), "El mensaje de error debería indicar que la tabla no está disponible");
    }

    @Test
    void testMostrarBracket() {
        torneo.registrarObservador(observador);
        torneo.agregarParticipante(new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("María", "Gómez", 22, "maria@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("Carlos", "López", 30, "carlos@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("Ana", "Martínez", 28, "ana@example.com"));

        assertDoesNotThrow(() -> torneo.iniciarTorneo(), "Iniciar torneo no debería lanzar excepción");
        assertDoesNotThrow(() -> torneo.mostrarBracket(), "Mostrar bracket no debería lanzar excepción");
        verify(observador, atLeastOnce()).actualizar(anyString());
    }

    @Test
    void testAgregarParticipanteDuplicado() {
        torneo.registrarObservador(observador);
        IndividuoParticipante participante = new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com");
        torneo.agregarParticipante(participante);

        TorneoException exception = assertThrows(TorneoException.class, () -> torneo.agregarParticipante(participante),
                "Agregar participante duplicado debería lanzar TorneoException");
        assertTrue(exception.getMessage().contains("duplicado"), "El mensaje de error debería mencionar participante duplicado");
    }
}