package org.Logica;

public class GestorEstadoEncuentro {
    public static final int PENDIENTE = 0;
    public static final int EN_CURSO = 1;
    public static final int TERMINADO = 2;

    private int estado = PENDIENTE;

    public void iniciar() { estado = EN_CURSO; }
    public void terminar() { estado = TERMINADO; }
    public int obtenerEstado() { return estado; }

    public boolean estaPendiente() { return estado == PENDIENTE; }
    public boolean estaTerminado() { return estado == TERMINADO; }
}
