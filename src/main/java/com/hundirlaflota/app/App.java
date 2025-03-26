package com.hundirlaflota.app;

import com.hundirlaflota.model.Battleship;
import com.hundirlaflota.model.Frigate;
import com.hundirlaflota.model.Canoe;

/**
 * Clase principal de la aplicación.
 */
public class App {
    public static void main(String[] args) {
        // Creación de instancias usando el Builder de cada tipo de barco
        Battleship battleship = new Battleship.Builder().build();
        Frigate frigate = new Frigate.Builder().build();
        Canoe canoe = new Canoe.Builder().build();

        // Simulación de impactos en el Battleship
        System.out.println("Atacando Battleship:");
        for (int i = 0; i < 5; i++) {
            battleship.hit(i);
        }
        System.out.println("¿Battleship hundido? " + battleship.isSunk());

        // Simulación de impactos en la Frigate
        System.out.println("\nAtacando Frigate:");
        for (int i = 0; i < 3; i++) {
            frigate.hit(i);
        }
        System.out.println("¿Frigate hundido? " + frigate.isSunk());

        // Simulación de impacto en la Canoe
        System.out.println("\nAtacando Canoe:");
        canoe.hit(0);
        System.out.println("¿Canoe hundido? " + canoe.isSunk());
    }
}
