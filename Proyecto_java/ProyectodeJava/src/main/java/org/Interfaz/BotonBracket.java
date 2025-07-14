package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para mostrar el "Bracket" del torneo.
 * Extiende {@link BotonBase} y define la apariencia y comportamiento al ser presionado.
 */
public class BotonBracket extends BotonBase{

    /**
     * Constructor que recibe el JFrame principal y lo pasa a la superclase.
     *
     * @param frame el JFrame en el que se mostrará el botón.
     */
    public BotonBracket(JFrame frame) {
        super(frame);
    }

    /**
     * Configura el botón con tamaño, posición, texto, colores,
     * fuente, borde y cursor personalizado.
     * Este método se llama al construir el botón para su apariencia.
     */
    @Override
    public void configurar() {
        setBounds(700, 150, 170, 150);
        setText("mostrar Bracket");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción al presionar el botón:
     * - Añade el panel actual al historial para navegación.
     * - Cambia el contenido del JFrame al panel de Bracket.
     * - Imprime un mensaje en consola para seguimiento.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelObservador(frame));
        cambiarPanel(new PanelBracket(frame));
        System.out.println("¡Botón Bracket presionado!");
    }

}
