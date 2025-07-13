package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;

public class BotonTick extends BotonBase{

    public BotonTick(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(595,320, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/tick.jpg"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() throws TorneoException {

        Navegador.historial.push(new PanelTipoTorneo(frame));
        cambiarPanel(new PanelPrincipal(frame));

        GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();
        Creador pedro = gestor.obtenerCreador(Navegador.t);

        Navegador.torneo = pedro.crearTorneo("TORNEO1",Navegador.palabra,8);
        System.out.println("¡Botón Confirmar presionado!");
    }
}
