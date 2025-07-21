package org.Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.time.format.DateTimeFormatter;

/**
 * Implementación de un torneo en formato Liga (todos contra todos).
 * <p>
 * Los participantes se enfrentan entre sí, acumulando puntos por victorias,
 * empates y derrotas. Se mantiene una tabla de clasificación que ordena los
 * participantes según puntos, victorias, puntos a favor y puntos en contra.
 * </p>
 * <p>
 * Esta clase maneja la generación de enfrentamientos, la ejecución simultánea
 * de partidos en hilos, la actualización de estadísticas y la notificación a
 * observadores de eventos relevantes.
 * </p>
 */

public class TorneoLiga extends TorneoAbstracto implements Torneo, SujetoTorneo {

    /**
     * Lista de puntos acumulados por cada participante.
     */

    private ArrayList<Integer> puntos;

    public ArrayList<Integer> obtenerPuntos(){
        return puntos;
    }

    /**
     * Lista de victorias acumuladas por cada participante.
     */

    private ArrayList<Integer> victorias;
    public ArrayList<Integer> obtenerVictorias(){
        return victorias;
    }

    /**
     * Lista de derrotas acumuladas por cada participante.
     */

    private ArrayList<Integer> derrotas;
    public ArrayList<Integer> obtenerDerrotas(){
        return derrotas;
    }

    /**
     * Lista de puntos anotados a favor por cada participante.
     */

    private ArrayList<Integer> puntosAFavor;
    public ArrayList<Integer> obtenerPuntosAFavor(){
        return puntosAFavor;
    }

    /**
     * Lista de puntos recibidos en contra por cada participante.
     */

    private ArrayList<Integer> puntosEnContra;
    public ArrayList<Integer> obtenerPuntosEnContra(){
        return puntosEnContra;
    }
    /**
     * Lista de enfrentamientos generados para el torneo.
     */

    private ArrayList<Enfrentamiento> enfrentamientos;
    public ArrayList<Enfrentamiento> obtenerEnfrentamientos(){
        return enfrentamientos;
    }

    /**
     * Construye un torneo en formato Liga con los datos básicos.
     *
     * @param nombre          Nombre del torneo.
     * @param disciplina      Disciplina o deporte del torneo.
     * @param maxParticipantes Máximo número de participantes permitidos.
     */

    public TorneoLiga(String nombre, String disciplina, int maxParticipantes) {
        super(nombre, disciplina, maxParticipantes);
        this.puntos = new ArrayList<>();
        this.victorias = new ArrayList<>();
        this.derrotas = new ArrayList<>();
        this.puntosAFavor = new ArrayList<>();
        this.puntosEnContra = new ArrayList<>();
        this.enfrentamientos = new ArrayList<>();
    }

    /**
     * Configura el torneo con los parámetros dados.
     *
     * @param nombre          Nombre del torneo.
     * @param disciplina      Disciplina del torneo.
     * @param maxParticipantes Número máximo de participantes.
     */

    @Override
    public void configurar(String nombre, String disciplina, int maxParticipantes) {
        super.configurar(nombre, disciplina, maxParticipantes);
    }

    /**
     * Agrega un participante al torneo y inicializa sus estadísticas.
     *
     * @param p Participante a agregar.
     * @throws TorneoException Si no se puede agregar el participante.
     */

    @Override
    public void agregarParticipante(Participante p) throws TorneoException {
        super.agregarParticipante(p);
        puntos.add(0);
        victorias.add(0);
        derrotas.add(0);
        puntosAFavor.add(0);
        puntosEnContra.add(0);
    }

    /**
     * Elimina un participante del torneo y sus estadísticas.
     *
     * @param p Participante a eliminar.
     * @throws TorneoException Si el participante no existe o no puede ser eliminado.
     */

    @Override
    public void eliminarParticipante(Participante p) throws TorneoException {
        int index = participantes.indexOf(p);
        if (index != -1) {
            super.eliminarParticipante(p);
            puntos.remove(index);
            victorias.remove(index);
            derrotas.remove(index);
            puntosAFavor.remove(index);
            puntosEnContra.remove(index);
        }
    }

    /**
     * Inicia el torneo, genera la tabla de enfrentamientos y notifica el inicio.
     *
     * @throws TorneoException Si no se cumplen las condiciones para iniciar.
     */

