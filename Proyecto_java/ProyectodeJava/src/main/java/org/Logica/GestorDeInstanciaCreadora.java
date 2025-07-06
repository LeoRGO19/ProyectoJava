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

    public Creador obtenerCreador(FormatoTorneo formato) {
        switch (formato) {
            case ELIMINACION_SIMPLE:
                return new EliminacionSimpleCreador();
            case ELIMINACION_DOBLE:
                return new EliminacionDobleCreador();
            case LIGA:
                return new LigaCreador();
            default:
                throw new IllegalArgumentException("Formato de torneo no soportado: " + formato);
        }
    }
}