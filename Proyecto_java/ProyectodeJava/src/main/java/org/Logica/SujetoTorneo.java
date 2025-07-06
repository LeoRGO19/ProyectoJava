package org.Logica;

public interface SujetoTorneo {
    void registrarObservador(ObservadorTorneo observador);
    void eliminarObservador(ObservadorTorneo observador);
    void notificarObservadores(TipoEvento tipo, Object datos);
}
