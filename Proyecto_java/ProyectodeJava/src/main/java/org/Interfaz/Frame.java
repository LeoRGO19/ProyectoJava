package org.Interfaz;

import javax.swing.*;

public class Frame extends JFrame{

    public Frame(){

        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        PanelDeportes panel = new PanelDeportes();
        setContentPane(panel.obtenerPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Frame::new);
    }
}
