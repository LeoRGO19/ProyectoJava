package org.Logica;

public interface Torneo {
    void configurar(String nombre, Disciplina disciplina, int maxParticipantes);
    void agregarParticipante(Participante p);
    void eliminarParticipante(Participante p);
    void iniciarTorneo();
    void crearBracked(FormatoTorneo tipo);
    void generarCalendario();
    void generarEnfrentamientos();
    void verEstado();
}