package org.Interfaz;

import javax.swing.*;

public class BotonVolver extends BotonBase {

    private final PanelBase destino;

    public BotonVolver(JFrame frame, PanelBase destino) {
        super(frame);
        this.destino = destino;
    }

    @Override
    public void configurar() {
        setBounds(200, 150, 200, 200); // Posición y tamaño
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/flecha.png"));
    }

    protected void cambiarPanel(PanelBase nuevoPanel) {
        if (frame == null || nuevoPanel == null) return;

        frame.setContentPane(nuevoPanel.obtenerPanel());
        frame.revalidate();
        frame.repaint();
    }


    @Override
    public void alPresionar() {
        cambiarPanel(destino);
        System.out.println("Boton Volver presionado");
    }
}