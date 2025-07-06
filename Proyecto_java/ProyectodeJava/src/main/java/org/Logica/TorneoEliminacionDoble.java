package org.Logica;

public class TorneoEliminacionDoble extends TorneoAbstracto implements Torneo, SujetoTorneo {


    public TorneoEliminacionDoble(String nombre, String disciplina) {
        super(nombre, disciplina);

    }

    @Override
    public void configurar(String nombre, String disciplina, int maxParticipantes) {
        super.configurar(nombre, disciplina, maxParticipantes);
    }

    @Override
    public void agregarParticipante(Participante p) {
        super.agregarParticipante(p);
    }

    @Override
    public void eliminarParticipante(Participante p) {
        super.eliminarParticipante(p);
    }

    @Override
    public void iniciarTorneo() {
        super.iniciarTorneo();
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

    @Override
    public void generarBracket() {

    }

    @Override
    public void generarTabla() {
        throw new UnsupportedOperationException("El formato de eliminación doble no soporta tablas de clasificación");
    }

    @Override
    public void generarEnfrentamientos() {
    }

    @Override
    public void generarCalendario() {

    }

    @Override
    public void verEstado() {

    }

    public void mostrarBracket() {

    }

    @Override
    public String toString() {
        return "TorneoEliminacionDoble creado.";
    }
}