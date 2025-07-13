package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class BotonTabla extends BotonBase {

    public BotonTabla(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setText("mostrar Tabla");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(700, 410, 170, 150);
    }

    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelObservador(frame));
        cambiarPanel(new PanelTabla(frame));
        System.out.println("¡Botón Tabla presionado!");
    }
}