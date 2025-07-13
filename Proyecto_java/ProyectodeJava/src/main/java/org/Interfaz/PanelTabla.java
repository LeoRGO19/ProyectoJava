package org.Interfaz;

import org.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PanelTabla extends PanelBase {
    private JPanel tablaPanel;
    private SwingWorker<Void, List<String>> worker;

    public PanelTabla(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
        iniciarActualizaciones();
    }

    @Override
    public void configurar() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(70, 45, 90));
        setImagenFondo("/fondoprincipal.jpg");
    }

    @Override
    public void agregarComponentes() {
        tablaPanel = new JPanel();
        tablaPanel.setOpaque(false);
        tablaPanel.setLayout(new GridLayout(0, 1, 10, 10));
        JScrollPane scrollPane = new JScrollPane(tablaPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(70, 45, 90));
        BotonVolver botonVolver = new BotonVolver(frame);
        buttonPanel.add(botonVolver);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void iniciarActualizaciones() {
        worker = new SwingWorker<Void, List<String>>() {
            @Override
            protected Void doInBackground() {
                while (!isCancelled()) {
                    List<String> lineas = new ArrayList<>();
                    if (Navegador.torneo instanceof TorneoLiga) {
                        TorneoLiga torneo = (TorneoLiga) Navegador.torneo;
                        lineas.add("Tabla de Clasificación: " + torneo.nombre);
                        lineas.add("---------------------------------------------------------------------------------");
                        lineas.add(String.format("%-20s | %-6s | %-8s | %-8s | %-10s | %-10s",
                                "Participante", "Puntos", "Victorias", "Derrotas", "P. Favor", "P. Contra"));
                        lineas.add("---------------------------------------------------------------------------------");

                        ArrayList<Integer> indices = new ArrayList<>();
                        for (int i = 0; i < torneo.participantes.size(); i++) {
                            indices.add(i);
                        }
                        indices.sort((i1, i2) -> {
                            int cmp = torneo.puntos.get(i2).compareTo(torneo.puntos.get(i1));
                            if (cmp != 0) return cmp;
                            cmp = torneo.victorias.get(i2).compareTo(torneo.victorias.get(i1));
                            if (cmp != 0) return cmp;
                            cmp = torneo.puntosAFavor.get(i2).compareTo(torneo.puntosAFavor.get(i1));
                            if (cmp != 0) return cmp;
                            return torneo.puntosEnContra.get(i1).compareTo(torneo.puntosEnContra.get(i2));
                        });

                        for (int i : indices) {
                            lineas.add(String.format("%-20s | %-6d | %-8d | %-8d | %-10d | %-10d",
                                    torneo.participantes.get(i).obtenerNombre(),
                                    torneo.puntos.get(i),
                                    torneo.victorias.get(i),
                                    torneo.derrotas.get(i),
                                    torneo.puntosAFavor.get(i),
                                    torneo.puntosEnContra.get(i)));
                        }
                        lineas.add("---------------------------------------------------------------------------------");
                    } else if (Navegador.torneo instanceof TorneoEliminacionSimple) {
                        lineas.add("Tabla de Clasificación no disponible para Torneo de Eliminación Simple");
                        lineas.add("---------------------------------------------------------------------------------");
                        lineas.add("Ronda Actual: " + (((TorneoEliminacionSimple) Navegador.torneo).obtenerRondaActual() + 1));
                        lineas.add("---------------------------------------------------------------------------------");
                    }
                    publish(new CopyOnWriteArrayList<>(lineas));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                return null;
            }

            @Override
            protected void process(List<List<String>> chunks) {
                for (List<String> lineas : chunks) {
                    tablaPanel.removeAll();
                    tablaPanel.setLayout(new GridLayout(lineas.size(), 1, 10, 10));
                    for (String linea : lineas) {
                        JLabel label = new JLabel(linea);
                        label.setForeground(Color.WHITE);
                        label.setFont(new Font("Monospaced", Font.PLAIN, 14));
                        tablaPanel.add(label);
                    }
                    tablaPanel.revalidate();
                    tablaPanel.repaint();
                }
            }
        };
        worker.execute();
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

    public void cleanup() {
        if (worker != null) {
            worker.cancel(true);
        }
    }
}