package org.Logica;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

public class Enfrentamiento {
    private Participante p1;
    private Participante p2;
    private int puntaje1 = 0;
    private int puntaje2 = 0;
    protected boolean resultadoP1 = false;
    protected boolean resultadoP2 = false;
    protected boolean terminoEncuentro = false;
    private float duracion;   //aqui es donde se va a guardar el tiempo del enfrentamiento como tal
    private int duracionPrevista = 2; //duración que puede durar un encuentro (sin considerar el tiempo extra obvio)
    private LocalTime inicio;
    private LocalTime fin;

    public Enfrentamiento(Participante n1, Participante n2){
        this.p1 = n1;
        this.p2 = n2;
    }

    /*esto lo pensé así pq no es como que todos los enfrentamientos vayan
    * a empezar al mismo tiempo creo yo, ejm que uno ganó está esperando a que
    * el otro gane para que empiece su enfrentamiento con ese*/
    public void iniciarEncuentro() {
        this.inicio = LocalTime.now();
        int tiempo = 0;
        Random r = new Random();

        while (tiempo < duracionPrevista) {

            int posiblesPuntos1 = r.nextInt(2); // puede hacer entre 0 y 1 puntos
            int posiblesPuntos2 = r.nextInt(2);
            //esto es super innecesario pero lo hace más realista xd
            this.puntaje1 += simularPuntaje(posiblesPuntos1);
            this.puntaje2 += simularPuntaje(posiblesPuntos2);
            System.out.println(p1 + " " + puntaje1 + " - " + puntaje2 + " " + p2);
            // a segundos pq asi es más facil de probar
            tiempo = (int)Duration.between(inicio, LocalTime.now()).getSeconds();
            //casting a int pq lw devuelve long y eso consume más recursos

        }

        System.out.println("Tiempo reglamentario terminado");
        System.out.println(p1 + ": " + puntaje1 + " - " + puntaje2 + " : " + p2);

        if (puntaje1 == puntaje2) {
            System.out.println("Empate detectado. Iniciando tiempo extra...");
            resolverEmpate();
        } else {
            asignarGanador();
        }

        System.out.println("victoria de " + getGanador());
        this.fin = LocalTime.now();
        this.duracion = getDuracion();
        this.terminoEncuentro = true;
    }
    //metodo que simula un puntaje realista
    private int simularPuntaje(int puntosMaximos) {
        Random r = new Random();
        int puntos = 0;
        // puntos maximos es un tema, porque por disciplina lw puede cambiar
        //mi idea es hacer como una constante que tenga los puntos posibles de cada disciplina
        // ejm, que puntos maximos de basquetbol sean 3 puntos, como puede hacer 2(dentro del area de 3, o 1 (tiro libre) o 0
        // o futbol q puede hacer 0 o 1
        // puede que haya que cambiarlo pq por ejm valorant si o si en una ronda se hace un punto
        for (int i = 0; i < puntosMaximos; i++) {

            try {
                int minutosSimulados = 1 + r.nextInt(3); // puede durar de 1 a 5 min para posiblemente sumar puntos
                Thread.sleep(minutosSimulados * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // 50/50 de que haga un punto
            if (r.nextBoolean()) {
                puntos++;

            }
        }

        return puntos;
    }
    //por si hay empate el bucle arreglado
    private void resolverEmpate() {
        Random r = new Random();
        while (puntaje1 == puntaje2) {
            duracionPrevista += 3;
            try {
                Thread.sleep(1000*(1 + r.nextInt(3)));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            //pueden hacer punto o no, pero si uno hace gana, por eso el break
            if (r.nextBoolean()) {
                puntaje1++;
                break;
            }
            if (r.nextBoolean()) {
                puntaje2++;
                break;
            }


        }
        System.out.println("Tiempo extra: " + p1 + " " + puntaje1 + " - " + puntaje2 + " " + p2);
        asignarGanador();
    }
    //para no escribir lw 2 veces
    private void asignarGanador() {
        if (puntaje1 > puntaje2) {
            resultadoP1 = true;
        } else {
            resultadoP2 = true;
        }
    }

    public Participante getGanador() {
        if (!terminoEncuentro) {
            if (resultadoP1) {
                return p1;
            } else {
                return p2;
            }
        } else {
            return null;
        }

    }
    //se modificó a segundos por compatibilidad
    public int getDuracion() {
        if (inicio == null || fin == null) {
            return 0;
        }
        return (int) Duration.between(inicio, fin).getSeconds();
    }
    //la dejé pero si acaso le veo uso como para ver estado
    public boolean TerminoEncuentro(){ //DEJAR PARA DESPUES
        return terminoEncuentro;
    }
    public void verEnfrentamiento() {
        Participante ganador = getGanador();
        String x = "";
        if(ganador == null) {
            x = "aún no decidido";
        }
        System.out.println(p1 + " " + puntaje1 + " - " + puntaje2 + " " + p2);
        System.out.println("Ganador: " + getGanador() + x);
        System.out.println("Duración: " + (int)Duration.between(inicio, LocalTime.now()).getSeconds());
    }




}