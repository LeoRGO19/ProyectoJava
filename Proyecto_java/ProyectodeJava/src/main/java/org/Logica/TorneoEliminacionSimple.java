package org.Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Implementación de un torneo con formato de eliminación simple.
 * <p>
 * En este formato, los participantes compiten en rondas eliminatorias
 * hasta que queda un único ganador. El número de participantes debe ser
 * potencia de dos para que el torneo funcione correctamente.
 * </p>
 */

public class TorneoEliminacionSimple extends TorneoAbstracto implements Torneo, SujetoTorneo {

    /** Matriz de enfrentamientos por ronda */

    private Enfrentamiento[][] rondas;

    /** Array con los enfrentamientos actuales de la ronda */

    private Enfrentamiento[] rondasS;
    public Enfrentamiento[] obtenerRondasS(){
        return rondasS;
    }

    /** Índice de la ronda actual */

    private int rondaActual;

    /** Número total de rondas */

    private int numRondas;

    /** Estado del torneo: 0=no iniciado, 1=iniciado, 2=finalizado */

    private int iniciado = 0;
    /**
     * getter del entero iniciado.
     *
     * @return iniciado.
     */
    public int obtenerIniciado(){
        return iniciado;
    }
    /**
     * método que retorna si el torneo ha terminado.
     *
     * @return false o true.
     */
    public boolean haTerminado(){
        if(iniciado == 2){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Constructor que inicializa el torneo con nombre, disciplina y máximo participantes.
     *
     * @param nombre          Nombre del torneo.
     * @param disciplina      Disciplina del torneo.
     * @param maxParticipantes Número máximo de participantes.
     */

    public TorneoEliminacionSimple(String nombre, String disciplina, int maxParticipantes){
        super(nombre, disciplina, maxParticipantes);
        rondaActual = 0;
        rondas = null;
        numRondas = 0;
    }
    /**
     * método para agregar participantes desde un archivo.
     * llama al mismo método de la superclase.
     */
    @Override
    public void agregarParticipantesDesdeLista(ArrayList<Participante> listaParticipantes) throws TorneoException {
        super.agregarParticipantesDesdeLista(listaParticipantes);
    }
    /**
     * getter de la lista de participantes.
     *
     * @return un ArrayList<Participante></Participante>
     */
    public ArrayList<Participante> obtenerParticipantes(){
        return super.obtenerParticipantes();
    }
    /**
     * método para configurar el torneo.
     * @param nombre          Nombre del torneo.
     * @param disciplina      Disciplina del torneo.
     * @param maxParticipantes Número máximo de participantes.
     * llama a al mismo método de la superclase.
     */
    @Override
    public void configurar(String nombre, String disciplina, int maxParticipantes) {
        super.configurar(nombre, disciplina, maxParticipantes);
    }
    /**
     * método para agregar participantes.
     * @param p          Participante a agregar.
     * llama a al mismo método de la superclase.
     */
    public void agregarParticipante(Participante p) throws TorneoException{
        super.agregarParticipante(p);
    }
    /**
     * método para eliminar participantes.
     * * @param p          Participante a eliminar.
     * llama a al mismo método de la superclase.
     */
    public void eliminarParticipante(Participante p) throws TorneoException{
        super.eliminarParticipante(p);
    }
    /**
     * método para registrar observadores.
     * @param observador       ObservadorTorneo (observador) a registrar.
     * llama a al mismo método de la superclase.
     */
    @Override
    public void registrarObservador(ObservadorTorneo observador) {
        super.registrarObservador(observador);
    }
    /**
     * método eliminar observadores registrados.
     * @param observador       ObservadorTorneo (observador) a eliminar.
     * llama a al mismo método de la superclase.
     */
    @Override
    public void eliminarObservador(ObservadorTorneo observador) {
        super.eliminarObservador(observador);
    }
    /**
     * método notificar observadores registrados.
     * @param tipo       tipo de evento.
     * @param datos       objeto actualizado.
     * llama a al mismo método de la superclase.
     */
    @Override
    public void notificarObservadores(TipoEvento tipo, Object datos) {
        super.notificarObservadores(tipo, datos);
    }

    /**
     * Inicia el torneo asegurando que el número de participantes sea potencia de dos,
     * genera el bracket y avanza por las rondas hasta que finaliza el torneo.
     *
     * @throws TorneoException Si el número de participantes no es potencia de dos.
     */

    @Override
    public void iniciarTorneo() throws TorneoException{
        if (!esPotenciaDeDos(participantes.size())) {
            throw new TorneoException("Para iniciar el formato de eliminación simple el número de participantes debe ser potencia de 2");
        }
        super.iniciarTorneo();
        iniciado = 1;
        generarBracket();
        notificarObservadores(TipoEvento.TORNEO_INICIADO, this);
        while (rondaActual < numRondas) {
            Enfrentamiento[][] l = obtenerRondas();
            verEstado();
            mostrarBracket();
            avanzarRonda();
        }
        iniciado = 2;
    }

    /**
     * Verifica si un número es potencia de dos.
     *
     * @param n Número a verificar.
     * @return true si n es potencia de dos, false en caso contrario.
     */

    private boolean esPotenciaDeDos(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * Genera la estructura del bracket para el torneo.
     *
     * @throws TorneoException Si no hay participantes.
     */

    @Override
    public void generarBracket() throws TorneoException {
        if (participantes.isEmpty()) {
            throw new TorneoException("No hay participantes para generar el bracket.");
        }
        int numParticipantes = participantes.size();
        numRondas = (int) Math.ceil(Math.log(numParticipantes) / Math.log(2));
        rondas = new Enfrentamiento[numRondas][];
        for (int r = 0; r < numRondas; r++) {
            rondas[r] = new Enfrentamiento[numParticipantes / (1 << (r + 1))];
        }
        generarEnfrentamientos();
    }

    /**
     * Este formato no soporta generación de tablas de clasificación.
     *
     * @throws TorneoException Siempre, porque no está soportado.
     */

    @Override
    public void generarTabla() throws TorneoException {
        throw new TorneoException("El formato de eliminación simple no soporta tablas de clasificación.");
    }

    /**
     * Genera los enfrentamientos para la ronda actual.
     *
     * @throws TorneoException Si ocurre algún error en la generación.
     */

    @Override
    public void generarEnfrentamientos() throws TorneoException {
        if (rondaActual >= numRondas) return;
        ArrayList<Participante> participantesRonda;
        if (rondaActual == 0) {
            participantesRonda = new ArrayList<>(participantes);
            Collections.shuffle(participantesRonda, new Random());
        } else {
            participantesRonda = new ArrayList<>();
            for (Enfrentamiento enf : rondas[rondaActual - 1]) {
                Participante ganador = enf.obtenerGanador();
                if (ganador != null) {
                    participantesRonda.add(ganador);
                }
            }
            Collections.shuffle(participantesRonda, new Random());
        }

        for (int i = 0; i < participantesRonda.size() / 2; i++) {
            rondas[rondaActual][i] = new Enfrentamiento(participantesRonda.get(i * 2), participantesRonda.get(i * 2 + 1), this);
        }
        generarCalendario();
        notificarObservadores(TipoEvento.ENFRENTAMIENTOS_GENERADOS, rondas[rondaActual]);
        rondasS = rondas[rondaActual];
    }

    /**
     * Avanza la ronda actual ejecutando los enfrentamientos en paralelo.
     * Notifica resultados y finaliza el torneo cuando corresponde.
     *
     * @throws TorneoException En caso de errores en la ejecución.
     */

    public void avanzarRonda() throws TorneoException {
        if (rondaActual >= numRondas) {
            System.out.println("Torneo finalizado.");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(rondas[rondaActual].length);
        for (Enfrentamiento enf : rondas[rondaActual]) {
            if (!enf.haTerminadoEncuentro()) {
                executor.submit(() -> {
                    enf.iniciarEncuentro();
                });
            }
        }

        // Esperar a que todos los enfrentamientos terminen
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS); // Tiempo máximo de espera
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        notificarObservadores(TipoEvento.RESULTADOS_ACTUALIZADOS, rondas[rondaActual]);
        rondasS = rondas[rondaActual];

        if (rondaActual == numRondas - 1) {
            Participante ganador = rondas[rondaActual][0].obtenerGanador();
            System.out.println("¡Torneo finalizado! Ganador: " + (ganador != null ? ganador.obtenerNombre() : "No determinado"));
            notificarObservadores(TipoEvento.TORNEO_FINALIZADO, ganador);
            rondaActual++;
            return;
        }

        rondaActual++;
        generarEnfrentamientos();
    }

    /**
     * Muestra el estado actual del torneo, incluyendo calendario y enfrentamientos.
     */

    @Override
    public void verEstado() {
        System.out.println("Torneo: " + nombre + " (Eliminación Simple)");
        calendario.mostrarCalendario();

        for (int r = 0; r <= rondaActual && r < numRondas; r++) {
            if (rondas[r] != null && rondas[r].length > 0) {
                System.out.println("Ronda " + (r + 1) + ":");
                for (Enfrentamiento enf : rondas[r]) {
                    if (enf != null) {
                        enf.verEnfrentamiento();
                    }
                }
            }
        }
    }

    /**
     * Genera el calendario de enfrentamientos para la ronda actual.
     */

    @Override
    public void generarCalendario() {
        calendario = new Calendario();
        if (rondaActual < numRondas) {
            int indice = 0;
            for (Enfrentamiento enf : rondas[rondaActual]) {
                calendario.agregarEnfrentamiento(enf, rondaActual * rondas[rondaActual].length + indice);
                indice++;
            }
        }
    }

    /**
     * Muestra en consola el bracket actual del torneo.
     */

    public void mostrarBracket() {
        System.out.println("Bracket de Eliminación Simple: " + nombre);
        for (int r = 0; r <= rondaActual && r < numRondas; r++) {
            if (rondas[r] != null && rondas[r].length > 0) {
                System.out.println("Ronda " + (r + 1) + ":");
                for (Enfrentamiento enf : rondas[r]) {
                    if (enf != null) {
                        String ganador = enf.obtenerGanador() != null ? enf.obtenerGanador().obtenerNombre() : "Pendiente";
                        System.out.println("├── " + enf + " → Ganador: " + ganador);
                    } else {
                        System.out.println("├── Enfrentamiento no inicializado");
                    }
                }
                if (r < rondaActual) {
                    System.out.println("│");
                }
            }
        }
    }

    /**
     * Muestra el calendario actual del torneo.
     */

    public void verCalendario(){
        calendario.mostrarCalendario();
    }

    /**
     * Obtiene la matriz con todas las rondas y sus enfrentamientos.
     *
     * @return Matriz de enfrentamientos por ronda.
     */

    public Enfrentamiento[][] obtenerRondas() {
        return rondas;
    }

    /**
     * Obtiene la ronda actual en curso.
     *
     * @return Índice de la ronda actual.
     */

    public int obtenerRondaActual() {
        return rondaActual;
    }

    /**
     * Obtiene el número total de rondas que tendrá el torneo.
     *
     * @return Número total de rondas.
     */

    public int obtenerNumRondas() {
        return numRondas;
    }
    @Override
    public String toString() {
        return "TorneoEliminacionSimple creado.";
    }
}