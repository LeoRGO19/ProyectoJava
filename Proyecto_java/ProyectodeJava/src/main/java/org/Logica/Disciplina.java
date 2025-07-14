package org.Logica;

/**
 * Enumeración que representa las distintas disciplinas deportivas o de competencia
 * disponibles para organizar torneos.
 *
 * Esta enumeración se utiliza para validar y restringir las disciplinas permitidas
 * dentro del sistema de torneos.
 *
 * <p>Las disciplinas actualmente soportadas son:</p>
 * <ul>
 *   <li>FUTBOL</li>
 *   <li>TENISDEMESA</li>
 *   <li>TIROCONARCO</li>
 *   <li>TENIS</li>
 *   <li>BASKETBALL</li>
 *   <li>LOL (League of Legends)</li>
 *   <li>VALORANT</li>
 *   <li>ROCKETLEAGUE</li>
 *   <li>CSGO (Counter-Strike)</li>
 *   <li>FIFA</li>
 * </ul>
 *
 * <p>Puede ser utilizado mediante {@code Disciplina.valueOf(String)} para validar entradas.</p>
 *
 * @see Torneo
 * @author LeoRGO19
 */
public enum Disciplina {
    FUTBOL,
    TENISDEMESA,
    TIROCONARCO,
    TENIS,
    BASKETBALL,
    LOL,
    VALORANT,
    ROCKETLEAGUE,
    CSGO,
    FIFA;
}
