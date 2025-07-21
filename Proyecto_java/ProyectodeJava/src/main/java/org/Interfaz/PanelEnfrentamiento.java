package org.Interfaz;

import org.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * {@code PanelEnfrentamiento} representa una vista interactiva donde
 * se muestra el enfrentamiento actual del torneo.
 * <p>
 * Permite visualizar los participantes, puntajes y estado del encuentro,
 * con la posibilidad de navegar al siguiente enfrentamiento con un botón.
 * El fondo varía según la disciplina del torneo (fútbol, tenis, etc.),
 * proporcionando una experiencia visual más inmersiva.
 * </p>
 *
 * <p>
 * Esta clase forma parte del sistema de visualización de torneos,
 * y extiende {@link PanelBase}.
 * </p>
 *
 * @author Tú :)
 */
public class PanelEnfrentamiento extends PanelBase {
    private JLabel texto;               // Muestra los nombres de los participantes
    private JLabel texto2;              // Muestra el estado y puntaje del enfrentamiento
    private JButton botonSiguiente;     // Permite ver el siguiente enfrentamiento
    private int enfrentamientoActual;   // Índice del enfrentamiento actual mostrado
    private Enfrentamiento[] enfrentamientos; // Arreglo de enfrentamientos del torneo
    private SwingWorker<Void, String> worker; // Actualiza la interfaz periódicamente

    /**
     * Constructor principal del panel.
     *
     * @param frame Ventana principal que contiene este panel.
     */
    public PanelEnfrentamiento(JFrame frame) {
        super(frame);
        enfrentamientoActual = 0;
        enfrentamientos = getEnfrentamientos();
        configurar();
        agregarComponentes();
        iniciarActualizaciones();
    }

    /**
     * Configura el diseño y la imagen de fondo del panel
     * según la disciplina del torneo actual.
     */
    @Override
    public void configurar() {
        panel.setBackground(new Color(70, 45, 90));
        panel.setLayout(null);

        switch (Navegador.palabra) {
            case "FUTBOL" -> setImagenFondo("/campo_futbol.jpg");
            case "BASKETBALL" -> setImagenFondo("/cancha_basket.jpg");
            case "TENIS" -> setImagenFondo("/cancha_tenis.jpg");
            case "TENISDEMESA" -> setImagenFondo("/cancha_ping_pong.jpg");
            case "TIROCONARCO" -> setImagenFondo("/campotiro.jpg");
            case "VALORANT" -> setImagenFondo("/valorantmapa.jpg");
            case "LOL" -> setImagenFondo("/leaguemapa.jpg");
            case "CSGO" -> setImagenFondo("/csgo.jpg");
            case "ROCKETLEAGUE" -> setImagenFondo("/rocketleague.png");
            case "FIFA" -> setImagenFondo("/fifa_estadio.jpg");
        }
    }

