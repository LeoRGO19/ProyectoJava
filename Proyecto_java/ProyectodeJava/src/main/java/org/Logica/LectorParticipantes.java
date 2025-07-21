package org.Logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para leer participantes desde un archivo de texto en la carpeta resources.
 * <p>
 * Devuelve una lista de Participante, que contiene objetos Equipo si hay separadores '%',
 * o objetos IndividuoParticipante si no los hay.
 * </p>
 */
public class LectorParticipantes {

    /**
     * Lee participantes desde un archivo en la carpeta resources.
     *
     * @param nombreArchivo El nombre del archivo de texto (ejemplo: "participantes.txt") ubicado en src/main/resources.
     * @return Una lista de Participante (Equipos o Individuos).
     * @throws TorneoException Si el formato del archivo es inválido, el archivo no se encuentra o hay errores al crear participantes.
     */
    public static ArrayList<Participante> leerParticipantes(String nombreArchivo) throws TorneoException {
        ArrayList<String> lineas = leerLineasArchivo(nombreArchivo);
        return procesarLineas(lineas);
    }

    /**
     * Lee las líneas del archivo desde resources.
     *
     * @param nombreArchivo Nombre del archivo.
     * @return Lista de líneas del archivo.
     * @throws TorneoException Si no se encuentra el archivo o hay un error de lectura.
     */
    private static ArrayList<String> leerLineasArchivo(String nombreArchivo) throws TorneoException {
        ArrayList<String> lineas = new ArrayList<>();
        try (InputStream inputStream = LectorParticipantes.class.getClassLoader().getResourceAsStream(nombreArchivo)) {
            if (inputStream == null) {
                throw new TorneoException("No se encontró el archivo en resources: " + nombreArchivo);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    lineas.add(linea.trim());
                }
            }
        } catch (IOException e) {
            throw new TorneoException("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }

    /**
     * Procesa las líneas para crear una lista de Participante.
     *
     * @param lineas Líneas del archivo.
     * @return Lista de Participante (Equipos o Individuos).
     * @throws TorneoException Si el formato es inválido.
     */
    private static ArrayList<Participante> procesarLineas(List<String> lineas) throws TorneoException {
        ArrayList<IndividuoParticipante> grupoActual = new ArrayList<>();
        ArrayList<Participante> equipos = new ArrayList<>();
        boolean haySeparador = false;
        int equipoCount = 1;
        int numeroLinea = 0;

        for (String linea : lineas) {
            numeroLinea++;
            if (linea.equals("%")) {
                haySeparador = true;
                procesarGrupoActual(grupoActual, equipos, equipoCount++);
            } else if (!linea.isEmpty()) {
                grupoActual.add(procesarLineaParticipante(linea, numeroLinea));
            }
        }

        if (!grupoActual.isEmpty()) {
            if (haySeparador) {
                procesarGrupoActual(grupoActual, equipos, equipoCount);
                return equipos;
            } else {
                return new ArrayList<>(grupoActual);
            }
        }

        return haySeparador ? equipos : new ArrayList<>();
    }

    /**
     * Procesa una línea para crear un IndividuoParticipante.
     *
     * @param linea La línea a procesar.
     * @param numeroLinea El número de línea para mensajes de error.
     * @return Un IndividuoParticipante.
     * @throws TorneoException Si el formato es inválido.
     */
    private static IndividuoParticipante procesarLineaParticipante(String linea, int numeroLinea) throws TorneoException {
        String[] partes = linea.split("\\s+");
        if (partes.length < 3 || partes.length > 4) {
            throw new TorneoException("Formato de línea inválido en línea " + numeroLinea + ": " + linea + " (se espera 'Nombre Apellido Edad [Correo]')");
        }
        try {
            String nombre = partes[0];
            String apellido = partes[1];
            int edad = Integer.parseInt(partes[2]);
            String correo = partes.length == 4 ? partes[3] : nombre.toLowerCase() + "." + apellido.toLowerCase() + "@default.com";
            return new IndividuoParticipante(nombre, apellido, edad, correo);
        } catch (NumberFormatException e) {
            throw new TorneoException("Edad inválida en línea " + numeroLinea + ": " + linea);
        }
    }

    /**
     * Crea un equipo a partir del grupo actual y lo añade a la lista de equipos.
     *
     * @param grupoActual Lista de individuos.
     * @param equipos Lista donde se añade el equipo.
     * @param equipoCount Número del equipo para el nombre.
     */
    private static void procesarGrupoActual(ArrayList<IndividuoParticipante> grupoActual, ArrayList<Participante> equipos, int equipoCount) throws TorneoException {
        if (!grupoActual.isEmpty()) {
            String nombreEquipo = "Equipo " + equipoCount;
            Equipo equipo = new Equipo(nombreEquipo, nombreEquipo.toLowerCase().replace(" ", "_") + "@gmail.com");
            for (IndividuoParticipante miembro : grupoActual) {
                equipo.agregarMiembro(miembro);
            }
            equipos.add(equipo);
            grupoActual.clear();
        }
    }
}