package com.hundirlaflota.app;

import com.hundirlaflota.model.BoatData;
import com.hundirlaflota.storage.BoatStorage;

import java.util.Arrays;

public class DemoHashTables {
    public static void main(String[] args) {
        // Crear la estructura de almacenamiento:
        // typeTable con capacidad 10, nameTable con capacidad 50 (por ejemplo)
        BoatStorage storage = new BoatStorage(10, 50);

        // Crear algunos barcos de prueba
        BoatData barco1 = new BoatData(1, "BarcoA", Arrays.asList("Battleship"), 5);
        BoatData barco2 = new BoatData(2, "BarcoB", Arrays.asList("Frigate", "Canoe"), 3);
        BoatData barco3 = new BoatData(3, "BarcoC", Arrays.asList("Frigate"), 2);

        // Cargar barcos en la estructura
        storage.addBoat(barco1);
        storage.addBoat(barco2);
        storage.addBoat(barco3);

        // Buscar por nombre
        System.out.println("Buscar BarcoB por nombre:");
        BoatData foundByName = storage.searchByName("BarcoB");
        System.out.println(foundByName);

        // Buscar por tipo y número
        System.out.println("\nBuscar BarcoC por tipo 'Frigate' y número 3:");
        BoatData foundByTypeNum = storage.searchByTypeAndNumber("Frigate", 3);
        System.out.println(foundByTypeNum);

        // Eliminar un barco
        System.out.println("\nEliminando BarcoA...");
        storage.removeBoat(barco1);

        // Intentar buscarlo otra vez por tipo
        System.out.println("Buscar BarcoA (tipo 'Battleship', num=1) tras eliminar:");
        BoatData afterRemoval = storage.searchByTypeAndNumber("Battleship", 1);
        System.out.println(afterRemoval); // debería ser null
    }
}
