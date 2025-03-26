package com.hundirlaflota.model;

import java.util.List;

/**
 * Representa la información de un barco, que puede tener uno o más tipos.
 */
public class BoatData {
    private int number;         // Número del barco
    private String name;        // Nombre del barco
    private List<String> types; // Lista de tipos (e.g. ["Battleship", "Frigate"])
    private int level;          // Nivel del barco (o cualquier otro dato extra)

    public BoatData(int number, String name, List<String> types, int level) {
        this.number = number;
        this.name = name;
        this.types = types;
        this.level = level;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public List<String> getTypes() {
        return types;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "BoatData{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", types=" + types +
                ", level=" + level +
                '}';
    }
}
