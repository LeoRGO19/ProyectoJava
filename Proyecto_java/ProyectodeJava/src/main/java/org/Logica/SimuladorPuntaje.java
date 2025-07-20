package org.Logica;

import java.util.Random;

public class SimuladorPuntaje {

    private Random rand = new Random();

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

    public int simularFutbol() {
        int puntaje = 0;
        int chance = rand.nextInt(51); // 0 a 50
        if (chance > 45) {
            puntaje++;
        }
        return puntaje;
    }

    public int simularBasketball() {
        int puntaje = 0;
            puntaje += rand.nextInt(3) + 1;
        return puntaje;
    }

    public int simularSimple() {
        return rand.nextBoolean() ? 1 : 0;
    }
    public int simularTiroConArco(){
        return rand.nextInt(10) + 1;
    }
}