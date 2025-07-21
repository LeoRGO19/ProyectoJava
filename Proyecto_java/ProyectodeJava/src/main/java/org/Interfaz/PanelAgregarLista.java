package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelAgregarLista extends PanelBase {
    /**
     * Constructor que inicializa el panel base con el JFrame dado.
     * Crea un JPanel con layout nulo y sobreescribe paintComponent para pintar la imagen de fondo.
     *
     * @param frame el JFrame contenedor donde se mostrará este panel.
     */
    public PanelAgregarLista(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(70, 45, 90));
    }

    @Override
    public void agregarComponentes() {
        // Etiqueta para el número de participantes
        JLabel etiquetaCantidad = new JLabel("Cantidad de Participantes:");
        etiquetaCantidad.setForeground(Color.WHITE);
        etiquetaCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        etiquetaCantidad.setBounds(500, 200, 200, 30);
        panel.add(etiquetaCantidad);

        // ComboBox para seleccionar número de participantes (2 al 8)
        JComboBox<String> comboBoxCantidad = new JComboBox<>();
        for (int i = 2; i <= 8; i++) {
            comboBoxCantidad.addItem(String.valueOf(i));
        }
        comboBoxCantidad.setBounds(700, 200, 100, 30);
        panel.add(comboBoxCantidad);

        // Etiqueta para tipo de participante
        JLabel etiquetaTipo = new JLabel("Tipo de Participante:");
        etiquetaTipo.setForeground(Color.WHITE);
        etiquetaTipo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        etiquetaTipo.setBounds(500, 250, 200, 30);
        panel.add(etiquetaTipo);

        // Radio Buttons para seleccionar Individuos o Equipos
        JRadioButton radioIndividuos = new JRadioButton("Individuos");
        radioIndividuos.setForeground(Color.WHITE);
        radioIndividuos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        radioIndividuos.setBackground(new Color(70, 45, 90));
        radioIndividuos.setBounds(700, 250, 100, 30);
        radioIndividuos.setSelected(true); // Seleccionado por defecto

        JRadioButton radioEquipos = new JRadioButton("Equipos");
        radioEquipos.setForeground(Color.WHITE);
        radioEquipos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        radioEquipos.setBackground(new Color(70, 45, 90));
        radioEquipos.setBounds(800, 250, 100, 30);

        // Agrupar los Radio Buttons en un ButtonGroup
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(radioIndividuos);
        grupoTipo.add(radioEquipos);

        panel.add(radioIndividuos);
        panel.add(radioEquipos);

        JLabel lblLista = new JLabel("Lista de participantes");
        lblLista.setForeground(Color.WHITE);
        lblLista.setBounds(950, 200, 200, 50);
        panel.add(lblLista);

        JTextArea areaParticipantes = new JTextArea();
        areaParticipantes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaParticipantes);
        scroll.setBounds(950, 300, 300, 200);
        panel.add(scroll);

        // Botón Agregar Lista
        JButton botonAgregar = new JButton("Agregar Lista");
        botonAgregar.setBackground(new Color(40, 40, 40));
        botonAgregar.setForeground(Color.WHITE);
        botonAgregar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonAgregar.setFocusPainted(false);
        botonAgregar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonAgregar.setBounds(500, 350, 200, 30);
        panel.add(botonAgregar);

        // Acción al presionar el botón
        botonAgregar.addActionListener(e -> {
            String cantidad = (String) comboBoxCantidad.getSelectedItem();
            String tipo = radioIndividuos.isSelected() ? "Individuos" : "Equipos";
            String nombreArchivo = cantidad + "_" + tipo + ".txt";

            try {
                ArrayList<Participante> resultado = LectorParticipantes.leerParticipantes(nombreArchivo);
                ((TorneoAbstracto) Navegador.torneo).agregarParticipantesDesdeLista(resultado);
                resultado.clear();

                // Mostrar los participantes agregados en consola
                for (Participante d : ((TorneoAbstracto) Navegador.torneo).obtenerParticipantes()) {
                    System.out.println(d);
                }

                JOptionPane.showMessageDialog(frame, "Lista de participantes agregada correctamente");
            } catch (TorneoException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        });

    new Timer(500, e -> {
    StringBuilder texto = new StringBuilder();
    for (Participante p : ((TorneoAbstracto) Navegador.torneo).obtenerParticipantes()) {
        texto.append("Nombre: ")
                .append(p.obtenerNombre())
                .append(" ")
                .append("Contacto: ")
                .append(p.obtenerContacto())
                .append("\n");
        }
        areaParticipantes.setText(texto.toString());

    }).start();
        panel.add(new BotonVolver(frame));
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}