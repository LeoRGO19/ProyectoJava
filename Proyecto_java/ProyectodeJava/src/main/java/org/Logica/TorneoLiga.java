package org.Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TorneoLiga extends TorneoAbstracto implements Torneo, SujetoTorneo {
    private ArrayList<Integer> puntos;
    private ArrayList<Enfrentamiento> enfrentamientos;

    public TorneoLiga(String nombre, String disciplina) {
        super(nombre, disciplina);
        this.puntos = new ArrayList<>();
        this.enfrentamientos = new ArrayList<>();
    }

    @Override
    public void configurar(String nombre, String disciplina, int maxParticipantes) {
        super.configurar(nombre, disciplina, maxParticipantes);
    }

    @Override
    public void agregarParticipante(Participante p) {
        super.agregarParticipante(p);
    }

    @Override
    public void eliminarParticipante(Participante p) {
        super.eliminarParticipante(p);
    }

    @Override
    public void iniciarTorneo() {
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
        throw new UnsupportedOperationException("El formato de liga no soporta brackets");
    }

    @Override
    public void generarTabla() {
        puntos.clear();
        for (int i = 0; i < participantes.size(); i++) {
            puntos.add(0);
        }
        generarEnfrentamientos();
    }

    @Override
    public void generarEnfrentamientos() {
        enfrentamientos.clear();
        ArrayList<Participante> participantesRonda = new ArrayList<>(participantes);
        Collections.shuffle(participantesRonda, new Random());
        for (int i = 0; i < participantesRonda.size(); i++) {
            for (int j = i + 1; j < participantesRonda.size(); j++) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(participantesRonda.get(i), participantesRonda.get(j));
                enfrentamientos.add(enfrentamiento);
            }
        }
        generarCalendario();
        notificarObservadores(TipoEvento.ENFRENTAMIENTOS_GENERADOS, enfrentamientos);
        for (Enfrentamiento enf : enfrentamientos) {
            enf.iniciarEncuentro();
            Participante ganador = enf.obtenerGanador();
            if (ganador == null) {
                int idx1 = participantes.indexOf(enf.obtenerParticipante1());
                int idx2 = participantes.indexOf(enf.obtenerParticipante2());
                puntos.set(idx1, puntos.get(idx1) + 1);
                puntos.set(idx2, puntos.get(idx2) + 1);
            } else {
                int idx = participantes.indexOf(ganador);
                puntos.set(idx, puntos.get(idx) + 3);
            }
        }
        notificarObservadores(TipoEvento.RESULTADOS_ACTUALIZADOS, puntos);
    }

    @Override
    public void generarCalendario() {
        calendario = new Calendario();
        for (int i = 0; i < enfrentamientos.size(); i++) {
            calendario.agregarEnfrentamiento(enfrentamientos.get(i), i);
        }
    }

    @Override
    public void verEstado() {
        System.out.println("Tabla de Clasificación: " + nombre);
        System.out.println("Resultados de Enfrentamientos:");
        for (Enfrentamiento enf : enfrentamientos) {
            enf.verEnfrentamiento();
        }
        System.out.println("\nCalendario del Torneo:");
        calendario.mostrarCalendario();
        System.out.println("\nClasificación:");
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < participantes.size(); i++) {
            indices.add(i);
        }
        indices.sort((i1, i2) -> puntos.get(i2).compareTo(puntos.get(i1))); // Orden descendente
        for (int i : indices) {
            System.out.println(participantes.get(i).obtenerNombre() + ": " + puntos.get(i) + " puntos");
        }
    }
    public void mostrarBracket() {
        throw new UnsupportedOperationException("El formato de liga no soporta brackets");
    }

}