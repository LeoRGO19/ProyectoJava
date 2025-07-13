package org.Interfaz;

import javax.swing.*;

public class Frame extends JFrame{

    public Frame(){

        setSize(1650, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        PanelPrincipal panel = new PanelPrincipal(this);
        setContentPane(panel.obtenerPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Frame::new);
    }
}
