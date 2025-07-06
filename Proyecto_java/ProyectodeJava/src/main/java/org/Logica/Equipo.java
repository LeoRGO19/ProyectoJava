package org.Logica;

import java.util.ArrayList;

public class Equipo extends Participante {
    private ArrayList<IndividuoParticipante> listaEquipo;

    public Equipo(String nombre, String contacto) {
        super(nombre, contacto);
        this.listaEquipo = new ArrayList<>();
    }

    public void agregarMiembro(IndividuoParticipante miembro) {
        listaEquipo.add(miembro);
    }

    public ArrayList<IndividuoParticipante> obtenerListaEquipo() {
        return listaEquipo;
    }

    @Override
    public int obtenerPuntaje() {
        return 0; // Implementar lógica de puntaje si es necesario
    }
}