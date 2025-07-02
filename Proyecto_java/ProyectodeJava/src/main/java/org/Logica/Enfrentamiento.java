package org.Logica;

public class Enfrentamiento {
    private Participante p1;
    private Participante p2;
    private int puntaje1 = 0;
    private int puntaje2 = 0;
    protected boolean resultadoP1 = false;
    protected boolean resultadoP2 = false;
    protected boolean terminoencuentro = false;
    private int duracion;

    public Enfrentamiento(Participante n1, int PUNTAJE1, Participante n2, int PUNTAJE2){
        this.p1 = n1;
        this.p2 = n2;
        this.puntaje1 = PUNTAJE1;
        this.puntaje2 = PUNTAJE2;
    }

    public Participante getGanador(int n1, int n2){
        if (n1>n2){
            resultadoP1 = true;
            terminoencuentro = true;
            return p1;
        }
        else if (n2>n1){
            resultadoP2 = true;
            terminoencuentro = true;
            return p2;
        }
        else{

            return desempate(0,35); //DESPUES SE CAMBIA LOS PUNTAJES
        }
    }
    public Participante desempate(int puntaje1Desempate, int puntaje2Desempate){ //ARREGLAR BUCLE DE EMPATES
        this.puntaje1 += puntaje1Desempate;
        this.puntaje2 += puntaje2Desempate;
        return getGanador(puntaje1, puntaje2);

    }



    public int getDuracion(){ //CAMBIAR A TIME
        return 0;
    }

    public boolean TerminoEncuentro(){ //DEJAR PARA DESPUES
        return terminoencuentro;
    }
    public void verEnfrentamiento(){}




}