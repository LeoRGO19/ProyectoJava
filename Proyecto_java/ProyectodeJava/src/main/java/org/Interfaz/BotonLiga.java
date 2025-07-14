package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;

/**
 * Botón que representa la opción de seleccionar el formato de torneo tipo "Liga".
 * Extiende {@link BotonBase} y define su estilo visual y la lógica que se ejecuta al presionarlo.
 */
public class BotonLiga extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonLiga(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia del botón:
     * - Posición, tamaño y estilo.
     * - Texto, colores, fuente, borde y cursor.
     * Este método es llamado automáticamente en la construcción.
     */
    @Override
    public void configurar() {
        setBounds(595, 320, 200, 200);
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setText("LIGA");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Agrega el panel actual (PanelTipoTorneo) al historial de navegación.
     * - Cambia la vista al panel de confirmación.
     * - Asigna el formato de torneo {@code LIGA} a {@link Navegador#t}.
     * - Imprime mensajes en consola para seguimiento.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelTipoTorneo(frame));
        cambiarPanel(new PanelConfirmacion(frame));
        Navegador.t = FormatoTorneo.LIGA;
        System.out.println(Navegador.t);
        System.out.println("¡Botón Liga presionado!");
    }
}
