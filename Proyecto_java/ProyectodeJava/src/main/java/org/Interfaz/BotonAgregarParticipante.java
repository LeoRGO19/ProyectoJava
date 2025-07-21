package org.Interfaz;

import org.Logica.TorneoException;

import javax.swing.*;
import java.awt.*;
/**
 * Botón específico para el panel {@link PanelInscripcion} que permite agregar participantes manualmente al torneo.
 * Extiende {@link BotonBase} y define la configuración visual y el comportamiento al ser presionado.
 */
public class BotonAgregarParticipante extends BotonBase{
    /**
     * Constructor que inicializa el botón y lo configura.
     * También registra el listener para la acción del botón que llama a {@link #alPresionar()}.
     *
     * @param frame el JFrame principal que contiene el botón
     */
    public BotonAgregarParticipante(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón, incluyendo texto, colores, fuente, borde, cursor y tamaño.
     */
    @Override
    public void configurar() {
        setText("Agregar Participante");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 15));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(1000, 150, 170, 150);
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Agrega el panel actual al historial de navegación.
     * - Cambia la vista al panel para llenar el formulario del participante.
     * - Imprime un mensaje en consola para seguimiento.
     * @throws TorneoException si ocurre un error relacionado con la lógica del torneo.
     */
    @Override
    public void alPresionar() throws TorneoException {
        Navegador.historial.push(new PanelInscripcion(frame));
        cambiarPanel(new PanelFormularioParticipante(frame));
        System.out.println("¡Botón AgregarParticipante presionado!");
    }
}
