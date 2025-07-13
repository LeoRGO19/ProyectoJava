package org.Interfaz;

import org.Logica.TorneoAbstracto;

import javax.swing.*;
import java.awt.*;

public class BotonEnfrentamiento extends BotonBase {

    public BotonEnfrentamiento(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setText("ver Enfrentamiento");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 15));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(1000, 410, 170, 150);
    }

    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelObservador(frame));
        cambiarPanel(new PanelEnfrentamiento(frame));
        System.out.println("¡Botón Enfrentamiento presionado!");
    }
}