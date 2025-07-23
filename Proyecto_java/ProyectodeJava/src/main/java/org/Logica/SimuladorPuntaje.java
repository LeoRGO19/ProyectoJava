package org.Logica;

import java.util.Random;

/**
 * La clase {@code SimuladorPuntaje} se encarga de simular la obtención de puntajes
 * para cierto tipo de disciplinas, en este caso, las de tiempo de ejecucion.
 * Cada disciplina tiene su propia lógica de simulación.
 * <p>
 * Utiliza un generador de números aleatorios para modelar los resultados.
 * </p>
 *
 * @author Canito301
 */
public class SimuladorPuntaje {

    /** Generador de números aleatorios utilizado en las simulaciones. */
    private Random rand = new Random();

    /**
     * Simula el puntaje obtenido en una disciplina específica.
     *
     * @param disciplina la disciplina a simular.
     * @return un entero representando el puntaje simulado según la lógica de la disciplina.
     */
    public int simularPuntaje(Disciplina disciplina) {
        switch (disciplina) {
            case FUTBOL:
            case FIFA:
            case ROCKETLEAGUE:
                return simularFutbol();
            case BASKETBALL:
                return simularBasketball();
            case TIROCONARCO:
                return simularTiroConArco();
            default:
                return simularSimple();
        }
    }

    /**
     * Simula un puntaje para disciplinas tipo fútbol, con una baja probabilidad de anotar (orgulloso de este código).
     *
     * @return 1 si se anota (probabilidad baja), 0 en caso contrario.
     */
    public int simularFutbol() {
        int puntaje = 0;
        int chance = rand.nextInt(51); // valor entre 0 y 50
        if (chance > 45) {
            puntaje++;
        }
        return puntaje;
    }

    /**
     * Simula un puntaje para la disciplina de baloncesto (basketball).
     *
     * @return un puntaje entre 1 y 3 puntos, simulando un lanzamiento exitoso.
     */
    public int simularBasketball() {
        return rand.nextInt(3) + 1; // 1, 2 o 3 puntos
    }

    /**
     * Simula un puntaje simple para disciplinas (sumar 1 o 0 en cada iteración).
     *
     * @return 1 con 50% de probabilidad, 0 en caso contrario.
     */
    public int simularSimple() {
        return rand.nextBoolean() ? 1 : 0;
    }

    /**
     * Simula un puntaje para tiro con arco, donde el valor puede variar entre 1 y 10.
     *
     * @return un entero entre 1 y 10 representando la precisión del tiro.
     */
    public int simularTiroConArco() {
        return rand.nextInt(10) + 1;
    }
}