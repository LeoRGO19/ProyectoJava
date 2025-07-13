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

    public TorneoAbstracto(String nombre, String disciplina, int maxParticipantes) {
        this.nombre = nombre;
        this.disciplina = Disciplina.valueOf(disciplina);
        this.participantes = new ArrayList<>();
        this.calendario = new Calendario();
        this.observadores = new ArrayList<>();
        this.torneoIniciado = false;
        this.maxParticipantes = maxParticipantes;
    }

    protected void configurar(String nombre, String disciplina, int maxParticipantes) {
        this.nombre = nombre;
        this.disciplina = Disciplina.valueOf(disciplina);
        this.maxParticipantes = maxParticipantes;
    }

    protected void agregarParticipante(Participante p) throws TorneoException {
        if (torneoIniciado) {
            throw new TorneoException("No se pueden agregar participantes una vez iniciado el torneo.");
        }
        if (p == null) {
            throw new TorneoException("El participante no puede ser nulo.");
        }
        if (participantes.size() >= maxParticipantes) {
            throw new TorneoException("No se pueden agregar más participantes: límite de " + maxParticipantes + " alcanzado.");
        }
        participantes.add(p);
        notificarObservadores(TipoEvento.PARTICIPANTE_AGREGADO, p);
    }

    protected void eliminarParticipante(Participante p) throws TorneoException {
        if (torneoIniciado) {
            throw new TorneoException("No se pueden eliminar participantes una vez iniciado el torneo.");
        }
        if (participantes.isEmpty()) {
            throw new TorneoException("No hay participantes para eliminar.");
        }
        if (!participantes.remove(p)) {
            throw new TorneoException("El participante no se encuentra en el torneo.");
        }
        if (!participantes.isEmpty() && !participantes.remove(p)) {
            participantes.remove(p);
            notificarObservadores(TipoEvento.PARTICIPANTE_ELIMINADO, p);
        }
    }

    protected void iniciarTorneo() throws TorneoException {
        if (participantes.size() < 2) {
            throw new TorneoException("Se necesitan al menos 2 participantes para iniciar el torneo.");
        } else {
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
