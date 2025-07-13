package org.Interfaz;


import javax.swing.*;

public class BotonLoL extends BotonBase{

    public BotonLoL(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1100,150, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/lol_icono.png"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón LoL presionado!");
    }
}