    @Override
    public void iniciarTorneo() throws TorneoException {
        super.iniciarTorneo();
        generarTabla();
        notificarObservadores(TipoEvento.TORNEO_INICIADO, this);
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

    /**
     * No soporta brackets, lanza excepción si se invoca.
     */

    @Override
    public void generarBracket() {
        throw new UnsupportedOperationException("El formato de liga no soporta brackets");
    }

    /**
     * Genera la tabla inicializando las estadísticas y crea los enfrentamientos.
     */

    @Override
    public void generarTabla() {
        puntos.clear();
        victorias.clear();
        derrotas.clear();
        puntosAFavor.clear();
        puntosEnContra.clear();
        for (int i = 0; i < participantes.size(); i++) {
            puntos.add(0);
            victorias.add(0);
            derrotas.add(0);
            puntosAFavor.add(0);
            puntosEnContra.add(0);
        }
        generarEnfrentamientos();
    }

    /**
     * Genera todos los enfrentamientos posibles entre los participantes, ejecutándolos
     * en paralelo, actualizando estadísticas y notificando resultados.
     */

    @Override
    public void generarEnfrentamientos() {
        enfrentamientos.clear();
        ArrayList<Participante> participantesRonda = new ArrayList<>(participantes);
        Collections.shuffle(participantesRonda, new Random());

        // Generar todos los enfrentamientos posibles
        for (int i = 0; i < participantesRonda.size(); i++) {
            for (int j = i + 1; j < participantesRonda.size(); j++) {
                Enfrentamiento enfrentamiento = new Enfrentamiento(participantesRonda.get(i), participantesRonda.get(j), this);
                enfrentamientos.add(enfrentamiento);
            }
        }

        // Generar calendario
        generarCalendario();
        notificarObservadores(TipoEvento.ENFRENTAMIENTOS_GENERADOS, enfrentamientos.toArray(new Enfrentamiento[0]));

        // Ejecutar enfrentamientos en hilos
        ExecutorService executor = Executors.newFixedThreadPool(enfrentamientos.size());
        for (Enfrentamiento enf : enfrentamientos) {
            executor.submit(() -> {
                enf.iniciarEncuentro();
                actualizarEstadisticas(enf);
            });
        }

        // Esperar a que todos los enfrentamientos terminen
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        notificarObservadores(TipoEvento.RESULTADOS_ACTUALIZADOS, enfrentamientos.toArray(new Enfrentamiento[0]));

        // Determinar ganador
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < participantes.size(); i++) {
            indices.add(i);
        }
        // Ordenar por puntos, victorias, puntos a favor, y luego puntos en contra
        indices.sort((i1, i2) -> {
            int cmp = puntos.get(i2).compareTo(puntos.get(i1));
            if (cmp != 0) return cmp;
            cmp = victorias.get(i2).compareTo(victorias.get(i1));
            if (cmp != 0) return cmp;
            cmp = puntosAFavor.get(i2).compareTo(puntosAFavor.get(i1));
            if (cmp != 0) return cmp;
            return puntosEnContra.get(i1).compareTo(puntosEnContra.get(i2));
        });
        Participante ganador = participantes.get(indices.get(0));
        notificarObservadores(TipoEvento.TORNEO_FINALIZADO, ganador);
    }

    /**
     * Agrega participantes desde una lista, verificando su validez.
     *
     * @param listaParticipantes Lista de participantes a agregar.
     * @throws TorneoException Si la lista contiene tipos mixtos o es inválida.
     */

    @Override
    public void agregarParticipantesDesdeLista(ArrayList<Participante> listaParticipantes) throws TorneoException {
        super.agregarParticipantesDesdeLista(listaParticipantes);
    }

    /**
     * Devuelve la lista de participantes del torneo.
     *
     * @return Lista de participantes.
     */

    @Override
    public ArrayList<Participante> obtenerParticipantes(){
        return super.obtenerParticipantes();
    }

    /**
     * Actualiza las estadísticas tras la finalización de un enfrentamiento.
     *
     * @param enf Enfrentamiento terminado.
     */

