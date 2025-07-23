package org.Logica;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

/**
 * La clase {@code Enfrentamiento} representa un encuentro entre dos {@link Participante}s
 * dentro de un torneo. Gestiona el puntaje, estado, duración, disciplina y simulación
 * del enfrentamiento de forma automática.
 * <p>
 * Admite disciplinas con reglas específicas (como VALORANT o TENISDEMESA) o con lógica de duración.
 * Además, permite programar enfrentamientos en una fecha que queramos.
 * </p>
 *
 * @author Canito301, LeoRGO19
 */


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

    /**
     * Crea un nuevo enfrentamiento entre dos participantes en un torneo.
     *
     * @param p1 el primer participante.
     * @param p2 el segundo participante.
     * @param torneo el torneo en el cual se desarrolla el enfrentamiento.
     */

    public Enfrentamiento(Participante p1, Participante p2, Torneo torneo) {
        this.p1 = p1;
        this.p2 = p2;
        this.estado = new GestorEstadoEncuentro();
        this.notificador = new GestorNotificaciones((SujetoTorneo) torneo);
        this.simulador = new SimuladorPuntaje();
        this.disciplina = ((TorneoAbstracto) torneo).disciplina;
    }

    /**
     * Establece la fecha programada para el enfrentamiento.
     *
     * @param fecha la fecha y hora de inicio del enfrentamiento.
     */

    public void establecerFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Retorna la fecha programada del enfrentamiento.
     *
     * @return la fecha y hora programada.
     */

    public LocalDateTime obtenerFecha() {
        return fecha;
    }
    /**
     * Obtiene el estado actual del enfrentamiento.
     *
     * @return un entero que representa el estado (PENDIENTE, EN_CURSO o TERMINADO).
     */

    public int obtenerEstado() {
        return estado.obtenerEstado();
    }
    /**
     * Retorna el primer participante del enfrentamiento.
     *
     * @return el participante 1.
     */

    public Participante obtenerParticipante1() {
        return p1;
    }
    /**
     * Retorna el segundo participante del enfrentamiento.
     *
     * @return el participante 2.
     */
    public Participante obtenerParticipante2() {
        return p2;
    }

    /**
     * Retorna el puntaje actual del primer participante.
     *
     * @return puntaje de p1.
     */
    public int obtenerPuntaje1() {
        return puntaje1;
    }

    /**
     * Retorna el puntaje actual del segundo participante.
     *
     * @return puntaje de p2.
     */
    public int obtenerPuntaje2() {
        return puntaje2;
    }

    /**
     * Inicia el enfrentamiento, simulando su ejecución completa
     * según la disciplina asignada.
     * <p>
     * Si el enfrentamiento ya ha finalizado, no se ejecuta nuevamente.
     * </p>
     */
    //RECALCAR AQUI: Se dejó lógica aquí para las disciplinas que necesitaban comparar puntaje de los contrincantes,
    //ya que  necesitaba asignar puntos a los participantes y declararlos aquí, por eso no se movieron.

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

    /**
     * Simula un enfrentamiento hasta que un participante alcance una meta específica de puntaje.
     *
     * @param meta el puntaje objetivo que determina el fin del enfrentamiento.
     */
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

    /**
     * Simula enfrentamientos cuya lógica se basa en una duración determinada en ciclos.
     */
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

    /**
     * Si el enfrentamiento tiene una fecha futura programada, espera hasta ese momento para comenzar.
     */
    private void esperarSiHayFechaProgramada() {
        if (fecha != null && LocalDateTime.now().isBefore(fecha)) {
            try {
                Thread.sleep(Duration.between(LocalDateTime.now(), fecha).toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Retorna el participante ganador.
     *
     * @return el participante con mayor puntaje, o {@code null} si el encuentro no ha terminado.
     */
    public Participante obtenerGanador() {
        if (!terminoEncuentro) return null;
        return (puntaje1 > puntaje2) ? p1 : p2;
    }

    /**
     * Retorna el participante perdedor.
     *
     * @return el participante con menor puntaje, o {@code null} si el encuentro no ha terminado.
     */
    public Participante obtenerPerdedor() {
        if (!terminoEncuentro) return null;
        return (puntaje1 > puntaje2) ? p2 : p1;
    }

    /**
     * Devuelve la duración del enfrentamiento en segundos.
     *
     * @return duración en segundos.
     */
    public int getDuracion() {
        return (int) duracion;
    }

    /**
     * Indica si el enfrentamiento ha finalizado.
     *
     * @return {@code true} si el encuentro ha terminado, {@code false} en caso contrario.
     */
    public boolean haTerminadoEncuentro() {
        return terminoEncuentro;
    }

    /**
     * Imprime los datos del enfrentamiento: nombres, puntajes, ganador y duración.
     */
    public void verEnfrentamiento() {
        Participante ganador = obtenerGanador();
        String ganadorStr = (ganador == null) ? "aún no decidido" : ganador.obtenerNombre();
        String p2Nombre = (p2 != null) ? p2.obtenerNombre() : "BYE";
        System.out.println(p1.obtenerNombre() + " " + puntaje1 + " - " + puntaje2 + " " + p2Nombre);
        System.out.println("Ganador: " + ganadorStr);
        System.out.println("Duración: " + (terminoEncuentro ? (int) duracion : 0));
    }

    /**
     * Devuelve el enfrentamiento, incluyendo fecha programada.
     *
     * @return descripción del enfrentamiento.
     */
    @Override
    public String toString() {
        String p2Nombre = (p2 != null) ? p2.obtenerNombre() : "BYE";
        String fechaFormateada = (fecha != null) ? fecha.toString() : "Sin fecha";
        return p1.obtenerNombre() + " vs " + p2Nombre + " (Programado para: " + fechaFormateada + ")";
    }

    /**
     * Hace una pausa en la ejecución del hilo por la cantidad de milisegundos indicada.
     *
     * @param milisegundos cantidad de tiempo a dormir.
     */
    private void dormir(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}