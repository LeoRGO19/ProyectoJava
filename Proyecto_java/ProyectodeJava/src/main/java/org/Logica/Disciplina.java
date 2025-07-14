package org.Logica;

/**
 * Enumeración que representa las distintas disciplinas deportivas o de competencia
 * disponibles para organizar torneos en el sistema.
 *
 * <p>Esta enumeración permite validar y restringir las disciplinas permitidas
 * en los torneos, garantizando que sólo se utilicen disciplinas soportadas.</p>
 *
 * <p>Las disciplinas soportadas actualmente son:</p>
 * <ul>
 *   <li>{@link #FUTBOL}</li>
 *   <li>{@link #TENISDEMESA}</li>
 *   <li>{@link #TIROCONARCO}</li>
 *   <li>{@link #TENIS}</li>
 *   <li>{@link #BASKETBALL}</li>
 *   <li>{@link #LOL} (League of Legends)</li>
 *   <li>{@link #VALORANT}</li>
 *   <li>{@link #ROCKETLEAGUE}</li>
 *   <li>{@link #CSGO} (Counter-Strike)</li>
 *   <li>{@link #FIFA}</li>
 * </ul>
 *
 * <p>Para validación de entradas, se puede utilizar el método {@link #valueOf(String)}.</p>
 *
 * @see Torneo
 * @author LeoRGO19
 */
public enum Disciplina {

    /**FUTBOL */
    FUTBOL,
    /**TENISDEMESA*/
    TENISDEMESA,
    /**TIROCONARCO,*/
    TIROCONARCO,
    /**TENIS*/
    TENIS,
    /**BASKETBALL*/
    BASKETBALL,
    /**LOL*/
    LOL,
    /**VALORANT*/
    VALORANT,
    /** ROCKETLEAGUE */
    ROCKETLEAGUE,
    /** CSGO */
    CSGO,
    /** FIFA */
    FIFA;
}
