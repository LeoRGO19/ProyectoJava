package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Representa un botón personalizado que, al ser presionado, muestra el panel del calendario
 * del torneo actual. Hereda de {@link BotonBase} y configura tanto el estilo visual como
 * la acción específica del botón.
 *
 * <p>Este botón está diseñado para integrarse con la interfaz del sistema de torneos,
 * mostrando el {@link PanelCalendario} cuando es activado.</p>
 *
 * @author TuNombre
 */
public class BotonCalendario extends BotonBase {

    /**
     * Crea una instancia del botón de calendario asociada al marco principal.
     *
     * @param frame El {@link JFrame} principal en el cual se desplegará el nuevo panel.
     */
    public BotonCalendario(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón, incluyendo:
     * texto, fuente, colores, borde, tamaño y cursor.
     * Esta personalización otorga coherencia estética con el resto de la interfaz.
     */
    @Override
    public void configurar() {
        setText("mostrar Calendario");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 15));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(1000, 150, 170, 150);
    }

    /**
     * Define la acción que se ejecuta al presionar el botón.
     * Apila el {@link PanelObservador} en el historial y cambia el panel actual
     * al {@link PanelCalendario}, mostrando así el calendario del torneo en pantalla.
     * También imprime un mensaje en consola como confirmación visual.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelObservador(frame));
        cambiarPanel(new PanelCalendario(frame));
        System.out.println("¡Botón Calendario presionado!");
    }
}