    /**
     * Agrega los componentes visuales como etiquetas de texto
     * y botones interactivos al panel principal.
     */
    @Override
    public void agregarComponentes() {
        texto = new JLabel("Esperando enfrentamiento...");
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        texto.setBounds(700, 20, 350, 150);

        texto2 = new JLabel("");
        texto2.setForeground(Color.WHITE);
        texto2.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        texto2.setBounds(600, 50, 500, 300);

        // Panel inferior con botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(70, 45, 90));

        BotonVolver botonVolver = new BotonVolver(frame);
        botonVolver.setBounds(600, 600, 150, 150);
        botonVolver.setBackground(Color.white);
        buttonPanel.add(botonVolver);

        botonSiguiente = new JButton("Siguiente");
        botonSiguiente.setBackground(new Color(40, 40, 40));
        botonSiguiente.setForeground(Color.WHITE);
        botonSiguiente.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botonSiguiente.setFocusPainted(false);
        botonSiguiente.setBounds(800, 600, 150, 150);
        botonSiguiente.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonSiguiente.addActionListener(e -> mostrarSiguienteEnfrentamiento());
        buttonPanel.add(botonSiguiente);

        // Añadir componentes al panel
        panel.setComponentZOrder(botonSiguiente, 0);
        panel.setComponentZOrder(texto, 0);
        panel.setComponentZOrder(texto2, 0);
        panel.setComponentZOrder(botonVolver, 0);

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Obtiene los enfrentamientos según el tipo de torneo.
     *
     * @return Arreglo de enfrentamientos.
     */
    private Enfrentamiento[] getEnfrentamientos() {
        if (Navegador.torneo instanceof TorneoEliminacionSimple elim) {
            return elim.obtenerRondasS();
        } else if (Navegador.torneo instanceof TorneoLiga liga) {
            return liga.obtenerEnfrentamientos().toArray(new Enfrentamiento[0]);
        }
        return new Enfrentamiento[0];
    }

    /**
     * Muestra el siguiente enfrentamiento disponible en la lista.
     * Si no hay enfrentamientos, se desactiva el botón.
     */
    private void mostrarSiguienteEnfrentamiento() {
        if (enfrentamientos == null || enfrentamientos.length == 0) {
            texto.setText("No hay enfrentamientos disponibles");
            texto2.setText("");
            botonSiguiente.setEnabled(false);
            return;
        }

        enfrentamientoActual = (enfrentamientoActual + 1) % enfrentamientos.length;
        actualizarEnfrentamiento();
    }

    /**
     * Actualiza los textos en pantalla con la información
     * del enfrentamiento actualmente seleccionado.
     */
    private void actualizarEnfrentamiento() {
        if (enfrentamientos != null && enfrentamientoActual < enfrentamientos.length
                && enfrentamientos[enfrentamientoActual] != null) {

            Enfrentamiento enf = enfrentamientos[enfrentamientoActual];
            String p2Nombre = (enf.obtenerParticipante2() != null) ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
            String estado = switch (enf.obtenerEstado()) {
                case 0 -> "Pendiente";
                case 1 -> "En curso";
                case 2 -> "Terminado";
                default -> "Desconocido";
            };

            texto.setText(enf.obtenerParticipante1().obtenerNombre() + " vs " + p2Nombre);
            texto2.setText("Puntaje: " + enf.obtenerPuntaje1() + " - " + enf.obtenerPuntaje2() + " (" + estado + ")");
        } else {
            texto.setText("No hay enfrentamiento disponible");
            texto2.setText("");
        }
    }

    /**
     * Inicia un {@link SwingWorker} para actualizar automáticamente
     * la información del enfrentamiento actual cada segundo.
     */
    private void iniciarActualizaciones() {
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                while (!isCancelled()) {
                    if (enfrentamientos != null && enfrentamientoActual < enfrentamientos.length
                            && enfrentamientos[enfrentamientoActual] != null) {

                        Enfrentamiento enf = enfrentamientos[enfrentamientoActual];
                        String estado = switch (enf.obtenerEstado()) {
                            case 0 -> "Pendiente";
                            case 1 -> "En curso";
                            case 2 -> "Terminado";
                            default -> "Desconocido";
                        };

                        String textoActual = enf.obtenerParticipante1().obtenerNombre() + " vs " +
                                (enf.obtenerParticipante2() != null ? enf.obtenerParticipante2().obtenerNombre() : "BYE");
                        String textoPuntaje = "Puntaje: " + enf.obtenerPuntaje1() + " - " + enf.obtenerPuntaje2() + " (" + estado + ")";
                        publish(textoActual + "|" + textoPuntaje);
                    }

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
             * Actualiza los componentes visuales con los datos publicados.
             *
             * @param chunks Lista de strings con el formato "participantes|puntaje".
             */
            @Override
            protected void process(List<String> chunks) {
                for (String chunk : chunks) {
                    String[] partes = chunk.split("\\|");
                    if (partes.length == 2) {
                        texto.setText(partes[0]);
                        texto2.setText(partes[1]);
                    }
                }
            }
        };

        worker.execute();
    }

    /**
     * Retorna el panel principal para ser mostrado.
     *
     * @return el {@link JPanel} correspondiente al panel.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

    /**
     * Detiene la ejecución del {@link SwingWorker} que actualiza
     * los enfrentamientos automáticamente.
     * <p>Se debe llamar al salir del panel para liberar recursos.</p>
     */
    public void cleanup() {
        if (worker != null) {
            worker.cancel(true);
        }
    }
}