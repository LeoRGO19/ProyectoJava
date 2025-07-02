package org.Logica;

public class IndividuoParticipante extends Participante{
    private String nombre;
    private String apellido;
    private int edad; //ENTERO O STRING, AHI VEMOS
    private int puntaje;

    public IndividuoParticipante(String NOMBRE, String APELLIDO, int EDAD){
        this.nombre = NOMBRE;
        this.apellido = APELLIDO;
        this.edad = EDAD;
    }

    @Override
    public int obtenerPuntaje(){

        return puntaje; //EDITAR
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public int getEdad(){
        return edad;
    }
    @Override
    public String toString(){
        return nombre + " " + apellido + " de " + edad + " años.";
    }


}
