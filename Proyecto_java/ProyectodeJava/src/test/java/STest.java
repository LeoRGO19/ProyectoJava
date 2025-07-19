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
public class STest {

    private TorneoEliminacionSimple torneo;
    private GestorDeInstanciaCreadora gestor;
    private Creador creador;

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
        assertNotNull(torneo, "El torneo no debería ser null");
        assertEquals("Torneo Ejemplo", torneo.getNombre(), "El nombre del torneo debería coincidir");
        assertEquals("FUTBOL", torneo.getDeporte(), "El deporte debería ser FUTBOL");
        assertEquals(4, torneo.getCapacidad(), "La capacidad debería ser 4");
    }

    @Test
    void testAgregarParticipantes() {
        torneo.registrarObservador(observador);

        IndividuoParticipante p1 = new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com");
        IndividuoParticipante p2 = new IndividuoParticipante("María", "Gómez", 22, "maria@example.com");
        IndividuoParticipante p3 = new IndividuoParticipante("Carlos", "López", 30, "carlos@example.com");
        IndividuoParticipante p4 = new IndividuoParticipante("Ana", "Martínez", 28, "ana@example.com");

        assertDoesNotThrow(() -> {
            torneo.agregarParticipante(p1);
            torneo.agregarParticipante(p2);
            torneo.agregarParticipante(p3);
            torneo.agregarParticipante(p4);
        }, "No debería lanzar excepción al agregar participantes válidos");

        verify(observador, atLeastOnce()).actualizar(anyString());
    }

    @Test
    void testIniciarTorneo() {
        torneo.registrarObservador(observador);

        torneo.agregarParticipante(new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("María", "Gómez", 22, "maria@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("Carlos", "López", 30, "carlos@example.com"));
        torneo.agregarParticipante(new IndividuoParticipante("Ana", "Martínez", 28, "ana@example.com"));

        assertDoesNotThrow(() -> torneo.iniciarTorneo(), "No debería lanzar excepción al iniciar el torneo");
        verify(observador, atLeastOnce()).actualizar(anyString());
    }

    @Test
    void testTorneoConParticipantesInsuficientes() {
        torneo.registrarObservador(observador);
        torneo.agregarParticipante(new IndividuoParticipante("Juan", "Pérez", 25, "juan@example.com"));

        TorneoException exception = assertThrows(TorneoException.class,
                () -> torneo.iniciarTorneo(),
                "Debería lanzar TorneoException por participantes insuficientes");

        assertTrue(exception.getMessage().contains("insuficientes"),
                "El mensaje de error debería indicar participantes insuficientes");
    }
}