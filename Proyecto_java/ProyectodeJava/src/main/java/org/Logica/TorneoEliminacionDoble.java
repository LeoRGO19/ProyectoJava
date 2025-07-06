package org.Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TorneoEliminacionDoble extends TorneoAbstracto implements Torneo, SujetoTorneo {
    private ArrayList<ArrayList<Enfrentamiento>> rondasGanadores;
    private ArrayList<ArrayList<Enfrentamiento>> rondasPerdedores;
    private int rondaActual;

    public TorneoEliminacionDoble(String nombre, String disciplina){
        super(nombre, disciplina);
        this.rondasGanadores = new ArrayList<>();
        this.rondasPerdedores = new ArrayList<>();
        this.rondaActual = 0;
    }
    @Override
    public void configurar(String nombre, String disciplina, int maxParticipantes) {
        super.configurar(nombre, disciplina, maxParticipantes);
    }
    public void agregarParticipante(Participante p){
        super.agregarParticipante(p);
    }
    public void eliminarParticipante(Participante p){
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

    @Override
    public void generarBracket() {
        rondasGanadores.clear();
        rondasPerdedores.clear();
        int numParticipantes = participantes.size();
        int rondasNecesarias = (int) Math.ceil(Math.log(numParticipantes) / Math.log(2));
        for (int i = 0; i < rondasNecesarias; i++) {
            rondasGanadores.add(new ArrayList<>());
            rondasPerdedores.add(new ArrayList<>());
        }
        generarEnfrentamientos();
    }

    @Override
    public void generarTabla() {
        throw new UnsupportedOperationException("El formato de eliminación doble no soporta tablas de clasificación");
    }

    @Override
    public void generarEnfrentamientos() {
        if (rondasGanadores.isEmpty()) return;

        if (rondaActual == 0) {
            // Primera ronda: solo bracket de ganadores
            rondasGanadores.getFirst().clear();
            ArrayList<Participante> participantesRonda = new ArrayList<>(participantes);
            Collections.shuffle(participantesRonda, new Random());

            for (int i = 0; i < participantesRonda.size() - 1; i += 2) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(participantesRonda.get(i), participantesRonda.get(i + 1));
                rondasGanadores.get(0).add(enfrentamiento);
            }

            if (participantesRonda.size() % 2 != 0) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(participantesRonda.get(participantesRonda.size() - 1), null);
                rondasGanadores.get(0).add(enfrentamiento);
            }

            generarCalendario();
            notificarObservadores(TipoEvento.ENFRENTAMIENTOS_GENERADOS, rondasGanadores.get(0));
        } else {
            // Rondas posteriores
            avanzarRonda();
        }
    }

    private void avanzarRonda() {
        ArrayList<Participante> ganadores = new ArrayList<>();
        ArrayList<Participante> perdedores = new ArrayList<>();

        // Procesar ronda actual del bracket de ganadores
        for (Enfrentamiento enf : rondasGanadores.get(rondaActual)) {
            if (!enf.haTerminadoEncuentro()) {
                enf.iniciarEncuentro();
            }
            Participante ganador = enf.obtenerGanador();
            Participante perdedor = enf.obtenerPerdedor();
            if (ganador != null) {
                ganadores.add(ganador);
            }
            if (perdedor != null) {
                perdedores.add(perdedor);
            }
        }

        // Procesar bracket de perdedores (si aplica)
        if (rondaActual > 0) {
            for (Enfrentamiento enf : rondasPerdedores.get(rondaActual - 1)) {
                if (!enf.haTerminadoEncuentro()) {
                    enf.iniciarEncuentro();
                }
                Participante ganador = enf.obtenerGanador();
                if (ganador != null) {
                    ganadores.add(ganador);
                }
            }
        }

        // Generar enfrentamientos para la siguiente ronda de ganadores
        rondaActual++;
        if (rondaActual < rondasGanadores.size()) {
            rondasGanadores.get(rondaActual).clear();
            Collections.shuffle(ganadores, new Random());
            for (int i = 0; i < ganadores.size() - 1; i += 2) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(ganadores.get(i), ganadores.get(i + 1));
                rondasGanadores.get(rondaActual).add(enfrentamiento);
            }
            if (ganadores.size() % 2 != 0) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(ganadores.get(ganadores.size() - 1), null);
                rondasGanadores.get(rondaActual).add(enfrentamiento);
            }

            // Generar enfrentamientos para el bracket de perdedores
            rondasPerdedores.get(rondaActual - 1).clear();
            Collections.shuffle(perdedores, new Random());
            for (int i = 0; i < perdedores.size() - 1; i += 2) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(perdedores.get(i), perdedores.get(i + 1));
                rondasPerdedores.get(rondaActual - 1).add(enfrentamiento);
            }
            if (perdedores.size() % 2 != 0) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(perdedores.get(perdedores.size() - 1), null);
                rondasPerdedores.get(rondaActual - 1).add(enfrentamiento);
            }

            generarCalendario();
            notificarObservadores(TipoEvento.RESULTADOS_ACTUALIZADOS, rondasGanadores.get(rondaActual));
        }
    }

    @Override
    public void generarCalendario() {
        calendario = new Calendario();
        int indice = 0;
        for (Enfrentamiento enf : rondasGanadores.get(rondaActual)) {
            calendario.agregarEnfrentamiento(enf, indice++);
        }
        if (rondaActual > 0) {
            for (Enfrentamiento enf : rondasPerdedores.get(rondaActual - 1)) {
                calendario.agregarEnfrentamiento(enf, indice++);
            }
        }
    }
    @Override
    public void verEstado() {
        System.out.println("Bracket de Eliminación Doble: " + nombre);
        calendario.mostrarCalendario();
        System.out.println("Bracket de Ganadores:");
        for (int i = 0; i < rondasGanadores.size(); i++) {
            System.out.println("Ronda " + (i + 1) + ":");
            for (Enfrentamiento enf : rondasGanadores.get(i)) {
                enf.verEnfrentamiento();
            }
        }
        System.out.println("Bracket de Perdedores:");
        for (int i = 0; i < rondasPerdedores.size(); i++) {
            System.out.println("Ronda " + (i + 1) + ":");
            for (Enfrentamiento enf : rondasPerdedores.get(i)) {
                enf.verEnfrentamiento();
            }
        }
    }
    public void mostrarBracket() {
        System.out.println("Bracket de Eliminación Doble: " + nombre);
        System.out.println("Bracket de Ganadores:");
        for (int i = 0; i < rondasGanadores.size(); i++) {
            System.out.println("Ronda " + (i + 1) + ":");
            for (Enfrentamiento enf : rondasGanadores.get(i)) {
                String p1 = enf.obtenerParticipante1().obtenerNombre();
                String p2 = enf.obtenerParticipante2() != null ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                String ganador = enf.obtenerGanador() != null ? enf.obtenerGanador().obtenerNombre() : "Pendiente";
                System.out.println("├── " + p1 + " vs " + p2 + " → Ganador: " + ganador);
            }
            if (i < rondasGanadores.size() - 1) {
                System.out.println("│");
            }
        }
        System.out.println("Bracket de Perdedores:");
        for (int i = 0; i < rondasPerdedores.size(); i++) {
            System.out.println("Ronda " + (i + 1) + ":");
            for (Enfrentamiento enf : rondasPerdedores.get(i)) {
                String p1 = enf.obtenerParticipante1().obtenerNombre();
                String p2 = enf.obtenerParticipante2() != null ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                String ganador = enf.obtenerGanador() != null ? enf.obtenerGanador().obtenerNombre() : "Pendiente";
                System.out.println("├── " + p1 + " vs " + p2 + " → Ganador: " + ganador);
            }
            if (i < rondasPerdedores.size() - 1) {
                System.out.println("│");
            }
        }
    }

    @Override
    public String toString() {
        return "TorneoEliminacionDoble creado.";
    }

}