package org.Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TorneoEliminacionSimple extends TorneoAbstracto implements Torneo, SujetoTorneo {
    public ArrayList<ArrayList<Enfrentamiento>> rondas;
    public int rondaActual;

    public TorneoEliminacionSimple(String nombre, String disciplina){
        super(nombre, disciplina);
        this.rondas = new ArrayList<>();
        this.rondaActual = 0;
    }

    @Override
    public void configurar(String nombre, String disciplina, int maxParticipantes) {
        super.configurar(nombre, disciplina, maxParticipantes);
    }

    public void agregarParticipante(Participante p) {
        super.agregarParticipante(p);
    }

    public void eliminarParticipante(Participante p) {
        super.eliminarParticipante(p);
    }
    public void iniciarTorneo(){
        super.iniciarTorneo();
    }
    @Override
    public void registrarObservador(ObservadorTorneo observador) {
        super.registrarObservador(observador);
    }
    @Override
    public void eliminarObservador(ObservadorTorneo observador) {
        super.eliminarObservador(observador);
    }
    @Override
    public void notificarObservadores(TipoEvento tipo, Object datos) {
        super.notificarObservadores(tipo, datos);
    }

    public void generarBracket() {
        rondas.clear();
        int numParticipantes = participantes.size();
        int rondasNecesarias = (int) Math.ceil(Math.log(numParticipantes) / Math.log(2));
        for (int i = 0; i < rondasNecesarias; i++) {
            rondas.add(new ArrayList<>());
        }
        generarEnfrentamientos();
    }

    @Override
    public void generarTabla() {
        throw new UnsupportedOperationException("El formato de eliminación simple no soporta tablas de clasificación");
    }

    @Override
    public void generarEnfrentamientos() {
        if (rondas.isEmpty() || rondaActual >= rondas.size()) return;
        rondas.get(rondaActual).clear();
        ArrayList<Participante> participantesRonda = new ArrayList<>(participantes);
        Collections.shuffle(participantesRonda, new Random());
        for (int i = 0; i < participantesRonda.size() - 1; i += 2) {
            Enfrentamiento enfrentamiento = new Enfrentamiento(participantesRonda.get(i), participantesRonda.get(i + 1));
            rondas.get(rondaActual).add(enfrentamiento);
        }
        if (participantesRonda.size() % 2 != 0) {
            Enfrentamiento enfrentamiento = new Enfrentamiento(participantesRonda.get(participantesRonda.size() - 1), null);
            rondas.get(rondaActual).add(enfrentamiento);
        }
        generarCalendario();
        notificarObservadores(TipoEvento.ENFRENTAMIENTOS_GENERADOS, rondas.get(rondaActual));
    }
    public void avanzarRonda() {
        if (rondaActual >= rondas.size()) {
            return; // Evitar avanzar si ya no hay rondas
        }

        // Simular todos los enfrentamientos de la ronda actual
        ArrayList<Participante> ganadores = new ArrayList<>();
        for (Enfrentamiento enf : rondas.get(rondaActual)) {
            if (!enf.haTerminadoEncuentro()) {
                enf.iniciarEncuentro();
            }
            Participante ganador = enf.obtenerGanador();
            if (ganador != null) {
                ganadores.add(ganador);
            } else {
                System.out.println("Error: No se determinó un ganador para el enfrentamiento: " + enf);
            }
        }

        // Notificar resultados actualizados
        notificarObservadores(TipoEvento.RESULTADOS_ACTUALIZADOS, rondas.get(rondaActual));

        // Verificar si el torneo ha terminado
        if (ganadores.size() <= 1) {
            if (ganadores.size() == 1) {
                System.out.println("¡Torneo finalizado! Ganador: " + ganadores.get(0).obtenerNombre());
                notificarObservadores(TipoEvento.TORNEO_FINALIZADO, ganadores.get(0));
            } else {
                System.out.println("¡Torneo finalizado! No hay ganador (sin participantes suficientes).");
                notificarObservadores(TipoEvento.TORNEO_FINALIZADO, null);
            }
            rondaActual = rondas.size(); // Forzar la salida del bucle
            return;
        }

        // Avanzar a la siguiente ronda
        rondaActual++;
        if (rondaActual < rondas.size()) {
            rondas.get(rondaActual).clear();
            Collections.shuffle(ganadores, new Random());
            for (int i = 0; i < ganadores.size() - 1; i += 2) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(ganadores.get(i), ganadores.get(i + 1));
                rondas.get(rondaActual).add(enfrentamiento);
            }
            if (ganadores.size() % 2 != 0) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(ganadores.get(ganadores.size() - 1), null);
                rondas.get(rondaActual).add(enfrentamiento);
            }

            // Generar calendario para la nueva ronda
            generarCalendario();
            notificarObservadores(TipoEvento.ENFRENTAMIENTOS_GENERADOS, rondas.get(rondaActual));
        }
    }
    @Override
    public void verEstado() {
        System.out.println("Bracket de Eliminación Simple: " + nombre);
        if (rondaActual < rondas.size()) {
            calendario.mostrarCalendario();
        } else {
            System.out.println("Torneo finalizado. No hay más enfrentamientos programados.");
        }
        for (int i = 0; i <= rondaActual && i < rondas.size(); i++) {
            if (!rondas.get(i).isEmpty()) { // Solo mostrar rondas con enfrentamientos
                System.out.println("Ronda " + (i + 1) + ":");
                for (Enfrentamiento enf : rondas.get(i)) {
                    enf.verEnfrentamiento();
                }
            }
        }
    }
    /*
    @Override
    public void generarCalendario() {
        calendario = new Calendario();
        if (rondaActual < rondas.size()) {
            for (int i = 0; i < rondas.get(rondaActual).size(); i++) {
                calendario.agregarEnfrentamiento(rondas.get(rondaActual).get(i), i);
            }
        }
    }*/
    @Override
    public void generarCalendario() {
        calendario = new Calendario();
        if (rondaActual < rondas.size()) {
            int indice = 0;
            for (Enfrentamiento enf : rondas.get(rondaActual)) {
                calendario.agregarEnfrentamiento(enf, indice++);
            }
        }
    }

    public void mostrarBracket() {
        System.out.println("Bracket de Eliminación Simple: " + nombre);
        for (int i = 0; i < rondas.size(); i++) {
            System.out.println("Ronda " + (i + 1) + ":");
            for (Enfrentamiento enf : rondas.get(i)) {
                String p1 = enf.obtenerParticipante1().obtenerNombre();
                String p2 = enf.obtenerParticipante2() != null ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                String ganador = enf.obtenerGanador() != null ? enf.obtenerGanador().obtenerNombre() : "Pendiente";
                System.out.println("├── " + p1 + " vs " + p2 + " → Ganador: " + ganador);
            }
            if (i < rondas.size() - 1) {
                System.out.println("│");
            }
        }
    }

    @Override
    public String toString() {
        return "TorneoEliminacionSimple creado.";
    }
}