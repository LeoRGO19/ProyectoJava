package org.Logica;

import java.util.ArrayList;

/**
 * Clase abstracta que representa un torneo genérico.
 * <p>
 * Contiene la estructura básica y funcionalidad común para diferentes tipos de torneos,
 * como gestión de participantes, observadores, y control del estado del torneo.
 * </p>
 */

public abstract class TorneoAbstracto {

    /** Nombre del torneo */

    public String nombre;

    /** Disciplina del torneo */

    protected Disciplina disciplina;

    /** Número máximo de participantes permitidos */

    protected int maxParticipantes;

    /** Lista de participantes registrados */

    public ArrayList<Participante> participantes;

    /** Calendario asociado al torneo */

    protected Calendario calendario;

    /** Lista de observadores que escuchan eventos del torneo */

    protected ArrayList<ObservadorTorneo> observadores;

    /** Indica si el torneo ha sido iniciado */

    protected boolean torneoIniciado;

    /**
     * Constructor que inicializa el torneo con nombre, disciplina y máximo de participantes.
     *
     * @param nombre          El nombre del torneo.
     * @param disciplina      El nombre de la disciplina (convertido a {@link Disciplina}).
     * @param maxParticipantes Número máximo de participantes permitidos.
     */

    public TorneoAbstracto(String nombre, String disciplina, int maxParticipantes) {
        this.nombre = nombre;
        this.disciplina = Disciplina.valueOf(disciplina);
        this.participantes = new ArrayList<>();
        this.calendario = new Calendario();
        this.observadores = new ArrayList<>();
        this.torneoIniciado = false;
        this.maxParticipantes = maxParticipantes;
    }

    /**
     * Obtiene la lista de participantes del torneo.
     *
     * @return La lista de participantes.
     */

    public ArrayList<Participante> obtenerParticipantes() {
        return participantes;
    }

    /**
     * Configura los datos básicos del torneo.
     *
     * @param nombre          El nombre del torneo.
     * @param disciplina      La disciplina (nombre) del torneo.
     * @param maxParticipantes El máximo de participantes permitidos.
     */

    protected void configurar(String nombre, String disciplina, int maxParticipantes) {
        this.nombre = nombre;
        this.disciplina = Disciplina.valueOf(disciplina);
        this.maxParticipantes = maxParticipantes;
    }

    /**
     * Agrega un participante al torneo.
     *
     * @param p El participante a agregar.
     * @throws TorneoException Si el torneo ya fue iniciado, el participante es nulo,
     *                         o se ha alcanzado el máximo de participantes.
     */

    public void agregarParticipante(Participante p) throws TorneoException {
        if (torneoIniciado) {
            throw new TorneoException("No se pueden agregar participantes una vez iniciado el torneo.");
        }
        if (p == null) {
            throw new TorneoException("El participante no puede ser nulo.");
        }
        if (!this.obtenerParticipantes().isEmpty()) {
            if ((p instanceof Equipo && this.obtenerParticipantes().getFirst() instanceof IndividuoParticipante)||(p instanceof IndividuoParticipante && this.obtenerParticipantes().getFirst() instanceof Equipo)) {
                throw new TorneoException("El tipo de participante a ingresar es incompatible con los que ya hay");
            }
        }

        if (participantes.size() >= maxParticipantes) {
            throw new TorneoException("No se pueden agregar más participantes: límite de " + maxParticipantes + " alcanzado.");
        }
        participantes.add(p);
        notificarObservadores(TipoEvento.PARTICIPANTE_AGREGADO, p);
    }

    /**
     * Elimina un participante del torneo.
     *
     * @param p El participante a eliminar.
     * @throws TorneoException Si el torneo ya fue iniciado, no hay participantes,
     *                         o el participante no está registrado.
     */

    public void eliminarParticipante(Participante p) throws TorneoException {
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

    /**
     * Agrega múltiples participantes a partir de una lista.
     * Verifica que todos los participantes sean del mismo tipo (individuos o equipos).
     *
     * @param listaParticipantes La lista de participantes a agregar.
     * @throws TorneoException Si la lista es nula, vacía o contiene mezcla de tipos.
     */

    public void agregarParticipantesDesdeLista(ArrayList<Participante> listaParticipantes) throws TorneoException {
        if (listaParticipantes == null || listaParticipantes.isEmpty()) {
            throw new TorneoException("La lista de participantes no puede estar vacía.");
        }
        // Verificar que todos los participantes sean del mismo tipo (individuos o equipos)
        Class<?> tipo = listaParticipantes.get(0).getClass();
        for (Participante p : listaParticipantes) {
            if (!p.getClass().equals(tipo)) {
                throw new TorneoException("La lista contiene una mezcla de individuos y equipos, lo cual no está permitido.");
            }
        }
        // Agregar cada participante usando el método existente
        for (Participante p : listaParticipantes) {
            agregarParticipante(p);
        }
    }

    /**
     * Inicia el torneo si hay al menos dos participantes.
     *
     * @throws TorneoException si hay menos de 2 participantes al momento de iniciar.
     */

    protected void iniciarTorneo() throws TorneoException {
        if (participantes.size() < 2) {
            throw new TorneoException("Se necesitan al menos 2 participantes para iniciar el torneo.");
        } else {
            torneoIniciado = true;
            notificarObservadores(TipoEvento.TORNEO_INICIADO, null);
        }
    }

    /**
     * Registra un observador para recibir notificaciones de eventos.
     *
     * @param observador El observador a registrar.
     */

    protected void registrarObservador(ObservadorTorneo observador) {
        observadores.add(observador);
    }

    /**
     * Elimina un observador registrado.
     *
     * @param observador El observador a eliminar.
     */

    protected void eliminarObservador(ObservadorTorneo observador) {
        observadores.remove(observador);
    }

    /**
     * Notifica a todos los observadores registrados sobre un evento ocurrido.
     *
     * @param tipo  El tipo de evento que ocurrió.
     * @param datos Datos adicionales asociados al evento (puede ser null).
     */

    protected void notificarObservadores(TipoEvento tipo, Object datos) {
        for (ObservadorTorneo observador : observadores) {
            observador.actualizar(new EventoTorneo(tipo, (Torneo) this, datos));
        }
    }
}
