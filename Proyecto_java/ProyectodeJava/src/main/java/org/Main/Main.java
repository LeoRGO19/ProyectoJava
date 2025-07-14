package org.Main;

import org.Logica.*;

/**
 * Clase principal para ejecutar un ejemplo simple de torneo con enfrentamiento.
 * <p>
 * Este programa crea un torneo de eliminación simple, dos participantes, y un enfrentamiento entre ellos.
 * Luego inicia el encuentro y muestra el ganador en consola.
 * </p>
 */
public class Main {

    /**
     * Método principal que ejecuta la simulación básica.
     *
     * @param args argumentos de línea de comando (no usados)
     * @throws TorneoException si ocurre algún error relacionado con la lógica del torneo
     */
    public static void main(String[] args) throws TorneoException {
        // Crear un torneo de eliminación simple con 8 participantes
        Torneo torneo1 = new TorneoEliminacionSimple("torneo1", "FUTBOL", 8);

        // Crear dos participantes individuales
        IndividuoParticipante Lucas = new IndividuoParticipante("Lucas", "Ramirez", 15, "p1@GMAiL");
        IndividuoParticipante Martin = new IndividuoParticipante("Martin", "Ramirez", 12, "p2@GMAIL");

        // Crear un enfrentamiento entre Lucas y Martin en el torneo creado
        Enfrentamiento t = new Enfrentamiento(Lucas, Martin, torneo1);

        // Iniciar el enfrentamiento
        t.iniciarEncuentro();

        // Imprimir el ganador del enfrentamiento
        System.out.println(t.obtenerGanador());
    }
}