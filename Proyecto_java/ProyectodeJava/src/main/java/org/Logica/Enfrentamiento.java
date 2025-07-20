package org.Logica;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class Enfrentamiento {
    private Participante p1, p2;
    private int puntaje1 = 0, puntaje2 = 0;
    private boolean terminoEncuentro = false;

    private LocalDateTime fecha;
    private LocalTime inicio, fin;
    private float duracion;

    private final GestorEstadoEncuentro estado;
    private final GestorNotificaciones notificador;
    private final SimuladorPuntaje simulador;
    private final Disciplina disciplina;
    private final Random rand = new Random();

    public Enfrentamiento(Participante p1, Participante p2, Torneo torneo) {
        this.p1 = p1;
        this.p2 = p2;
        this.estado = new GestorEstadoEncuentro();
        this.notificador = new GestorNotificaciones((SujetoTorneo) torneo);
        this.simulador = new SimuladorPuntaje();
        this.disciplina = ((TorneoAbstracto) torneo).disciplina;
    }

    public void establecerFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime obtenerFecha() {
        return fecha;
    }

    public int obtenerEstado() {
        return estado.obtenerEstado();
    }

    public Participante obtenerParticipante1() {
        return p1;
    }

    public Participante obtenerParticipante2() {
        return p2;
    }

    public int obtenerPuntaje1() {
        return puntaje1;
    }

    public int obtenerPuntaje2() {
        return puntaje2;
    }

    public void iniciarEncuentro() {
        if (terminoEncuentro) return;

        puntaje1 = 0;
        puntaje2 = 0;
        terminoEncuentro = false;

        esperarSiHayFechaProgramada();
        estado.iniciar();
        inicio = LocalTime.now();

        if (disciplina == Disciplina.VALORANT || disciplina == Disciplina.CSGO) {
            simularHastaMeta(13);
        } else if (disciplina == Disciplina.TENISDEMESA) {
            simularHastaMeta(11);
        } else if (disciplina == Disciplina.TENIS) {
            simularHastaMeta(6);
        }else {
            simularDuracion();
        }

        // Resolver empate en deportes que usan simularHastaMeta o simularDuracion
        if (puntaje1 == puntaje2) {
            while (puntaje1 == puntaje2) {
                puntaje1 += simulador.simularSimple();
                puntaje2 += simulador.simularSimple();
                notificador.notificarResultadosActualizados(this);
                dormir(1000);
            }
        }

        estado.terminar();
        terminoEncuentro = true;
        fin = LocalTime.now();
        duracion = Duration.between(inicio, fin).toSeconds();
        notificador.notificarResultadosActualizados(this);

        System.out.println("Victoria de " + obtenerGanador().obtenerNombre());
    }

    private void simularHastaMeta(int meta) {
        while (true) {
            int puntajeTurnoP1 = rand.nextInt(100);
            int puntajeTurnoP2 = rand.nextInt(100);

            if (puntajeTurnoP1 > puntajeTurnoP2) {
                puntaje1++;
            } else if (puntajeTurnoP2 > puntajeTurnoP1) {
                puntaje2++;
            }

            if (meta == 11) {
                if ((puntaje1 >= 11 || puntaje2 >= 11) && Math.abs(puntaje1 - puntaje2) >= 2) {
                    break;
                }
            } else if (meta == 13) {
                if ((puntaje1 >= 13 || puntaje2 >= 13) && Math.abs(puntaje1 - puntaje2) >= 2) {
                    break;
                }
            } else if  (meta ==6) {
                if ((puntaje1 >= 6 || puntaje2 >= 6) && Math.abs(puntaje1 - puntaje2) >= 2){
                    break;
                }
                if ((puntaje1 == 7 && puntaje2 == 6) || (puntaje1 == 6 && puntaje2 == 7)){
                    break;
                }
            }


            else {
                if (puntaje1 >= meta || puntaje2 >= meta) {
                    break;
                }
            }

            notificador.notificarResultadosActualizados(this);
            dormir(1500);
        }

        notificador.notificarResultadosActualizados(this);
    }

    private void simularDuracion() {
        switch (disciplina) {
            case FUTBOL:
            case FIFA:
            case ROCKETLEAGUE:
                for (int i = 0; i < 90; i++) {
                    boolean turnoP1 = rand.nextBoolean();
                    int puntos = simulador.simularFutbol();
                    if (turnoP1) puntaje1 += puntos;
                    else puntaje2 += puntos;

                    notificador.notificarResultadosActualizados(this);
                    dormir(1000);
                }
                break;

            case BASKETBALL:
                for (int i = 0; i < 70; i++) {
                    boolean turnoP1 = rand.nextBoolean();
                    int puntos = simulador.simularBasketball();
                    if (turnoP1) puntaje1 += puntos;
                    else puntaje2 += puntos;

                    notificador.notificarResultadosActualizados(this);
                    dormir(1000);
                }
                break;

            case TIROCONARCO:
                for (int i = 0; i < 10; i++) {
                    puntaje1 += simulador.simularTiroConArco();
                    puntaje2 += simulador.simularTiroConArco();

                    notificador.notificarResultadosActualizados(this);
                    dormir(2000);
                }
                if (puntaje1 == puntaje2) {
                    while (puntaje1 == puntaje2) {
                        puntaje1 += simulador.simularTiroConArco();
                        puntaje2 += simulador.simularTiroConArco();

                        notificador.notificarResultadosActualizados(this);
                        dormir(1000);
                    }
                }
                break;

            default:
                for (int i = 0; i < 30; i++) {
                    boolean turnoP1 = rand.nextBoolean();
                    int puntos = simulador.simularSimple();
                    if (turnoP1) puntaje1 += puntos;
                    else puntaje2 += puntos;

                    notificador.notificarResultadosActualizados(this);
                    dormir(1000);
                }
                break;
        }
    }

    private void esperarSiHayFechaProgramada() {
        if (fecha != null && LocalDateTime.now().isBefore(fecha)) {
            try {
                Thread.sleep(Duration.between(LocalDateTime.now(), fecha).toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public Participante obtenerGanador() {
        if (!terminoEncuentro) return null;
        return (puntaje1 > puntaje2) ? p1 : p2;
    }

    public Participante obtenerPerdedor() {
        if (!terminoEncuentro) return null;
        return (puntaje1 > puntaje2) ? p2 : p1;
    }

    public int getDuracion() {
        return (int) duracion;
    }

    public boolean haTerminadoEncuentro() {
        return terminoEncuentro;
    }

    public void verEnfrentamiento() {
        Participante ganador = obtenerGanador();
        String ganadorStr = (ganador == null) ? "aún no decidido" : ganador.obtenerNombre();
        String p2Nombre = (p2 != null) ? p2.obtenerNombre() : "BYE";
        System.out.println(p1.obtenerNombre() + " " + puntaje1 + " - " + puntaje2 + " " + p2Nombre);
        System.out.println("Ganador: " + ganadorStr);
        System.out.println("Duración: " + (terminoEncuentro ? (int) duracion : 0));
    }

    @Override
    public String toString() {
        String p2Nombre = (p2 != null) ? p2.obtenerNombre() : "BYE";
        String fechaFormateada = (fecha != null) ? fecha.toString() : "Sin fecha";
        return p1.obtenerNombre() + " vs " + p2Nombre + " (Programado para: " + fechaFormateada + ")";
    }

    private void dormir(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}