package org.Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TorneoEliminacionSimple extends TorneoAbstracto implements Torneo, SujetoTorneo {
    private Enfrentamiento[][] rondas;
    private int rondaActual;
    private int numRondas;

    public TorneoEliminacionSimple(String nombre, String disciplina, int maxParticipantes){
        super(nombre, disciplina, maxParticipantes);
        rondaActual = 0;
        rondas = null;
        numRondas = 0;
    }

    @Override
    public void configurar(String nombre, String disciplina, int maxParticipantes) {
        super.configurar(nombre, disciplina, maxParticipantes);
    }

    public void agregarParticipante(Participante p) throws TorneoException{
        super.agregarParticipante(p);
    }

    public void eliminarParticipante(Participante p) throws TorneoException{
        super.eliminarParticipante(p);
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



    private boolean esPotenciaDeDos(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

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

    @Override
    public void iniciarTorneo() throws TorneoException{
        if (!esPotenciaDeDos(participantes.size())) {
            throw new TorneoException("Para iniciar el formato de eliminación simple eñ número de participantes debe ser potencia de 2");
        }
        super.iniciarTorneo();
        generarBracket();
        notificarObservadores(TipoEvento.TORNEO_INICIADO, this);
        while (rondaActual < numRondas) {
            verEstado();
            mostrarBracket();
            avanzarRonda();
        }
    }

    @Override
    public void generarTabla() throws TorneoException {
        throw new TorneoException("El formato de eliminación simple no soporta tablas de clasificación.");
    }

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
    }

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
    public void verCalendario(){
        calendario.mostrarCalendario();
    }

    public Enfrentamiento[][] obtenerRondas() {
        return rondas;
    }

    public int obtenerRondaActual() {
        return rondaActual;
    }

    public int obtenerNumRondas() {
        return numRondas;
    }
    @Override
    public String toString() {
        return "TorneoEliminacionSimple creado.";
    }
}