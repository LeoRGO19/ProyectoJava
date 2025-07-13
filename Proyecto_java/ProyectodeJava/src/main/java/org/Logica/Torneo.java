package org.Logica;

public interface Torneo {
    void configurar(String nombre, String disciplina, int maxParticipantes);
    void agregarParticipante(Participante p) throws TorneoException;
    void eliminarParticipante(Participante p) throws TorneoException;
    void iniciarTorneo() throws TorneoException;
    void generarBracket() throws TorneoException;
    void generarCalendario();
    void generarEnfrentamientos() throws TorneoException;
    void generarTabla() throws TorneoException;
    void verEstado();
}