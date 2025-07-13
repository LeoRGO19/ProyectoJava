package org.Interfaz;

import org.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PanelCalendario extends PanelBase {
    private JPanel calendarioPanel;
    private SwingWorker<Void, List<String>> worker;

    public PanelCalendario(JFrame frame) {
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
        calendarioPanel = new JPanel();
        calendarioPanel.setOpaque(false);
        calendarioPanel.setLayout(new GridLayout(0, 1, 10, 10));
        JScrollPane scrollPane = new JScrollPane(calendarioPanel);
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
                        lineas.add("Enfrentamientos: " + torneo.nombre);
                        lineas.add("-------------------------------------------------------------");
                        lineas.add(String.format("%-30s | %-19s | %-10s", "Enfrentamiento", "Fecha", "Resultado"));
                        lineas.add("-------------------------------------------------------------");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        for (Enfrentamiento enf : torneo.enfrentamientos) {
                            String p2Nombre = (enf.obtenerParticipante2() != null) ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                            String fechaFormateada = (enf.obtenerFecha() != null) ? enf.obtenerFecha().format(formatter) : "Sin fecha";
                            String resultado = enf.haTerminadoEncuentro() ?
                                    enf.obtenerParticipante1().obtenerNombre() + " " + enf.obtenerPuntaje1() + " - " +
                                            enf.obtenerPuntaje2() + " " + p2Nombre : "Pendiente";
                            lineas.add(String.format("%-30s | %-19s | %-10s",
                                    enf.obtenerParticipante1().obtenerNombre() + " vs " + p2Nombre,
                                    fechaFormateada,
                                    resultado));
                        }
                        lineas.add("-------------------------------------------------------------");
                    } else if (Navegador.torneo instanceof TorneoEliminacionSimple) {
                        TorneoEliminacionSimple torneo = (TorneoEliminacionSimple) Navegador.torneo;
                        lineas.add("Enfrentamientos de la Ronda Actual: " + torneo.nombre);
                        lineas.add("-------------------------------------------------------------");
                        lineas.add(String.format("%-30s | %-19s | %-10s", "Enfrentamiento", "Fecha", "Resultado"));
                        lineas.add("-------------------------------------------------------------");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        Enfrentamiento[] rondas = torneo.rondasS;
                        if (rondas != null) {
                            for (Enfrentamiento enf : rondas) {
                                if (enf != null) {
                                    String p2Nombre = (enf.obtenerParticipante2() != null) ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                                    String fechaFormateada = (enf.obtenerFecha() != null) ? enf.obtenerFecha().format(formatter) : "Sin fecha";
                                    String resultado = enf.haTerminadoEncuentro() ?
                                            enf.obtenerParticipante1().obtenerNombre() + " " + enf.obtenerPuntaje1() + " - " +
                                                    enf.obtenerPuntaje2() + " " + p2Nombre : "Pendiente";
                                    lineas.add(String.format("%-30s | %-19s | %-10s",
                                            enf.obtenerParticipante1().obtenerNombre() + " vs " + p2Nombre,
                                            fechaFormateada,
                                            resultado));
                                }
                            }
                        }
                        lineas.add("-------------------------------------------------------------");
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
                    calendarioPanel.removeAll();
                    calendarioPanel.setLayout(new GridLayout(lineas.size(), 1, 10, 10));
                    for (String linea : lineas) {
                        JLabel label = new JLabel(linea);
                        label.setForeground(Color.WHITE);
                        label.setFont(new Font("Monospaced", Font.PLAIN, 14));
                        calendarioPanel.add(label);
                    }
                    calendarioPanel.revalidate();
                    calendarioPanel.repaint();
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