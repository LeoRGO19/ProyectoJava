package org.Interfaz;

import org.Logica.TorneoException;

import javax.swing.*;
import java.awt.*;

public class BotonQuitarParticipante extends BotonBase{
    /**
     * Constructor que inicializa el botón y lo configura.
     * También registra el listener para la acción del botón que llama a {@link #alPresionar()}.
     *
     * @param frame el JFrame principal que contiene el botón
     */
    public BotonQuitarParticipante(JFrame frame) {
        super(frame);
    }
    @Override
    public void configurar() {
        setText("Quitar Participante");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 15));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(1000, 410, 170, 150);
    }

    @Override
    public void alPresionar() throws TorneoException {
        Navegador.historial.push(new PanelInscripcion(frame));
        cambiarPanel(new PanelEliminarParticipante(frame));
        System.out.println("¡Botón QuitarParticipante presionado!");
    }
}
