package com.hundirlaflota.model;

/**
 * Canoe es un barco de tamaño fijo 1.
 */
public class Canoe extends Ship {

    // Constructor privado para usar el Builder
    private Canoe(Builder builder) {
        super(builder.size);
    }

    // Builder interno para Canoe
    public static class Builder {
        private int size = 1; // Tamaño fijo

        public Builder() {}

        public Canoe build() {
            return new Canoe(this);
        }
    }
}
