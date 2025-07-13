package org.Interfaz;


import org.Logica.*;

import javax.swing.*;
import java.awt.*;

public class BotonLiga extends BotonBase{



    public BotonLiga(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(595,320,200,200);
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setText("LIGA");
        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelTipoTorneo(frame));
        cambiarPanel(new PanelConfirmacion(frame));

        Navegador.t = FormatoTorneo.LIGA;
        System.out.println(Navegador.t);
        System.out.println("¡Botón Liga presionado!");
    }
}
