package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonEliminacionSimple extends BotonBase{

    public BotonEliminacionSimple(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1115,320,200,200);
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);

        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setText("ELIMINACION SIMPLE");
        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelTipoTorneo(frame));
        cambiarPanel(new PanelConfirmacion(frame));
        System.out.println("¡Botón Eliminacion simple presionado!");
    }
}