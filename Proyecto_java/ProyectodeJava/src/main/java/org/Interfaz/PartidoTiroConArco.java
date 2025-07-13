package org.Interfaz;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class PartidoTiroConArco extends JFrame {

    private BufferedImage imagen1;
    private BufferedImage imagen2;

    public PartidoTiroConArco() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);

        FondoPanel fondo = new FondoPanel();
        setContentPane(fondo);

        setVisible(true);
    }

    class FondoPanel extends JPanel {
        private BufferedImage imagen1;
        private BufferedImage imagen2;

        public FondoPanel() {
            try {
                URL url1 = getClass().getResource("/tiroconarco_1.jpg");
                URL url2 = getClass().getResource("/tiroconarco_2.jpg");

                if (url1 != null) imagen1 = ImageIO.read(url1);
                if (url2 != null) imagen2 = ImageIO.read(url2);
            } catch (IOException e) {
                e.printStackTrace();
            }

            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen1 != null) {
                g.drawImage(imagen1, 0, 0, getWidth() / 2, getHeight(), this);
            }

            if (imagen2 != null) {
                g.drawImage(imagen2, getWidth() / 2, 0, getWidth() / 2, getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PartidoTiroConArco());
    }

}
