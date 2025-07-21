package org.Interfaz;


import org.Logica.*;
import javax.swing.*;
import java.awt.*;


/**
 * Panel principal de la aplicación que actúa como menú inicial.
 * Contiene botones para crear torneos, inscribir participantes,
 * observar torneos y comenzar un torneo.
 */
public class PanelPrincipal extends PanelBase {
    private JButton botonIniciarTorneo;
    private JButton botonInscribirEnTorneo;
    private JButton botonCrearTorneo;
    private JButton botonSpectearTorneo;
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
        botonCrearTorneo = new JButton("Crear Torneo");
        botonCrearTorneo.setBackground(new Color(40, 40, 40));
        botonCrearTorneo.setForeground(Color.WHITE);
        botonCrearTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonCrearTorneo.setFocusPainted(false);
        botonCrearTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonCrearTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonCrearTorneo.setBounds(600, 170, 200, 40);
        panel.add(botonCrearTorneo);

        botonCrearTorneo.addActionListener(e -> {
            if(Navegador.torneo != null){
                int respuesta = JOptionPane.showConfirmDialog(
                        null,
                        "Ya hay un torneo creado.\nSi creas otro, el otro se borrará.\n¿Deseas continuar?",
                        "Confirmar creación de torneo",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (respuesta == JOptionPane.YES_OPTION) {
                    PanelDisciplina panelDisciplina = new PanelDisciplina(frame);
                    Navegador.historial.push(new PanelPrincipal(frame));  // Guarda el estado actual
                    frame.setContentPane(panelDisciplina.obtenerPanel());
                    frame.revalidate();
                    frame.repaint();
                    System.out.println("Creando nuevo torneo...");
                } else {
                    System.out.println("Operación cancelada.");
                }
            } else {
                PanelDisciplina panelDisciplina = new PanelDisciplina(frame);
                Navegador.historial.push(new PanelPrincipal(frame));  // Guarda el estado actual
                frame.setContentPane(panelDisciplina.obtenerPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        // Botón Inscripción Torneo
        botonInscribirEnTorneo = new JButton("Inscripcion Torneo");
        botonInscribirEnTorneo.setBackground(new Color(40, 40, 40));
        botonInscribirEnTorneo.setForeground(Color.WHITE);
        botonInscribirEnTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonInscribirEnTorneo.setFocusPainted(false);
        botonInscribirEnTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonInscribirEnTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonInscribirEnTorneo.setBounds(1115, 170, 200, 40);
        panel.add(botonInscribirEnTorneo);



        botonInscribirEnTorneo.addActionListener(e -> {
            if (Navegador.torneo == null) {
                JOptionPane.showMessageDialog(frame, "No hay un torneo válido para inscribir, crea uno.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (((TorneoAbstracto) Navegador.torneo).haIniciado()){
                JOptionPane.showMessageDialog(frame, "No puedes administrar los participantes si el torneo ha iniciado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                PanelInscripcion panelInscripcion = new PanelInscripcion(frame);
                panelInscripcion.setImagenFondo("/fondoprincipal.jpg");
                Navegador.historial.push(new PanelPrincipal(frame)); // Guardar estado actual
                frame.setContentPane(panelInscripcion.obtenerPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        // Botón Observar Torneo
        botonSpectearTorneo = new JButton("Observar Torneo");
        botonSpectearTorneo.setBackground(new Color(40, 40, 40));
        botonSpectearTorneo.setForeground(Color.WHITE);
        botonSpectearTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonSpectearTorneo.setFocusPainted(false);
        botonSpectearTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonSpectearTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonSpectearTorneo.setBounds(80, 170, 200, 40);
        panel.add(botonSpectearTorneo);

        botonSpectearTorneo.addActionListener(e -> {
            if (Navegador.torneo == null) {
                JOptionPane.showMessageDialog(frame, "No hay un torneo válido para visualizar, crea uno.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!((TorneoAbstracto) Navegador.torneo).haIniciado()){
                JOptionPane.showMessageDialog(frame, "No puedes visualizar un torneo que no ha iniciado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                PanelObservador panelObservador = new PanelObservador(frame);
                panelObservador.setImagenFondo("/fondoprincipal.jpg");
                Navegador.historial.push(new PanelPrincipal(frame)); // Guardar estado actual
                frame.setContentPane(panelObservador.obtenerPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        // Botón Iniciar Torneo
        botonIniciarTorneo = new JButton("Iniciar Torneo");
        botonIniciarTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonIniciarTorneo.setFocusPainted(false);
        botonIniciarTorneo.setBackground(new Color(40, 40, 40));
        botonIniciarTorneo.setForeground(Color.WHITE);
        botonIniciarTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonIniciarTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIniciarTorneo.setBounds(600, 300, 200, 40);
        panel.add(botonIniciarTorneo);

        botonIniciarTorneo.addActionListener(e -> {
            if (Navegador.torneo == null) {
                JOptionPane.showMessageDialog(frame, "No hay un torneo válido para iniciar, crea uno.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (((TorneoAbstracto) Navegador.torneo).haIniciado()){
                JOptionPane.showMessageDialog(frame, "El torneo ya está iniciado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {
                            Navegador.torneo.iniciarTorneo();
                        }catch (TorneoException ex){
                            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        System.out.println("Torneo iniciado correctamente.");

                    }
                };
                worker.execute();
            }
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