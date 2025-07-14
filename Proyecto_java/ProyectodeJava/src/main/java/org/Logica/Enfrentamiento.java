package org.Logica;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Representa un enfrentamiento entre dos {@link Participante}s dentro de un torneo
 * controlado por un {@link SujetoTorneo}.
 *
 * <p>Simula el desarrollo del partido según la disciplina del torneo,
 * actualiza puntajes, resuelve empates con tiempo extra y notifica a los
 * observadores del torneo cada vez que ocurre un cambio relevante.</p>
 *
 * <ol>
 *   <li>El enfrentamiento se crea en estado <em>Pendiente</em>.</li>
 *   <li>Se establece una fecha programada (opcional).</li>
 *   <li>Al invocar {@link #iniciarEncuentro()}, el estado pasa a <em>En curso</em>
 *       y se simula la duración reglamentaria.</li>
 *   <li>Si hay empate, se llama a {@link #resolverEmpate()} para tiempo extra.</li>
 *   <li>Finalmente se asigna un ganador con {@link #asignarGanador()} y el estado pasa a <em>Terminado</em>.</li>
 * </ol>
 *
 * <p>Los puntajes máximos por jugada varían según la disciplina (Fútbol, Valorant, etc.);
 * véase {@link #obtenerPuntosPorDisciplina(Random)}.</p>
 *
 * @author Canito301, LeoRGO19
 *
 */

public class Enfrentamiento {
    private Participante p1;
    private Participante p2;

    private int puntaje1 = 0;
    private int puntaje2 = 0;
    private int duracionPrevista = 2;
    private int estado = 0; // 0: Pendiente, 1: En curso, 2: Terminado

    private float duracion;   

    boolean resultadoP1 = false;
    boolean resultadoP2 = false;
    boolean terminoEncuentro = false;


    private LocalTime inicio;
    private LocalTime fin;
    private LocalDateTime fecha;

    private SujetoTorneo torneo;


    /* Constructores       */

    /**
     * Crea un enfrentamiento entre dos participantes asociado a un torneo.
     *
     * @param n1     Primer participante.
     * @param n2     Segundo participante.
     * @param torneo Instancia del torneo al que pertenece (utilizado para notificaciones).
     */
    public Enfrentamiento(Participante n1, Participante n2, Torneo torneo){
        this.p1 = n1;
        this.p2 = n2;
        this.torneo = (SujetoTorneo) torneo;
    }

    /* Getters y setters */

    public int obtenerEstado() {return estado;}
    public void establecerFecha(LocalDateTime fecha) {this.fecha = fecha;}
    public LocalDateTime obtenerFecha() {return fecha;}
    public Participante obtenerParticipante1() {return p1;}
    public Participante obtenerParticipante2() {return p2;}
    public int obtenerPuntaje1() {return puntaje1;}
    public int obtenerPuntaje2() {return puntaje2;}

    /**
     * Notifica a los observadores del torneo que los resultados han sido actualizados.
     */

    private void notificarActualizacion() {
        if (torneo != null) {
            torneo.notificarObservadores(TipoEvento.RESULTADOS_ACTUALIZADOS, this);
        }
    }

    /**
     * Inicia la simulación del enfrentamiento.
     * Respeta la {@code fecha} programada (espera si es futura), notifica estados
     * y resuelve automáticamente empates con tiempo extra.
     */

    public void iniciarEncuentro() {
        if (terminoEncuentro) return;
        LocalDateTime ahora = LocalDateTime.now();
        if (fecha != null && ahora.isBefore(fecha)) {
            long segundosEspera = Duration.between(ahora, fecha).getSeconds();
            if (segundosEspera > 0) {
                try {
                    System.out.println("Esperando " + segundosEspera + " segundos para iniciar " + this);
                    Thread.sleep(segundosEspera * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        estado = 1; // En curso
        inicio = LocalTime.now();
        int temp1 = 0;
        int temp2 = 0;
        Random r = new Random();
        int tiempo = 0;
        System.out.println(p1 + " " + puntaje1 + " - " + puntaje2 + " " + p2);
        while (tiempo < duracionPrevista) {
            temp1 = puntaje1;
            temp2 = puntaje2;
            Boolean turno = r.nextBoolean();
            int posiblesPuntos = obtenerPuntosPorDisciplina(r);
            if (turno) {this.puntaje1 += simularPuntaje(posiblesPuntos);} else {this.puntaje2 += simularPuntaje(posiblesPuntos);}
            notificarActualizacion();
            if (posiblesPuntos > 0 && ( temp1 < puntaje1 || temp2 < puntaje2 )) {
                System.out.println(p1 + " " + puntaje1 + " - " + puntaje2 + " " + p2);
                notificarActualizacion();
            }
            tiempo = (int)Duration.between(inicio, LocalTime.now()).getSeconds();
        }

        System.out.println("Tiempo reglamentario terminado");
        System.out.println(p1 + ": " + puntaje1 + " - " + puntaje2 + " : " + p2);

        if (puntaje1 == puntaje2) {
            System.out.println("Empate detectado. Iniciando tiempo extra...");
            resolverEmpate();
        } else {
            asignarGanador();
            estado = 2;
            this.terminoEncuentro = true;
        }

        notificarActualizacion();
        System.out.println("victoria de " + obtenerGanador());
        this.fin = LocalTime.now();
        this.duracion = getDuracion();

    }

    /**
     * Devuelve la cantidad máxima de puntos que se pueden anotar en una jugada,
     * dependiendo de la disciplina del torneo.
     *
     * @param r Instancia de {@link Random} para generar valores aleatorios.
     * @return  Número máximo de puntos posibles.
     */

    private int obtenerPuntosPorDisciplina(Random r) {
        Disciplina disciplina = ((TorneoAbstracto) torneo).disciplina;
        switch (disciplina) {
            case FUTBOL:
                return r.nextInt(2);
            case FIFA:
                return r.nextInt(2); // 0 o 1 punto
            case BASKETBALL:
                return r.nextInt(4); // 0, 1, 2 o 3 puntos
            case TIROCONARCO:
                return r.nextInt(11); // 0 a10 puntos
            case VALORANT:
                return r.nextInt(2);
            case CSGO:
                return r.nextInt(2);
            case ROCKETLEAGUE:
                return r.nextInt(3); // 0, 1 o 2 puntos
            default:
                return r.nextInt(2); // Por defecto, 0 o 1 punto
        }
    }

    /**
     * Simula la obtención de puntos en una jugada, durmiendo brevemente para
     * imitar el paso del tiempo.
     *
     * @param puntosMaximos Valor máximo de puntos posibles en la jugada.
     * @return Puntos conseguidos (0 .. puntosMaximos).
     */

    private int simularPuntaje(int puntosMaximos) {
        Random r = new Random();
        int puntos = 0;
        for (int i = 0; i < puntosMaximos; i++) {
            try {
                int minutosSimulados = 1 + r.nextInt(3); // puede durar de 1 a 5 min para posiblemente sumar puntos
                Thread.sleep(minutosSimulados * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (r.nextBoolean()) {
                puntos++;
            }
        }

        return puntos;
    }

    /**
     * Resuelve un empate agregando tiempo extra hasta que haya un ganador.
     */

    private void resolverEmpate() {
        Random r = new Random();
        while (puntaje1 == puntaje2) {
            duracionPrevista += 3;
            try {
                Thread.sleep(1000*(1 + r.nextInt(3)));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int posiblesPuntos = obtenerPuntosPorDisciplina(r);
            if (r.nextBoolean()) {
                puntaje1 += simularPuntaje(posiblesPuntos);
            } else {
                puntaje2 += simularPuntaje(posiblesPuntos);
            }
            if (puntaje1 != puntaje2) {
                break;
            }
        }
        asignarGanador();
        estado = 2;
        notificarActualizacion();
        System.out.println("Tiempo extra: " + p1 + " " + puntaje1 + " - " + puntaje2 + " " + p2);
        this.terminoEncuentro = true;
    }

    /**
     * Asigna el ganador del encuentro según los puntajes alcanzados.
     */

    private void asignarGanador() {
        if (puntaje1 > puntaje2) {
            resultadoP1 = true;
        } else {
            resultadoP2 = true;
        }
    }

    /**
     * Obtiene el {@link Participante} ganador si el encuentro ha finalizado.
     *
     * @return Ganador, o {@code null} si aún no hay resultado definitivo.
     */

    public Participante obtenerGanador() {
        if (terminoEncuentro) {
            if (resultadoP1) {
                return p1;
            } else if (resultadoP2) {
                return p2;
            }
        }
        return null;
    }

    /**
     * Obtiene el perdedor del encuentro si ha finalizado.
     *
     * @return Perdedor, o {@code null} si el encuentro no ha concluido.
     */

    public Participante obtenerPerdedor() {
        if (terminoEncuentro) {
            if (resultadoP1) {
                return p2;
            } else if (resultadoP2) {
                return p1;
            }
        }
        return null;
    }

    /**
     * Devuelve la duración total, en segundos, del encuentro completado.
     *
     * @return Duración en segundos, o 0 si no se ha finalizado.
     */

    public int getDuracion() {
        if (inicio == null || fin == null) {
            return 0;
        }
        return (int) Duration.between(inicio, fin).getSeconds();
    }

    /**
     * Indica si el enfrentamiento ya terminó.
     *
     * @return {@code true} si finalizó, de lo contrario {@code false}.
     */

    public boolean haTerminadoEncuentro(){
        return terminoEncuentro;
    }

    /**
     * Muestra en consola los detalles actuales del enfrentamiento (puntajes, ganador, duración).
     */

    public void verEnfrentamiento() {
        Participante ganador = obtenerGanador();
        String ganadorStr = (ganador == null) ? "aún no decidido" : ganador.obtenerNombre();
        String p2Nombre = (p2 != null) ? p2.obtenerNombre() : "BYE";
        System.out.println(p1.obtenerNombre() + " " + puntaje1 + " - " + puntaje2 + " " + p2Nombre);
        System.out.println("Ganador: " + ganadorStr);
        System.out.println("Duración: " + (terminoEncuentro ? (int) duracion : 0));
        if (fecha != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            System.out.println("Programado para: " + fecha.format(formatter));
        }
    }

    /**
     *Representación textual: muestra participantes y la fecha programada.
     *
     * @return Cadena descriptiva del enfrentamiento.
     */

    @Override
    public String toString() {
        String p2Nombre = (p2 != null) ? p2.obtenerNombre() : "BYE";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String fechaFormateada = (fecha != null) ? fecha.format(formatter) : "Sin fecha";
        return p1.obtenerNombre() + " vs " + p2Nombre + " (Programado para: " + fechaFormateada + ")";
    }
}