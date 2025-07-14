package org.Interfaz;


import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para la disciplina Basket.
 * Extiende {@link BotonBase} y define la configuración visual y el comportamiento al ser presionado.
 */

public class BotonBasket extends BotonBase{

    /**
     * Constructor que recibe el JFrame padre y lo pasa a la superclase.
     *
     * @param frame JFrame principal donde se mostrará el botón.
     */
    public BotonBasket(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón:
     * tamaño, posición, colores, imagen y cursor.
     * Este método se llama durante la construcción.
     */
    @Override
    public void configurar() {
        setBounds(1100,150, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/basket_pelota.jpg"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Agrega el panel actual al historial de navegación.
     * - Cambia la vista al panel de selección de tipo de torneo.
     * - Establece la palabra clave "BASKETBALL" en Navegador.
     * - Imprime un mensaje en consola para seguimiento.
     */
    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelDeportes(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "BASKETBALL";
        System.out.println("¡Botón Basket presionado!");
    }
}
