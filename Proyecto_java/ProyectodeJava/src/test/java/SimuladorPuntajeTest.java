import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimuladorPuntajeTest {

    private SimuladorPuntaje simulador;

    @BeforeEach
    public void setUp() {
        simulador = new SimuladorPuntaje();
    }

    // Método: simularPuntaje
    // Caso normal con FUTBOL
    // Caso normal con BASKETBALL
    // Caso normal con TIROCONARCO
    // Caso normal con disciplina por defecto (simularSimple)
    @Test
    public void testSimularPuntaje_Futbol() {
        int puntaje = simulador.simularPuntaje(Disciplina.FUTBOL);
        assertTrue(puntaje >= 0 && puntaje <= 1); // Máximo 1 por simulación
    }
    @Test
    public void testSimularPuntaje_Basketball() {
        int puntaje = simulador.simularPuntaje(Disciplina.BASKETBALL);
        assertTrue(puntaje >= 1 && puntaje <= 3); // Rango 1-3
    }
    @Test
    public void testSimularPuntaje_TiroConArco() {
        int puntaje = simulador.simularPuntaje(Disciplina.TIROCONARCO);
        assertTrue(puntaje >= 1 && puntaje <= 10); // Rango 1-10
    }
    @Test
    public void testSimularPuntaje_Default() {
        int puntaje = simulador.simularPuntaje(Disciplina.TENIS); // Ejemplo de disciplina no específica
        assertTrue(puntaje == 0 || puntaje == 1); // 0 o 1
    }

    // Método: simularFutbol
    @Test
    public void testSimularFutbol_CasoNormal() {
        int puntaje = simulador.simularFutbol();
        assertTrue(puntaje >= 0 && puntaje <= 1); // Probabilidad ~10% de 1
        // No se puede verificar exactamente la probabilidad sin muchas iteraciones
    }

    // Método: simularBasketball
    @Test
    public void testSimularBasketball_CasoNormal() {
        int puntaje = simulador.simularBasketball();
        assertTrue(puntaje >= 1 && puntaje <= 3); // Rango 1-3
    }

    // Método: simularSimple
    @Test
    public void testSimularSimple_CasoNormal() {
        int puntaje = simulador.simularSimple();
        assertTrue(puntaje == 0 || puntaje == 1); // 50% de probabilidad
    }

    // Método: simularTiroConArco
    @Test
    public void testSimularTiroConArco_CasoNormal() {
        int puntaje = simulador.simularTiroConArco();
        assertTrue(puntaje >= 1 && puntaje <= 10); // Rango 1-10
    }
}