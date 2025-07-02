package org.Logica;

import java.util.ArrayList;

public class Equipo extends Participante{
private String nombre;
private ArrayList<IndividuoParticipante> listaEquipo;
private int puntaje;

    public Equipo(String NOMBRE){
        this.nombre = NOMBRE;
    }


    public String getNombre() {
        return nombre;
    }

    public ArrayList<IndividuoParticipante> getListaEquipo() {
        return listaEquipo;
    }

    @Override
    public int obtenerPuntaje(){

        return puntaje; //EDITAR
}



}
