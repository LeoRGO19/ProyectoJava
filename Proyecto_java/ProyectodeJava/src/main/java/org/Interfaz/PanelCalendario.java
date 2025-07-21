package org.Interfaz;

import org.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * {@code PanelCalendario} representa un panel visual que despliega
 * en tiempo real el calendario de enfrentamientos del torneo.
 * <p>
 * Soporta tanto torneos de tipo Liga como Eliminación Simple,
 * y actualiza dinámicamente los datos mostrados cada segundo,
 * ofreciendo al usuario una vista clara del progreso y programación
 * de los encuentros.
 * </p>
 *
 * <p>
 * Este panel forma parte de la interfaz gráfica del sistema y extiende {@link PanelBase}.
 * </p>
 *
 * @author Tú :)
 */
public class PanelCalendario extends PanelBase {
    private JPanel calendarioPanel;
    private SwingWorker<Void, List<String>> worker;

    /**
     * Crea un nuevo panel de calendario para mostrar los enfrentamientos.
     *
     * @param frame El marco principal donde se renderizará este panel.
     */
    public PanelCalendario(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
        iniciarActualizaciones();
    }

    /**
     * Configura las propiedades visuales del panel:
     * - Establece un layout {@link BorderLayout}.
     * - Aplica un fondo verde con imagen personalizada.
     */
    @Override
    public void configurar() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(70, 155, 90));
        setImagenFondo("/fondo.jpg");
    }

    /**
     * Agrega los componentes gráficos al panel:
     * - Un {@link JScrollPane} que contiene las líneas de calendario.
     * - Un botón de regreso en la parte inferior del panel.
     */
    @Override
    public void agregarComponentes() {
        calendarioPanel = new JPanel();
        calendarioPanel.setOpaque(false);
        calendarioPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JScrollPane scrollPane = new JScrollPane(calendarioPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 400, 0, 0));
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(70, 45, 90));

        BotonVolver botonVolver = new BotonVolver(frame);
        buttonPanel.add(botonVolver);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Inicia un {@link SwingWorker} que se encarga de actualizar
     * el contenido del calendario automáticamente cada segundo.
     * <p>
     * La lógica de actualización distingue entre torneos de tipo
     * {@link TorneoLiga} y {@link TorneoEliminacionSimple}.
     * </p>
     */
    private void iniciarActualizaciones() {
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                while (!isCancelled()) {
                    List<String> lineas = new ArrayList<>();

                    if (Navegador.torneo instanceof TorneoLiga torneo) {
                        lineas.add("Enfrentamientos: " + torneo.obtenerNombre());
                        lineas.add("-------------------------------------------------------------");
                        lineas.add(String.format("%-30s | %-19s | %-10s", "Enfrentamiento", "Fecha", "Resultado"));
                        lineas.add("-------------------------------------------------------------");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        for (Enfrentamiento enf : torneo.obtenerEnfrentamientos()) {
                            String p2 = (enf.obtenerParticipante2() != null) ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                            String fecha = (enf.obtenerFecha() != null) ? enf.obtenerFecha().format(formatter) : "Sin fecha";
                            String resultado = enf.haTerminadoEncuentro()
                                    ? enf.obtenerParticipante1().obtenerNombre() + " " + enf.obtenerPuntaje1() + " - "
                                    + enf.obtenerPuntaje2() + " " + p2
                                    : "Pendiente";

                            lineas.add(String.format("%-30s | %-19s | %-10s",
                                    enf.obtenerParticipante1().obtenerNombre() + " vs " + p2, fecha, resultado));
                        }

                        lineas.add("-------------------------------------------------------------");

                    } else if (Navegador.torneo instanceof TorneoEliminacionSimple torneo) {
                        lineas.add("Enfrentamientos de la Ronda Actual: " + torneo.obtenerNombre());
                        lineas.add("-------------------------------------------------------------");
                        lineas.add(String.format("%-30s | %-19s | %-10s", "Enfrentamiento", "Fecha", "Resultado"));
                        lineas.add("-------------------------------------------------------------");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        for (Enfrentamiento enf : torneo.obtenerRondasS()) {
                            if (enf != null) {
                                String p2 = (enf.obtenerParticipante2() != null) ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                                String fecha = (enf.obtenerFecha() != null) ? enf.obtenerFecha().format(formatter) : "Sin fecha";
                                String resultado = enf.haTerminadoEncuentro()
                                        ? enf.obtenerParticipante1().obtenerNombre() + " " + enf.obtenerPuntaje1() + " - "
                                        + enf.obtenerPuntaje2() + " " + p2
                                        : "Pendiente";

                                lineas.add(String.format("%-30s | %-19s | %-10s",
                                        enf.obtenerParticipante1().obtenerNombre() + " vs " + p2, fecha, resultado));
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

            /**
             * Procesa las líneas generadas y actualiza el contenido visual del calendario.
             *
             * @param chunks listas de líneas que representan los datos del calendario.
             */
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

    /**
     * Devuelve el panel completo que contiene los componentes del calendario.
     *
     * @return el {@link JPanel} principal.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

    /**
     * Cancela el worker en ejecución para detener la actualización del calendario.
     * Este método debe llamarse al cerrar o cambiar de panel.
     */
    public void cleanup() {
        if (worker != null) {
            worker.cancel(true);
        }
    }
}