package com.hundirlaflota.model;

/**
 * Frigate es un barco de tamaño fijo 3.
 */
public class Frigate extends Ship {

    // Constructor privado para usar el Builder
    private Frigate(Builder builder) {
        super(builder.size);
    }

    // Builder interno para Frigate
    public static class Builder {
        private int size = 3; // Tamaño fijo

        public Builder() {}

        public Frigate build() {
            return new Frigate(this);
        }
    }
}
