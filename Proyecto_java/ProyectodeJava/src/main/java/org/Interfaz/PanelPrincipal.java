package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel principal de la aplicación que actúa como menú inicial.
 * Contiene botones para crear torneos, inscribir participantes,
 * observar torneos y comenzar un torneo.
 */
public class PanelPrincipal extends PanelBase {

    /**
     * Constructor que recibe el JFrame principal y configura el panel.
     *
     * @param frame JFrame principal donde se mostrará el panel.
     */
    public PanelPrincipal(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    /**
     * Configura el fondo del panel principal con una imagen.
     */
    @Override
    public void configurar() {
        setImagenFondo("/fondoprincipal.jpg");
    }

    /**
     * Agrega los componentes gráficos al panel principal, incluyendo:
     * - Etiqueta de bienvenida.
     * - Botón para crear un torneo (navega a selección de disciplina).
     * - Botón para inscribir participantes desde un archivo.
     * - Botón para observar torneos (navega a panel observador).
     * - Botón para iniciar el torneo activo, que se ejecuta en un hilo separado.
     *
     * Cada botón tiene su configuración visual y sus listeners correspondientes.
     */
    @Override
    public void agregarComponentes() {
        JLabel titulo = new JLabel("Bienvenido al Menú Principal");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(500, 50, 400, 30);
        panel.add(titulo);

        // Botón Crear Torneo
        JButton botonCrearTorneo = new JButton("Crear Torneo");
        botonCrearTorneo.setBackground(new Color(40, 40, 40));
        botonCrearTorneo.setForeground(Color.WHITE);
        botonCrearTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonCrearTorneo.setFocusPainted(false);
        botonCrearTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonCrearTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonCrearTorneo.setBounds(600, 170, 200, 40);
        panel.add(botonCrearTorneo);

        botonCrearTorneo.addActionListener(e -> {
            PanelDisciplina panelDisciplina = new PanelDisciplina(frame);
            Navegador.historial.push(new PanelPrincipal(frame));  // Guarda el estado actual
            frame.setContentPane(panelDisciplina.obtenerPanel());
            frame.revalidate();
            frame.repaint();
        });

        // Botón Inscripción Torneo
        JButton botonInscribirEnTorneo = new JButton("Inscripcion Torneo");
        botonInscribirEnTorneo.setBackground(new Color(40, 40, 40));
        botonInscribirEnTorneo.setForeground(Color.WHITE);
        botonInscribirEnTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonInscribirEnTorneo.setFocusPainted(false);
        botonInscribirEnTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonInscribirEnTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonInscribirEnTorneo.setBounds(1115, 170, 200, 40);
        panel.add(botonInscribirEnTorneo);

        botonInscribirEnTorneo.addActionListener(e -> {
            try {
                List<List<Participante>> resultado = LectorParticipantes.leerParticipantes("C:/Users/Canito301/Downloads/participantes2.txt");
                ArrayList<Participante> individuos = new ArrayList<>(resultado.get(0));
                ((TorneoAbstracto) Navegador.torneo).agregarParticipantesDesdeLista(individuos);

                for (Participante d : ((TorneoAbstracto) Navegador.torneo).obtenerParticipantes()) {
                    System.out.println(d);
                }
            } catch (TorneoException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Botón Observar Torneo
        JButton botonSpectearTorneo = new JButton("Observar Torneo");
        botonSpectearTorneo.setBackground(new Color(40, 40, 40));
        botonSpectearTorneo.setForeground(Color.WHITE);
        botonSpectearTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonSpectearTorneo.setFocusPainted(false);
        botonSpectearTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonSpectearTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonSpectearTorneo.setBounds(80, 170, 200, 40);
        panel.add(botonSpectearTorneo);

        botonSpectearTorneo.addActionListener(e -> {
            PanelObservador panelObservador = new PanelObservador(frame);
            panelObservador.setImagenFondo("/fondoprincipal.jpg");
            Navegador.historial.push(new PanelPrincipal(frame)); // Guardar estado actual
            frame.setContentPane(panelObservador.obtenerPanel());
            frame.revalidate();
            frame.repaint();
        });

        // Botón Iniciar Torneo
        JButton botonIniciarTorneo = new JButton("Iniciar Torneo");
        botonIniciarTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonIniciarTorneo.setFocusPainted(false);
        botonIniciarTorneo.setBackground(new Color(40, 40, 40));
        botonIniciarTorneo.setForeground(Color.WHITE);
        botonIniciarTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonIniciarTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIniciarTorneo.setBounds(600, 300, 200, 40);
        panel.add(botonIniciarTorneo);

        botonIniciarTorneo.addActionListener(e -> {
            botonIniciarTorneo.setEnabled(false);  // Deshabilitar botón para evitar múltiples clicks
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Navegador.torneo.iniciarTorneo();
                    return null;
                }

                @Override
                protected void done() {
                    botonIniciarTorneo.setEnabled(true);  // Rehabilitar botón
                    System.out.println("Torneo iniciado correctamente.");
                }
            };
            worker.execute();
        });
    }

    /**
     * Obtiene el JPanel principal que contiene todos los componentes del panel.
     *
     * @return JPanel del panel principal.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}