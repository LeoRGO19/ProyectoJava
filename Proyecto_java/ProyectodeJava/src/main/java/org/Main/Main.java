package org.Main;
import org.Logica.*;

public class Main {
    public static void main(String[] args) {

        Torneo torneo1 = new TorneoEliminacionSimple("torneo1", "FUTBOL");
        IndividuoParticipante Lucas = new IndividuoParticipante("Lucas", "Ramirez", 15, "p1@GMAiL");
        IndividuoParticipante Martin = new IndividuoParticipante("Martin", "Ramirez", 12, "p2@GMAIL");
        Enfrentamiento t = new Enfrentamiento(Lucas,Martin);
        t.iniciarEncuentro();
        System.out.println(t.obtenerGanador());

    }
}
