package com.hundirlaflota.model;

/**
 * Battleship es un barco de tamaño fijo 5, que requiere que se golpeen todas sus posiciones
 * (contenedores aislados) para ser hundido.
 */
public class Battleship extends Ship {
    private boolean[] containerHits;

    // Constructor privado para usar el Builder
    private Battleship(Builder builder) {
        super(builder.size);
        this.containerHits = new boolean[builder.size];
    }

    // Sobreescribe el método hit para marcar también el contenedor golpeado
    @Override
    public void hit(int position) {
        if (position >= 0 && position < size) {
            containerHits[position] = true;
            super.hit(position);
        }
    }

    // El Battleship se hunde solo si todos los contenedores han sido golpeados
    @Override
    public boolean isSunk() {
        for (boolean hit : containerHits) {
            if (!hit) {
                return false;
            }
        }
        System.out.println("Battleship hundido");
        return true;
    }

    // Builder interno para crear instancias de Battleship
    public static class Builder {
        private int size = 5; // Tamaño fijo

        public Builder() {}

        public Battleship build() {
            return new Battleship(this);
        }
    }
}
