package org.Interfaz;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class PartidoFutbol extends JFrame {

    private BufferedImage imagen1;

    public PartidoFutbol() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);

        FondoPanel fondo = new FondoPanel();
        setContentPane(fondo);

        setVisible(true);
    }

    class FondoPanel extends JPanel {
        private BufferedImage imagen1;

        public FondoPanel() {
            try {
                URL url1 = getClass().getResource("/campo_futbol.jpg");

                if (url1 != null) imagen1 = ImageIO.read(url1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen1 != null) {
                g.drawImage(imagen1, 0, 0, getWidth() , getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PartidoFutbol());
    }

}
