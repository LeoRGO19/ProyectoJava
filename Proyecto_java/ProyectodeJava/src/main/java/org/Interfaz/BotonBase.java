package org.Interfaz;

import javax.swing.*;
import java.awt.*;
public abstract class BotonBase extends JButton implements InterfazBotones {

    protected JFrame frame;

    public BotonBase(JFrame frame) {
        super();
        this.frame = frame;
        configurar();
        addActionListener(e -> alPresionar());
    }
    public void configurar() {
        setSize(new Dimension(100, 50));
        setFocusPainted(false);
    }
    public void setImagen(String ruta){
        int ancho = 200;
        int alto = 200;
        ImageIcon imagen = new ImageIcon(getClass().getResource(ruta));
        Image imagenEscalada = imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagenEscalada);
        setIcon(imagenFinal);
    }

    protected void cambiarPanel(PanelBase nuevoPanel) {
        if (frame == null || nuevoPanel == null) return;

        frame.setContentPane(nuevoPanel.obtenerPanel());
        frame.revalidate();
        frame.repaint();
    }
}
