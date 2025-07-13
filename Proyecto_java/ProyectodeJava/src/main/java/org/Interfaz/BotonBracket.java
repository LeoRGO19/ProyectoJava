package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class BotonBracket extends BotonBase{

    public BotonBracket(JFrame frame) {
        super(frame);
    }

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

    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelObservador(frame));
        cambiarPanel(new PanelBracket(frame));
        System.out.println("¡Botón Bracket presionado!");
    }

}
