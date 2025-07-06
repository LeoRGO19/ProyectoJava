package org.Logica;
//es como una combinación de Singleton con Factory method podría decirse
public class TorneoFactory {
    private static TorneoFactory instancia;
    private java.util.List<Torneo> torneos; //solo por si decidimos hacer varios
    //aunque por ahora lo podemos tomar como una forma de guardar un torneo

    TorneoFactory() {
        torneos = new java.util.ArrayList<>();
    }

    public static TorneoFactory obtenerInstancia() {
        if (instancia == null) {
            instancia = new TorneoFactory();
        }
        return instancia;
    }
    /*
    public Torneo crearTorneo() {
        Torneo torneo = crearInstanciaTorneo();
        configurarTorneo(torneo);
        torneos.add(torneo);
        return torneo;
    }
    */
    /*
    protected Torneo crearInstanciaTorneo() {
        return new TorneoEliminacionSimple(); //puede ser un switch
    }*/

    public void configurarTorneo(Torneo torneo) {
        //Por implementar, según yo puede ser la configuración inicial que no podrá ser cambiada obviamente


    }


    public java.util.List<Torneo> obtenerTorneos() {
        return new java.util.ArrayList<>(torneos);
    }
}