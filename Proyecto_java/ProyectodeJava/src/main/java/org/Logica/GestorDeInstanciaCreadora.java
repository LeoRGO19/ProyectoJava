package org.Logica;

public class GestorDeInstanciaCreadora {
    private static final GestorDeInstanciaCreadora INSTANCE = new GestorDeInstanciaCreadora();

    private GestorDeInstanciaCreadora() {
        if (INSTANCE != null) {
            throw new RuntimeException("Utilice getInstance() para obtener la única instancia de esta clase.");
        }
    }

    public static GestorDeInstanciaCreadora getInstance() {
        return INSTANCE;
    }

    public Creador obtenerCreador(FormatoTorneo formato) throws TorneoException {
        return switch (formato) {
            case ELIMINACION_SIMPLE -> new EliminacionSimpleCreador();
            case LIGA -> new LigaCreador();
            default -> throw new TorneoException("Formato de torneo no soportado: " + formato);
        };
    }
}