    private void actualizarEstadisticas(Enfrentamiento enf) {
        Participante p1 = enf.obtenerParticipante1();
        Participante p2 = enf.obtenerParticipante2();
        int idx1 = participantes.indexOf(p1);
        int idx2 = participantes.indexOf(p2);
        int score1 = enf.obtenerPuntaje1();
        int score2 = enf.obtenerPuntaje2();

        // Actualizar puntos a favor y en contra
        puntosAFavor.set(idx1, puntosAFavor.get(idx1) + score1);
        puntosEnContra.set(idx1, puntosEnContra.get(idx1) + score2);
        puntosAFavor.set(idx2, puntosAFavor.get(idx2) + score2);
        puntosEnContra.set(idx2, puntosEnContra.get(idx2) + score1);

        // Actualizar puntos, victorias y derrotas
        if (score1 > score2) {
            puntos.set(idx1, puntos.get(idx1) + 3);
            victorias.set(idx1, victorias.get(idx1) + 1);
            derrotas.set(idx2, derrotas.get(idx2) + 1);
        } else if (score2 > score1) {
            puntos.set(idx2, puntos.get(idx2) + 3);
            victorias.set(idx2, victorias.get(idx2) + 1);
            derrotas.set(idx1, derrotas.get(idx1) + 1);
        } else {
            puntos.set(idx1, puntos.get(idx1) + 1);
            puntos.set(idx2, puntos.get(idx2) + 1);
        }
    }

    /**
     * Genera el calendario de enfrentamientos.
     */

    @Override
    public void generarCalendario() {
        calendario = new Calendario();
        for (int i = 0; i < enfrentamientos.size(); i++) {
            calendario.agregarEnfrentamiento(enfrentamientos.get(i), i);
        }
    }

    /**
     * Muestra el estado actual del torneo, incluyendo la tabla y calendario.
     */

    @Override
    public void verEstado() {
        System.out.println("Torneo: " + nombre + " (Formato Liga)");
        mostrarTabla();
        System.out.println("\nCalendario del Torneo:");
        calendario.mostrarCalendario();
    }

    /**
     * Muestra la tabla de clasificación ordenada por criterios estándar.
     */

    public void mostrarTabla() {
        System.out.println("\nTabla de Clasificación: " + nombre);
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < participantes.size(); i++) {
            indices.add(i);
        }
        // Ordenar por puntos, victorias, puntos a favor, y luego puntos en contra
        indices.sort((i1, i2) -> {
            int cmp = puntos.get(i2).compareTo(puntos.get(i1));
            if (cmp != 0) return cmp;
            cmp = victorias.get(i2).compareTo(victorias.get(i1));
            if (cmp != 0) return cmp;
            cmp = puntosAFavor.get(i2).compareTo(puntosAFavor.get(i1));
            if (cmp != 0) return cmp;
            return puntosEnContra.get(i1).compareTo(puntosEnContra.get(i2));
        });

        // Mostrar tabla de clasificación
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-20s | %-6s | %-8s | %-8s | %-10s | %-10s%n",
                "Participante", "Puntos", "Victorias", "Derrotas", "P. Favor", "P. Contra");
        System.out.println("---------------------------------------------------------------------------------");
        for (int i : indices) {
            System.out.printf("%-20s | %-6d | %-8d | %-8d | %-10d | %-10d%n",
                    participantes.get(i).obtenerNombre(),
                    puntos.get(i),
                    victorias.get(i),
                    derrotas.get(i),
                    puntosAFavor.get(i),
                    puntosEnContra.get(i));
        }
        System.out.println("---------------------------------------------------------------------------------");

        System.out.println("\nEnfrentamientos:");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-30s | %-19s | %-10s%n", "Enfrentamiento", "Fecha", "Resultado");
        System.out.println("-------------------------------------------------------------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        for (Enfrentamiento enf : enfrentamientos) {
            String p2Nombre = (enf.obtenerParticipante2() != null) ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
            String fechaFormateada = (enf.obtenerFecha() != null) ? enf.obtenerFecha().format(formatter) : "Sin fecha";
            String resultado = enf.haTerminadoEncuentro() ?
                    enf.obtenerParticipante1().obtenerNombre() + " " + enf.obtenerPuntaje1() + " - " +
                            enf.obtenerPuntaje2() + " " + p2Nombre : "Pendiente";
            System.out.printf("%-30s | %-19s | %-10s%n",
                    enf.obtenerParticipante1().obtenerNombre() + " vs " + p2Nombre,
                    fechaFormateada,
                    resultado);
        }
        System.out.println("-------------------------------------------------------------");
    }

    /**
     * Lanza excepción si se invoca.
     */
    public void mostrarBracket() {
        throw new UnsupportedOperationException("El formato de liga no soporta brackets");
    }
}