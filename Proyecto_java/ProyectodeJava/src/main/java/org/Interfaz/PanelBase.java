package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelBase implements InterfazPaneles{

    protected JPanel panel;
    protected JFrame frame;
    private Image imagenFondo;

    public PanelBase(JFrame frame) {
        this.frame = frame;

        // Creamos un panel con paintComponent para fondo
        this.panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }



    public void setImagenFondo(String ruta) {

        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        imagenFondo = icon.getImage();
        panel.repaint(); // Forzar repintado

    }


    @Override
    public void configurar() {

    }

    @Override
    public void agregarComponentes() {

    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
    public int getTipo() {
        return 0;
    }
}
