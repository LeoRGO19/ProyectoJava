package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class BotonVolver extends BotonBase {


    public BotonVolver(JFrame frame) {
        super(frame);

    }

    @Override
    public void configurar() {
        setBounds(200, 150, 200, 200); // Posición y tamaño
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/flecha.png"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    protected void cambiarPanel(PanelBase nuevoPanel) {
        if (frame == null || nuevoPanel == null) return;

        frame.setContentPane(nuevoPanel.obtenerPanel());
        frame.revalidate();
        frame.repaint();
    }


    @Override
    public void alPresionar() {
        if (!Navegador.historial.isEmpty()) {
            PanelBase anterior = Navegador.historial.pop();
            cambiarPanel(anterior);
            System.out.println("Boton Volver presionado");
        }
    }
}