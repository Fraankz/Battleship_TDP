package com.hundirlaflota.storage;

import com.hundirlaflota.hash.HashTable;
import com.hundirlaflota.model.BoatData;

import java.util.List;

/**
 * Clase que gestiona las tres tablas hash requeridas:
 * 1) typeTable: clave = tipo de barco, valor = otra tabla hash (número -> BoatData).
 * 2) nameTable: clave = nombre de barco, valor = BoatData.
 *
 * - El tamaño de typeTable debe ser suficientemente grande para los distintos tipos de barcos.
 * - Cada tabla secundaria (número -> BoatData) tiene tamaño 15.
 */
public class BoatStorage {

    // Primera tabla: type -> (tabla secundaria)
    // Donde la tabla secundaria es (number -> BoatData) con tamaño 15
    private HashTable<String, HashTable<Integer, BoatData>> typeTable;

    // Tercera tabla: name -> BoatData
    private HashTable<String, BoatData> nameTable;

    public BoatStorage(int capacityForTypes, int capacityForNames) {
        // Tabla 1: Se asume un tamaño suficiente para todos los tipos (ej. capacityForTypes=10 o 20).
        this.typeTable = new HashTable<>(capacityForTypes);
        // Tabla 3: Para nombres (ej. capacityForNames=50).
        this.nameTable = new HashTable<>(capacityForNames);
    }

    /**
     * Permite cargar un barco con su número, nombre, lista de tipos y nivel.
     * - Si el barco tiene varios tipos, se inserta en cada uno de esos tipos.
     * - También se inserta en la tabla de nombres.
     */
    public void addBoat(BoatData boat) {
        // Insertar en la tabla de nombres (tabla 3)
        nameTable.insert(boat.getName(), boat);

        // Insertar en la tabla de tipos
        List<String> tipos = boat.getTypes();
        if (tipos != null) {
            for (String tipo : tipos) {
                // Buscar si ya existe una tabla secundaria para este tipo
                HashTable<Integer, BoatData> secondaryTable = typeTable.search(tipo);

                if (secondaryTable == null) {
                    // Crear una nueva tabla hash de tamaño 15 para almacenar (numero -> BoatData)
                    secondaryTable = new HashTable<>(15);
                    typeTable.insert(tipo, secondaryTable);
                }

                // Insertar el barco en la tabla secundaria, usando su número como clave
                secondaryTable.insert(boat.getNumber(), boat);
            }
        }
    }

    /**
     * Busca un barco por nombre en la tabla de nombres.
     */
    public BoatData searchByName(String name) {
        return nameTable.search(name);
    }

    /**
     * Busca un barco por tipo y número.
     */
    public BoatData searchByTypeAndNumber(String tipo, int number) {
        HashTable<Integer, BoatData> secondaryTable = typeTable.search(tipo);
        if (secondaryTable != null) {
            return secondaryTable.search(number);
        }
        return null;
    }

    /**
     * Elimina un barco de la tabla de nombres y de cada tabla de tipos en las que esté.
     */
    public void removeBoat(BoatData boat) {
        // Eliminar de la tabla de nombres
        nameTable.remove(boat.getName());

        // Eliminar de cada tabla secundaria de tipos
        if (boat.getTypes() != null) {
            for (String tipo : boat.getTypes()) {
                HashTable<Integer, BoatData> secondaryTable = typeTable.search(tipo);
                if (secondaryTable != null) {
                    secondaryTable.remove(boat.getNumber());
                }
            }
        }
    }
}
