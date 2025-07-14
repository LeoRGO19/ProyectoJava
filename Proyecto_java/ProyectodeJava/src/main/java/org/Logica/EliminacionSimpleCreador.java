package org.Logica;

/**
 * {@code EliminacionSimpleCreador} es una clase concreta que extiende de {@link Creador}
 * y se encarga de crear instancias de {@link TorneoEliminacionSimple}.
 *
 * <p>Este creador valida los parámetros necesarios y construye un torneo con el
 * formato de eliminación simple, en el cual los participantes son eliminados tras perder
 * un enfrentamiento hasta que quede un único ganador.</p>
 *
 * <p>           </p>
 * <pre>{@code
 * Creador creador = new EliminacionSimpleCreador();
 * Torneo torneo = creador.crearTorneo("Torneo 1", "FUTBOL", 8);
 * }</pre>
 *
 * @author LeoRGO19
 * @see TorneoEliminacionSimple
 * @see Torneo
 */
public class EliminacionSimpleCreador extends Creador {

    /**
     * Crea una nueva instancia de {@link TorneoEliminacionSimple} con los parámetros especificados.
     *
     * @param nombre            Nombre del torneo.
     * @param disciplina        Disciplina en formato {@code String} (debe estar definida en {@link Disciplina}).
     * @param maxParticipantes  Número máximo de participantes que puede tener el torneo.
     * @return Instancia de {@link TorneoEliminacionSimple}.
     * @throws TorneoException Si alguno de los parámetros es inválido (por ejemplo, nombre vacío, disciplina no válida o número de participantes menor a 2).
     */
    @Override
    public Torneo crearTorneo(String nombre, String disciplina, int maxParticipantes) throws TorneoException {
        validarParametros(nombre, disciplina, maxParticipantes);
        return new TorneoEliminacionSimple(nombre, disciplina, maxParticipantes);
    }
}