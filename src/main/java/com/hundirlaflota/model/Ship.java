package com.hundirlaflota.model;

/**
 * Clase abstracta base que define la lógica común de los barcos.
 */
public abstract class Ship {
    public int size;
    protected boolean[] hits;

    protected Ship(int size) {
        this.size = size;
        this.hits = new boolean[size];
    }

    // Registra un impacto en la posición indicada
    public void hit(int position) {
        if (position >= 0 && position < size) {
            hits[position] = true;
            System.out.println(this.getClass().getSimpleName() + " impactado en posición " + position);
        }
    }

    // Retorna true si todas las posiciones han sido impactadas
    public boolean isSunk() {
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }
}
