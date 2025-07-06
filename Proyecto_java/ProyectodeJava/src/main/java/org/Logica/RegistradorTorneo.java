package org.Logica;

public class RegistradorTorneo implements ObservadorTorneo {
    @Override
    public void actualizar(EventoTorneo evento) {
        System.out.println("Evento: " + evento.getTipo() + " en torneo " + evento.getTorneo().getClass().getSimpleName());
        switch (evento.getTipo()) {
            case PARTICIPANTE_AGREGADO:
                System.out.println("Participante añadido: " + ((Participante) evento.getDatos()).obtenerNombre());
                break;
            case PARTICIPANTE_ELIMINADO:
                System.out.println("Participante eliminado: " + ((Participante) evento.getDatos()).obtenerNombre());
                break;
            case TORNEO_INICIADO:
                System.out.println("Torneo iniciado");
                break;
            case ENFRENTAMIENTOS_GENERADOS:
                System.out.println("Enfrentamientos generados: " + evento.getDatos());
                break;
            case RESULTADOS_ACTUALIZADOS:
                System.out.println("Resultados actualizados: " + evento.getDatos());
                break;
            case TORNEO_FINALIZADO:
                String mensaje = (evento.getDatos() != null) ?
                        "Torneo finalizado: Ganador: " + ((Participante) evento.getDatos()).obtenerNombre() :
                        "Torneo finalizado: Sin ganador";
                System.out.println(mensaje);
                break;
        }
    }
}