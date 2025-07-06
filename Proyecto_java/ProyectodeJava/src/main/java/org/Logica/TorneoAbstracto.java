package org.Logica;

import java.util.ArrayList;

public abstract class TorneoAbstracto {
    protected String nombre;
    protected Disciplina disciplina;
    protected int maxParticipantes;
    protected ArrayList<Participante> participantes;
    protected Calendario calendario;
    protected ArrayList<ObservadorTorneo> observadores;
    protected boolean torneoIniciado;

    public TorneoAbstracto(String nombre, String disciplina) {
        this.nombre = nombre;
        this.disciplina = Disciplina.valueOf(disciplina);
        this.participantes = new ArrayList<>();
        this.calendario = new Calendario();
        this.observadores = new ArrayList<>();
        this.torneoIniciado = false;
    }

    protected void configurar(String nombre, String disciplina, int maxParticipantes) {
        this.nombre = nombre;
        this.disciplina = Disciplina.valueOf(disciplina);
        this.maxParticipantes = maxParticipantes;
    }

    protected void agregarParticipante(Participante p) {
        if (participantes.size() < maxParticipantes && !torneoIniciado) {
            participantes.add(p);
            notificarObservadores(TipoEvento.PARTICIPANTE_AGREGADO, p);
        }
    }

    protected void eliminarParticipante(Participante p) {
        if (!torneoIniciado) {
            participantes.remove(p);
            notificarObservadores(TipoEvento.PARTICIPANTE_ELIMINADO, p);
        }
    }

    protected void iniciarTorneo() {
        if (participantes.size() >= 2) {
            torneoIniciado = true;
            notificarObservadores(TipoEvento.TORNEO_INICIADO, null);
        }
    }

    protected void registrarObservador(ObservadorTorneo observador) {
        observadores.add(observador);
    }

    protected void eliminarObservador(ObservadorTorneo observador) {
        observadores.remove(observador);
    }

    protected void notificarObservadores(TipoEvento tipo, Object datos) {
        for (ObservadorTorneo observador : observadores) {
            observador.actualizar(new EventoTorneo(tipo, (Torneo) this, datos));
        }
    }
}
