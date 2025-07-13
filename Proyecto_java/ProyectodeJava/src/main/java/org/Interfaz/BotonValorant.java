package org.Interfaz;

import javax.swing.*;

public class BotonValorant extends BotonBase{

    public BotonValorant(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(650,150, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/valorant_icono.jpg"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Valorant presionado!");
    }
}
