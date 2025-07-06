package org.Logica;

public interface Torneo {
    void configurar(String nombre, String disciplina, int maxParticipantes);
    void agregarParticipante(Participante p);
    void eliminarParticipante(Participante p);
    void iniciarTorneo();
    void generarBracket();
    void generarCalendario();
    void generarEnfrentamientos();
    void generarTabla();
    void verEstado();
}