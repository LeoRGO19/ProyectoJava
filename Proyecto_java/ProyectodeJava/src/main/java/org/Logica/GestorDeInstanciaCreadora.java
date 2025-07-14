package org.Logica;

/**
 * Clase singleton que gestiona la obtención de instancias de objetos tipo {@link Creador}
 * según el formato del torneo.
 * <p>
 * Esta clase asegura que solo exista una única instancia de {@code GestorDeInstanciaCreadora}
 * durante la ejecución de la aplicación.
 */
public class GestorDeInstanciaCreadora {
    private static final GestorDeInstanciaCreadora INSTANCE = new GestorDeInstanciaCreadora();

    /**
     * Constructor privado para evitar la creación directa de instancias.
     * Lanza una excepción si se intenta crear otra instancia, garantizando
     * el patrón singleton.
     */
    private GestorDeInstanciaCreadora() {
        if (INSTANCE != null) {
            throw new RuntimeException("Utiliza getInstance() para obtener la única instancia de esta clase.");
        }
    }

    /**
     * Devuelve la única instancia existente de {@code GestorDeInstanciaCreadora}.
     *
     * @return La instancia singleton de {@code GestorDeInstanciaCreadora}.
     */
    public static GestorDeInstanciaCreadora getInstance() {
        return INSTANCE;
    }

    /**
     * Obtiene un objeto {@link Creador} adecuado según el formato de torneo especificado.
     *
     * @param formato El formato del torneo para el cual se desea obtener un creador.
     * @return Una instancia de {@code Creador} correspondiente al formato indicado.
     * @throws TorneoException Si el formato de torneo no es soportado.
     */
    public Creador obtenerCreador(FormatoTorneo formato) throws TorneoException {
        return switch (formato) {
            case ELIMINACION_SIMPLE -> new EliminacionSimpleCreador();
            case LIGA -> new LigaCreador();
            default -> throw new TorneoException("Formato de torneo no soportado: " + formato);
        };
    }
}