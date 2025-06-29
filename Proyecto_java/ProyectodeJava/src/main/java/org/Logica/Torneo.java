package org.Logica;

public interface Torneo {
    private void configurar(String nombre, String disciplina, int maxParticipantes) {

    }
    //metodos por implementar, no se si serán traidos por a las clases concretas por otra interfaz, por ejm ver estadisticas
    private void agregarParticipante(Participante p){}
    private void eliminarParticipante(Participante p){}
    private void iniciarTorneo(){}
    private void crearEsquema(int tipo){}/*esto puesto que sabemos que hay que agregar los
     participantes primero antes de empezar el torneo para que sea posible el formato*/
    private void generarEnfrentamientos(){}
    public void verEstadisticas();
}