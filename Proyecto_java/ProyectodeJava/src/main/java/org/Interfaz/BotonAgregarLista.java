package org.Interfaz;
import org.Logica.*;
import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para el panel {@link PanelInscripcion} que permite agregar participantes desde una lista al torneo.
 * Extiende {@link BotonBase} y personaliza su apariencia y comportamiento al ser presionado.
 */
public class BotonAgregarLista extends BotonBase{
    /**
     * Constructor que recibe el JFrame principal y lo pasa a la superclase.
     *
     * @param frame el JFrame en el que se mostrará el botón.
     */
    public BotonAgregarLista(JFrame frame) {
        super(frame);
    }
    /**
     * Configura las propiedades visuales del botón, incluyendo tamaño, texto, colores, fuente, borde y cursor.
     */
    @Override
    public void configurar() {
        setBounds(600, 150, 330, 150);
        setText("Agregar lista de participantes");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * Acción que ocurre al presionar el botón:
     * - Agrega el panel actual al historial de navegación.
     * - Cambia la vista al panel de Agregar Lista.
     * - Imprime un mensaje en consola para seguimiento.
     * @throws TorneoException si ocurre un error relacionado con la lógica del torneo.
     */
    @Override
    public void alPresionar() throws TorneoException {
        Navegador.historial.push(new PanelInscripcion(frame));
        cambiarPanel(new PanelAgregarLista(frame));
        System.out.println("¡Botón AgregarLista presionado!");
    }
}
