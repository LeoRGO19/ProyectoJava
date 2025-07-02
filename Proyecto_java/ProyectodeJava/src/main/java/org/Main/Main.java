package org.Main;
import org.Logica.*;

import org.Logica.TorneoEstandar;

public class Main {
    public static void main(String[] args) {

        Torneo torneo1 = new TorneoEliminacionSimple(2);
        IndividuoParticipante Lucas = new IndividuoParticipante("Lucas", "Ramirez", 15);
        IndividuoParticipante Martin = new IndividuoParticipante("Martin", "Ramirez", 12);
        Enfrentamiento t = new Enfrentamiento(Lucas, 4,Martin,2);
        System.out.println(torneo1);
        System.out.println(t.getGanador(4,2));
        System.out.println(t.getGanador(2,4));

    }
}
