package com.hundirlaflota.app;

import com.hundirlaflota.model.Battleship;
import com.hundirlaflota.model.Frigate;
import com.hundirlaflota.model.Canoe;
import com.hundirlaflota.model.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Módulo principal del juego "Hundir la flota".
 * Cada jugador configura hasta 3 barcos. En cada turno,
 * ambos jugadores se atacan. Si al final de un turno
 * uno o ambos jugadores se quedan sin barcos, el juego termina.
 */
public class App {

    private static final int MAX_BARCOS = 3;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("=== HUNDIR LA FLOTA ===");

        // Configurar barcos para cada jugador
        System.out.println("\nConfiguración Jugador 1");
        List<Ship> barcosJugador1 = configurarBarcos();

        System.out.println("\nConfiguración Jugador 2");
        List<Ship> barcosJugador2 = configurarBarcos();

        // Iniciar la partida
        System.out.println("\n--- ¡Comienza la partida! ---");
        int turno = 0;
        while (true) {
            turno++;
            System.out.println("\n===== TURNO " + turno + " =====");

            // Ataque de Jugador 1 a Jugador 2
            if (!barcosJugador2.isEmpty()) {
                realizarAtaque(1, 2, barcosJugador1, barcosJugador2);
            }

            // Ataque de Jugador 2 a Jugador 1
            if (!barcosJugador1.isEmpty()) {
                realizarAtaque(2, 1, barcosJugador2, barcosJugador1);
            }

            // Eliminar los barcos hundidos de cada lista
            eliminarBarcosHundidos(barcosJugador1, 1);
            eliminarBarcosHundidos(barcosJugador2, 2);

            // Comprobar el estado de los jugadores
            boolean jugador1SinBarcos = barcosJugador1.isEmpty();
            boolean jugador2SinBarcos = barcosJugador2.isEmpty();

            if (jugador1SinBarcos && jugador2SinBarcos) {
                System.out.println("\n¡Empate! Ambos jugadores se quedaron sin barcos en el mismo turno.");
                break;
            } else if (jugador1SinBarcos) {
                System.out.println("\n¡Gana el Jugador 2!");
                break;
            } else if (jugador2SinBarcos) {
                System.out.println("\n¡Gana el Jugador 1!");
                break;
            }
        }

        System.out.println("\n--- Fin de la partida ---");
    }

    /**
     * Pide por consola la configuración de barcos para un jugador.
     * Cada jugador puede tener como máximo MAX_BARCOS barcos.
     */
    private static List<Ship> configurarBarcos() {
        List<Ship> barcos = new ArrayList<>();
        System.out.println("Puede colocar hasta " + MAX_BARCOS + " barcos.");
        for (int i = 1; i <= MAX_BARCOS; i++) {
            System.out.println("\n¿Desea colocar un barco? (S/N) [Barco " + i + " de " + MAX_BARCOS + "]");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                // Si el usuario no desea colocar más barcos, salimos
                break;
            }
            System.out.println("Seleccione tipo de barco:");
            System.out.println("1) Battleship (tamaño 5)");
            System.out.println("2) Frigate (tamaño 3)");
            System.out.println("3) Canoe (tamaño 1)");

            String opcion = scanner.nextLine().trim();
            Ship nuevoBarco = null;
            switch (opcion) {
                case "1":
                    nuevoBarco = new Battleship.Builder().build();
                    break;
                case "2":
                    nuevoBarco = new Frigate.Builder().build();
                    break;
                case "3":
                    nuevoBarco = new Canoe.Builder().build();
                    break;
                default:
                    System.out.println("Opción inválida. No se creará ningún barco.");
                    i--; // No contar este intento como un barco
                    continue;
            }
            barcos.add(nuevoBarco);
            System.out.println("Barco añadido: " + nuevoBarco.getClass().getSimpleName());
        }
        return barcos;
    }

    /**
     * Realiza un ataque de un jugador a otro.
     * - Se elige aleatoriamente uno de los barcos del rival.
     * - Se elige una posición aleatoria (entre 0 y size-1).
     * - Se llama al método hit() del barco elegido.
     */
    private static void realizarAtaque(int atacante, int defensor,
                                       List<Ship> barcosAtacante, List<Ship> barcosDefensor) {
        if (barcosDefensor.isEmpty()) {
            return;
        }
        // Elegir un barco enemigo al azar
        Ship barcoObjetivo = barcosDefensor.get(random.nextInt(barcosDefensor.size()));
        // Elegir una posición válida al azar
        int pos = random.nextInt(barcoObjetivo.size);
        System.out.println("Jugador " + atacante + " ataca al Jugador " + defensor
                + " (Barco: " + barcoObjetivo.getClass().getSimpleName()
                + ", Posición: " + pos + ")");
        barcoObjetivo.hit(pos);
    }

    /**
     * Elimina de la lista todos los barcos que estén hundidos tras el último turno.
     */
    private static void eliminarBarcosHundidos(List<Ship> barcos, int idJugador) {
        List<Ship> barcosVivos = new ArrayList<>();
        for (Ship b : barcos) {
            if (!b.isSunk()) {
                barcosVivos.add(b);
            } else {
                System.out.println("Jugador " + idJugador + " pierde un "
                        + b.getClass().getSimpleName() + " (hundido)");
            }
        }
        barcos.clear();
        barcos.addAll(barcosVivos);
    }
}
