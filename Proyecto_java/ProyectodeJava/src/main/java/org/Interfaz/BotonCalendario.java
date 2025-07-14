package org.Interfaz;

import javax.swing.*;
import java.awt.*;


public class BotonCalendario extends BotonBase {

    public BotonCalendario(JFrame frame) {
        super(frame);
    }

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

    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelObservador(frame));
        cambiarPanel(new PanelCalendario(frame));
        System.out.println("¡Botón Calendario presionado!");
    }
}