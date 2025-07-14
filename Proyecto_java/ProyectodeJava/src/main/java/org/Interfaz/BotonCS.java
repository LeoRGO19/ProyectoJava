package org.Interfaz;

import org.Logica.FormatoTorneo;

import javax.swing.*;
import java.awt.*;

/**
 * Botón personalizado para seleccionar la disciplina "CS:GO" dentro de la interfaz.
 *
 * <p>Al presionarlo, cambia el panel actual a {@link PanelTipoTorneo} y
 * establece la palabra clave "CSGO" en el navegador para filtrar o configurar el torneo.</p>
 *
 * <p>Hereda de {@link BotonBase} y define la configuración visual y el comportamiento
 * específico para la opción CS:GO.</p>
 *
 * @author Canito301
 */
public class BotonCS extends BotonBase {

    /**
     * Constructor que inicializa el botón con referencia al marco principal.
     *
     * @param frame El {@link JFrame} padre donde se muestra el botón.
     */
    public BotonCS(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón:
     * tamaño, colores, cursor y la imagen representativa.
     */
    @Override
    public void configurar() {
        setBounds(200, 400, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/cs_icono.jpg");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción ejecutada al presionar el botón:
     * <ul>
     *   <li>Agrega el panel {@link PanelVideojuegos} al historial de navegación.</li>
     *   <li>Cambia la vista actual al panel {@link PanelTipoTorneo}.</li>
     *   <li>Establece la palabra clave "CSGO" para la selección del torneo.</li>
     *   <li>Imprime en consola un mensaje indicando que el botón fue presionado.</li>
     * </ul>
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelVideojuegos(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "CSGO";
        System.out.println("¡Botón CSGO presionado!");
    }
}
